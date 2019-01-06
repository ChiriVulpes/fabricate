package yuudaari.fabricate.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import yuudaari.fabricate.api.IRecipeQuery;
import yuudaari.fabricate.api.RegistryEvent;
import yuudaari.fabricate.wrappers.recipe.Ingredient;
import yuudaari.fabricate.wrappers.recipe.ItemStack;
import yuudaari.fabricate.wrappers.recipe.Recipe;
import static yuudaari.fabricate.api.FabricateAPI.Fabricate;

public class RecipeQuery implements IRecipeQuery {

	private final IForgeRegistryModifiable<IRecipe> REGISTRY;
	private final List<Function<Recipe, Boolean>> PREDICATES = new ArrayList<>();
	private boolean immediate = false;
	private boolean finished = false;

	@SafeVarargs
	public RecipeQuery (final IForgeRegistryModifiable<IRecipe> registry, final Function<Recipe, Boolean>... predicates) {
		REGISTRY = registry;
		for (final Function<Recipe, Boolean> predicate : predicates) {
			PREDICATES.add(predicate);
		}
	}

	/**
	 * Sets the query to resolve immediately. 
	 * By default queries are all processed at once, after all scripts are finished. 
	 * Each query which runs immediately requires a loop over all existing recipes.
	 * When a lot of recipes need to be removed or replaced, it's not recommended
	 * to use immediate mode to replace them.
	 */
	@Override
	public IRecipeQuery setImmediate () {
		this.immediate = true;
		return this;
	}


	/**
	 * Filter all recipes with the given output
	 */
	@Override
	public IRecipeQuery filterByOutput (Object result) {
		return filterByOutput(result, null);
	}

	/**
	 * Filter all recipes with the given output
	 */
	@Override
	public IRecipeQuery filterByOutput (final Object result, final Integer count) {
		// Llog.info(result);
		final Ingredient ingredient = new Ingredient(result);
		/*Llog.info(Arrays.asList(ingredient.getMatchingStacks())
			.stream()
			.map(s -> s.toString())
			.collect(Collectors.joining(", ")));*/
		final boolean shouldMatchCount = !(result instanceof String);
		return filter(recipe -> ingredientMatchesStack(ingredient, recipe.getOutput(), shouldMatchCount, count));
	}

	private boolean ingredientMatchesStack (final Ingredient ingredient, final ItemStack stack, final boolean shouldMatchCount, Integer matchCount) {
		for (final ItemStack matchStack : ingredient.getStacks()) {

			if (matchStack.getStack().getItem() != stack.getStack().getItem())
				continue;

			final int i = matchStack.getMetadata();
			if (i != OreDictionary.WILDCARD_VALUE && i != stack.getMetadata())
				continue;

			if (matchCount == null) matchCount = matchStack.getCount();
			if (shouldMatchCount && matchCount != OreDictionary.WILDCARD_VALUE && matchCount != stack.getCount())
				continue;

			return true;
		}

		return false;
	}

	/**
	 * Filter all recipes by name
	 */
	@Override
	public RecipeQuery filterByName (String name) {
		filter(recipe -> recipe.getRegistryName().toString().equalsIgnoreCase(name));
		return this;
	}

	/**
	 * Further filter the remaining recipes
	 */
	@Override
	public IRecipeQuery filter (final Function<Recipe, Boolean> predicate) {
		PREDICATES.add(predicate);
		return this;
	}

	/**
	 * Get all recipes that match the filters
	 */
	@Override
	public void forEach (final Function<Recipe, Boolean> consumer) {
		execute(consumer);
	}

	/**
	 * Get the first recipe that matches the filters
	 */
	@Override
	public void forFirst (final Consumer<Recipe> consumer) {
		execute(recipe -> {
			consumer.accept(recipe);
			return false;
		});
	}

	/**
	 * Remove all recipes that match the filters
	 */
	@Override
	public void removeAll () {
		execute(recipe -> {
			REGISTRY.remove(recipe.getRegistryName());
			return true;
		});
	}

	/**
	 * Remove the first recipe that matches the filters, then return
	 */
	@Override
	public void removeFirst () {
		execute(recipe -> {
			REGISTRY.remove(recipe.getRegistryName());
			return false;
		});
	}

	/**
	 * Remove all recipes that match the filters
	 */
	@Override
	public void replaceAll (final Function<Recipe, Object> handler) {
		execute(recipe -> {
			REGISTRY.remove(recipe.getRegistryName());

			final Object replaceWithVague = handler.apply(recipe);

			IRecipe replaceWith;
			if (replaceWithVague instanceof Recipe) {
				replaceWith = ((Recipe) replaceWithVague).getRecipe();

			} else if (replaceWithVague instanceof IRecipe) {
				replaceWith = (IRecipe) replaceWithVague;

			} else {
				throw new IllegalArgumentException("Recipe replacement must return an IRecipe instance");
			}

			replaceWith.setRegistryName(recipe.getRegistryName());
			REGISTRY.register(replaceWith);

			return true;
		});
	}

	/**
	 * Replaces the first recipe that matches the filters.
	 */
	@Override
	public void replaceFirst (final Function<Recipe, Object> handler) {
		execute(recipe -> {
			REGISTRY.remove(recipe.getRegistryName());

			final Object replaceWithVague = handler.apply(recipe);

			IRecipe replaceWith;
			if (replaceWithVague instanceof Recipe) {
				replaceWith = ((Recipe) replaceWithVague).getRecipe();

			} else if (replaceWithVague instanceof IRecipe) {
				replaceWith = (IRecipe) replaceWithVague;

			} else {
				throw new IllegalArgumentException("Recipe replacement must return an IRecipe instance");
			}

			replaceWith.setRegistryName(recipe.getRegistryName());
			REGISTRY.register(replaceWith);

			return false;
		});
	}

	/**
	 * <p>Executes the given handler in one of two contexts, 
	 * depending on whether <code>setImmediate()</code> was called.</p>
	 * 
	 * <p>Immediate mode: The recipes will begin looping immediately. Each recipe that matches the filters will call the handler.</p>
	 * 
	 * <p>Non-immediate mode: The recipes will loop on the <code>RegisteredRecipe</code> event.</p>
	 */
	private void execute (final Function<Recipe, Boolean> handler) {
		if (!immediate) {
			Fabricate.on(RegistryEvent.RegisteredRecipe.name(), recipe -> {
				if (finished) return;

				for (final Function<Recipe, Boolean> predicate : PREDICATES) {
					if (!predicate.apply((Recipe) recipe)) return;
				}

				finished = !handler.apply((Recipe) recipe);
			});
			return;
		}

		// immediate
		TOP:
		for (final Entry<ResourceLocation, IRecipe> recipe : new ArrayList<>(REGISTRY.getEntries())) {
			final Recipe wrappedRecipe = new Recipe(recipe.getValue());

			for (final Function<Recipe, Boolean> predicate : PREDICATES) {
				if (!predicate.apply(wrappedRecipe)) continue TOP;
			}

			if (!handler.apply(wrappedRecipe)) break;
		}
	}
}
