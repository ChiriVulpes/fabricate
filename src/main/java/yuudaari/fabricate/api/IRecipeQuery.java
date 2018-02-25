package yuudaari.fabricate.api;

import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.item.crafting.IRecipe;

public interface IRecipeQuery {

	/**
	 * Sets the query to resolve immediately. 
	 * By default queries are all processed at once, after all scripts are finished. 
	 * Each query which runs immediately requires a loop over all existing recipes.
	 * When a lot of recipes need to be removed or replaced, it's not recommended
	 * to use immediate mode to replace them.
	 */
	public IRecipeQuery setImmediate ();

	/**
	 * Filter the query
	 */
	public IRecipeQuery filter (Function<IRecipe, Boolean> predicate);

	/**
	 * Call a handler for all recipes that match the filters. Return `true` to continue, and `false` to break.
	 */
	public void forEach (Function<IRecipe, Boolean> consumer);

	/**
	 * Call a handler for the first recipe that matches the filters
	 */
	public void forFirst (Consumer<IRecipe> consumer);

	/**
	 * Remove all recipes that match the filters
	 */
	public void removeAll ();

	/**
	 * Remove the first recipe that matches the filters, then return
	 */
	public void removeFirst ();

	/**
	 * Replaces all recipes that match the filters
	 */
	public void replaceAll (Function<IRecipe, IRecipe> handler);

	/**
	 * Replaces the first recipe that matches the filters
	 */
	public void replaceFirst (Function<IRecipe, IRecipe> handler);
}
