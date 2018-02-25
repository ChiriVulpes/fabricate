package yuudaari.fabricate.recipe;

import java.util.Stack;
import java.util.function.Function;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import yuudaari.fabricate.api.FabricateAPI;
import yuudaari.fabricate.api.IRecipeQuery;
import yuudaari.fabricate.api.IRecipeRegistry;

public class RecipeRegistry implements IRecipeRegistry {

	private final IForgeRegistryModifiable<IRecipe> REGISTRY;
	private int recipeIndex = 0;

	public RecipeRegistry (final IForgeRegistry<IRecipe> registry) {
		REGISTRY = (IForgeRegistryModifiable<IRecipe>) registry;
	}

	private ItemStack getRecipeOutput (final Object output) {
		final ItemStack result = getIngredient(output).getMatchingStacks()[0];
		if (result.getItemDamage() == 32767) result.setItemDamage(0);
		return result;
	}

	private Ingredient getIngredient (Object input) {
		if (input instanceof String) {
			String inputString = (String) input;
			if (inputString.charAt(0) == '$') {
				// ore dictionary
				return Ingredient.create(OreDictionary.getOres(inputString.substring(1)));
			}

			input = FabricateAPI.Fabricate.stack(inputString);
		}
		if (input instanceof ItemStack) {
			return Ingredient.create(input);
		}

		throw new IllegalArgumentException("Ingredient of type '" + input.getClass() + "'");
	}

	/**
	 * Create a shaped recipe
	 */
	@Override
	public IRecipe createShaped (final Object result, final Object[][] inputs) {
		final Stack<Object> ingredients = new Stack<>();

		int i = 0;
		for (final Object[] inputArr : inputs) {
			String row = "";
			for (final Object input : inputArr) {
				final char symbol = input == null ? ' ' : Character.forDigit(i++, 10);
				row += symbol;
				if (symbol == ' ' || input == null) continue;

				ingredients.push(symbol);
				final Ingredient ingredient = getIngredient(input);
				if (ingredient == null) return null;
				ingredients.push(ingredient);
			}
			ingredients.insertElementAt(row, (i - 1) / 3);
		}

		final IRecipe recipe = new ShapedOreRecipe(null, getRecipeOutput(result), ingredients.toArray(new Object[0]));
		recipe.setRegistryName(new ResourceLocation("fabricate", "recipe-" + recipeIndex++));

		return recipe;
	}

	/**
	 * Create a shapeless recipe
	 */
	@Override
	public IRecipe createShapeless (final Object result, final Object[] inputs) {

		final Object[] ingredients = new Ingredient[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			ingredients[i] = getIngredient(inputs[i]);
		}

		final IRecipe recipe = new ShapelessOreRecipe(null, getRecipeOutput(result), ingredients);
		recipe.setRegistryName(new ResourceLocation("fabricate", "recipe-" + recipeIndex++));

		return recipe;
	}

	/**
	 * Creates an empty recipe
	 */
	@Override
	public IRecipe createEmpty () {
		return new RecipeShapeless(ItemStack.EMPTY, ItemStack.EMPTY);
	}


	///////////////////////////////////
	// ADDING
	//

	/**
	 * Add the given recipe
	 */
	@Override
	public void add (final IRecipe recipe) {
		REGISTRY.register(recipe);
	}

	/**
	 * Add a shaped recipe
	 */
	@Override
	public void addShaped (final Object result, final Object[][] inputs) {
		add(createShaped(result, inputs));
		// Llog.info("added shaped recipe for ", result);
	}

	/**
	 * Add a shapeless recipe
	 */
	@Override
	public void addShapeless (final Object result, final Object[] inputs) {
		add(createShapeless(result, inputs));
		// Llog.info("added shapeless recipe for ", result);
	}


	///////////////////////////////////
	// QUERYING
	//

	/**
	 * Filter all recipes with the given output
	 */
	@Override
	public IRecipeQuery withOutput (Object result) {
		return withOutput(result, null);
	}

	/**
	 * Filter all recipes with the given output
	 */
	@Override
	public IRecipeQuery withOutput (final Object result, final Integer count) {
		// Llog.info(result);
		final Ingredient ingredient = getIngredient(result);
		/*Llog.info(Arrays.asList(ingredient.getMatchingStacks())
			.stream()
			.map(s -> s.toString())
			.collect(Collectors.joining(", ")));*/
		final boolean shouldMatchCount = !(result instanceof String);
		return filter(recipe -> ingredientMatchesStack(ingredient, recipe.getRecipeOutput(), shouldMatchCount, count));
	}

	private boolean ingredientMatchesStack (final Ingredient ingredient, final ItemStack stack, final boolean shouldMatchCount, Integer matchCount) {
		for (final ItemStack matchStack : ingredient.getMatchingStacks()) {

			if (matchStack.getItem() != stack.getItem())
				continue;

			final int i = matchStack.getMetadata();
			if (i != 32767 && i != stack.getMetadata())
				continue;

			if (matchCount == null) matchCount = matchStack.getCount();
			if (shouldMatchCount && matchCount != 32767 && matchCount != stack.getCount())
				continue;

			return true;
		}

		return false;
	}

	/**
	 * Filter all recipes by name
	 */
	@Override
	public IRecipeQuery byName (String name) {
		return filter(recipe -> recipe.getRegistryName().toString().equalsIgnoreCase(name));
	}

	/**
	 * Filter all recipes by a predicate
	 */
	@Override
	public IRecipeQuery filter (final Function<IRecipe, Boolean> predicate) {
		return new RecipeQuery(REGISTRY, predicate);
	}
}
