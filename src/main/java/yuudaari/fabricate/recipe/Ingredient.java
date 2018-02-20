package yuudaari.fabricate.recipe;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Ingredient extends net.minecraft.item.crafting.Ingredient {

	private boolean isSimple;

	public Ingredient (List<ItemStack> stacks) {
		super(stacks.toArray(new ItemStack[0]));
	}

	@Override
	public boolean isSimple () {
		return isSimple;
	}

	public static Ingredient create (Object... inputs) {
		List<ItemStack> stacks = new ArrayList<>();
		boolean simple = true;

		for (Object input : inputs) {
			ItemStack stack = null;
			if (input instanceof ItemStack) {
				stack = (ItemStack) input;
			}

			if (stack.isEmpty())
				continue;

			if (stack.getMetadata() == net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE) {
				if (stack.getItem().isDamageable())
					simple = false;

				NonNullList<ItemStack> nnl = NonNullList.create();
				stack.getItem().getSubItems(net.minecraft.creativetab.CreativeTabs.SEARCH, nnl);
				for (ItemStack substack : nnl) {
					substack.setCount(stack.getCount());
					stacks.add(substack);
				}
			}

			stacks.add(stack);
		}

		Ingredient result = new Ingredient(stacks);
		result.isSimple = simple;

		return result;
	}
}
