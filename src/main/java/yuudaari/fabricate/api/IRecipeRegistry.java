package yuudaari.fabricate.api;

import java.util.function.Function;
import net.minecraft.item.crafting.IRecipe;

public interface IRecipeRegistry {

	///////////////////////////////////
	// CREATION
	//

	/**
	 * Create a shaped recipe
	 */
	public IRecipe createShaped (Object result, Object[][] ingredients);

	/**
	 * Create a shapeless recipe
	 */
	public IRecipe createShapeless (Object result, Object[] ingredients);

	/**
	 * Creates an empty recipe
	 */
	public IRecipe createEmpty ();


	///////////////////////////////////
	// ADDING
	//

	/**
	 * Add the given recipe
	 */
	public void add (IRecipe recipe);

	/**
	 * Add a shaped recipe
	 */
	public void addShaped (Object result, Object[][] ingredients);

	/**
	 * Add a shapeless recipe
	 */
	public void addShapeless (Object result, Object[] ingredients);


	///////////////////////////////////
	// QUERYING
	//

	/**
	 * Filter all recipes with the given output
	 */
	public IRecipeQuery withOutput (Object result);

	/**
	 * Filter all recipes with the given output
	 */
	public IRecipeQuery withOutput (Object result, Integer count);

	/**
	 * Filter all recipes by name
	 */
	public IRecipeQuery byName (String name);

	/**
	 * Filter all recipes by a predicate
	 */
	public IRecipeQuery filter (Function<IRecipe, Boolean> predicate);
}
