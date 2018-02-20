package yuudaari.fabricate.api;

import java.util.function.Function;
import net.minecraft.item.crafting.IRecipe;

public interface IRecipeQuery {

	/**
	 * Filter the query
	 */
	public IRecipeQuery filter (Function<IRecipe, Boolean> predicate);

	/**
	 * Get all recipes that match the filters
	 */
	public IRecipe[] getAll ();

	/**
	 * Get the first recipe that matches the filters
	 */
	public IRecipe getFirst ();

	/**
	 * Remove all recipes that match the filters
	 */
	public void removeAll ();

	/**
	 * Remove the first recipe that matches the filters, then return
	 */
	public void removeFirst ();
}
