package yuudaari.fabricate.api;

import java.util.function.Consumer;
import yuudaari.fabricate.recipe.IngredientHelper;
import yuudaari.fabricate.wrappers.recipe.Ingredient;
import yuudaari.fabricate.wrappers.recipe.ItemStack;

public class FabricateAPI {

	private IApiWrapper wrapper;

	public FabricateAPI (final IApiWrapper wrapper) {
		this.wrapper = wrapper;
	}

	public static FabricateAPI Fabricate = null;


	public void on (final String event, final Consumer<Object> handler) {
		wrapper.addEventHandler(event, handler);
	}

	public ItemStack stack (final Object input) {
		return stack(input, 1);
	}

	public ItemStack stack (final Object input, final int count) {
		return IngredientHelper.getStacks(input).get(0).setCount(count);
	}

	public Ingredient ingredient (final Object... inputs) {
		return new Ingredient(inputs);
	}
}
