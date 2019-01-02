package yuudaari.fabricate.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import yuudaari.fabricate.api.IRecipeQuery;
import yuudaari.fabricate.api.RegistryEvent;
import static yuudaari.fabricate.api.FabricateAPI.Fabricate;

public class RecipeQuery implements IRecipeQuery {

	private final IForgeRegistryModifiable<IRecipe> REGISTRY;
	private final List<Function<IRecipe, Boolean>> PREDICATES = new ArrayList<>();
	private boolean immediate = false;
	private boolean finished = false;

	public RecipeQuery (final IForgeRegistryModifiable<IRecipe> registry, final Function<IRecipe, Boolean> predicate) {
		REGISTRY = registry;
		PREDICATES.add(predicate);
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
	 * Further filter the remaining recipes
	 */
	@Override
	public IRecipeQuery filter (final Function<IRecipe, Boolean> predicate) {
		PREDICATES.add(predicate);
		return this;
	}

	/**
	 * Get all recipes that match the filters
	 */
	@Override
	public void forEach (final Function<IRecipe, Boolean> consumer) {
		execute(consumer);
	}

	/**
	 * Get the first recipe that matches the filters
	 */
	@Override
	public void forFirst (final Consumer<IRecipe> consumer) {
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
	public void replaceAll (final Function<IRecipe, IRecipe> handler) {
		execute(recipe -> {
			REGISTRY.remove(recipe.getRegistryName());

			final IRecipe replaceWith = handler.apply(recipe);
			replaceWith.setRegistryName(recipe.getRegistryName());
			REGISTRY.register(replaceWith);

			return true;
		});
	}

	/**
	 * Replaces the first recipe that matches the filters.
	 */
	@Override
	public void replaceFirst (final Function<IRecipe, IRecipe> handler) {
		execute(recipe -> {
			REGISTRY.remove(recipe.getRegistryName());

			final IRecipe replaceWith = handler.apply(recipe);
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
	private void execute (final Function<IRecipe, Boolean> handler) {
		if (!immediate) {
			Fabricate.on(RegistryEvent.RegisteredRecipe.name(), recipe -> {
				if (finished) return;

				for (final Function<IRecipe, Boolean> predicate : PREDICATES) {
					if (!predicate.apply((IRecipe) recipe)) return;
				}

				finished = !handler.apply((IRecipe) recipe);
			});
			return;
		}

		// immediate
		TOP:
		for (final Entry<ResourceLocation, IRecipe> recipe : new ArrayList<>(REGISTRY.getEntries())) {
			for (final Function<IRecipe, Boolean> predicate : PREDICATES) {
				if (!predicate.apply(recipe.getValue())) continue TOP;
			}

			if (!handler.apply(recipe.getValue())) break;
		}
	}
}
