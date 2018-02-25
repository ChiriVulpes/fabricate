package yuudaari.fabricate.api;

public class RegistryEvents {

	/**
	 * Register an event handler for recipe registration
	 */
	public int Recipes = 0;

	/**
	 * This event is emitted for each registered recipe, after `RegistryEvent.Recipes`.
	 */
	public int RegisteredRecipe = 1;
}
