package yuudaari.fabricate.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraftforge.oredict.OreDictionary;
import yuudaari.fabricate.api.FabricateAPI;
import yuudaari.fabricate.wrappers.recipe.ItemStack;

public class IngredientHelper {

	public static List<ItemStack> getStacks (Object input) {
		if (input instanceof Iterable<?>) {
			final List<ItemStack> result = new ArrayList<>();
			for (final Object obj : (Iterable<?>) input) {
				result.addAll(getStacks(obj));
			}
			return result;
		}

		if (input instanceof String) {
			String inputString = (String) input;

			// ore dictionary
			if (inputString.charAt(0) == '$') {
				return OreDictionary.getOres(inputString.substring(1))
					.stream()
					.map(ItemStack::new)
					.collect(Collectors.toList());
			}

			input = FabricateAPI.Fabricate.stack(inputString);
		}

		if (input instanceof net.minecraft.item.ItemStack) {
			input = new ItemStack((net.minecraft.item.ItemStack) input);
		}

		if (input instanceof ItemStack) {
			return Collections.singletonList((ItemStack) input);
		}

		throw new IllegalArgumentException("Ingredient of type '" + input.getClass() + "'");
	}
}
