package yuudaari.fabricate.api;

import java.util.HashMap;

public class RegistryEvents extends HashMap<String, Object> {

	public static enum RegistryEvent {
		Recipes,
		RegisteredRecipe,
	}

	private static final long serialVersionUID = 3624982352343184575L;

	public RegistryEvents () {

		/**
		 * Register an event handler for recipe registration
		 */
		this.put(RegistryEvent.Recipes.name(), 0);

		/**
		 * This event is emitted for each registered recipe, after `RegistryEvent.Recipes`.
		 */
		this.put(RegistryEvent.RegisteredRecipe.name(), 1);
	}

	public Object get (RegistryEvent event) {
		return get(event.name());
	}
}
