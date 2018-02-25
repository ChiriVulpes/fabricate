package yuudaari.fabricate.recipe;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Ingredient extends net.minecraft.item.crafting.Ingredient {

	private boolean isSimple;

	private Ingredient (final List<ItemStack> stacks) {
		super(stacks.toArray(new ItemStack[0]));
	}

	@Override
	public boolean isSimple () {
		return isSimple;
	}

	public static Ingredient create (final NonNullList<ItemStack> inputs) {
		return create(inputs.toArray(new Object[0]));
	}

	public static Ingredient create (final Object... inputs) {
		final List<ItemStack> stacks = new ArrayList<>();
		boolean simple = true;

		for (final Object input : inputs) {
			final ItemStack stack = input instanceof ItemStack ? (ItemStack) input : null;

			if (stack.isEmpty())
				continue;

			if (stack.getMetadata() == net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE) {
				if (stack.getItem().isDamageable())
					simple = false;

				final NonNullList<ItemStack> nnl = NonNullList.create();
				stack.getItem().getSubItems(net.minecraft.creativetab.CreativeTabs.SEARCH, nnl);
				for (final ItemStack substack : nnl) {
					substack.setCount(stack.getCount());
					stacks.add(substack);
				}

			} else {
				stacks.add(stack);
			}
		}

		final Ingredient result = new Ingredient(stacks);
		result.isSimple = simple;

		return result;
	}
}
