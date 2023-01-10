package works.chiri.fabricate.api;

import java.util.function.Function;
import works.chiri.fabricate.recipe.RecipeBuilder;

public interface IRecipeRegistry {

	/**
	 * Begin constructing a shaped recipe
	 */
	public IRecipeRegistry addShaped (final String recipeName, final Function<RecipeBuilder.Shaped, IRecipeBuilder<?>> initializer);

	/**
	 * Begin constructing a shapeless recipe
	 */
	public IRecipeRegistry addShapeless (final String recipeName, final Function<RecipeBuilder.Shapeless, IRecipeBuilder<?>> initializer);

	/**
	 * Begin a recipe query
	 */
	public IRecipeQuery query ();
}
