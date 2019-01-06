package yuudaari.fabricate.wrappers.recipe;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import yuudaari.fabricate.recipe.IngredientHelper;
import yuudaari.fabricate.wrappers.recipe.ItemStack;

public class Ingredient {

	public static Ingredient getEmpty () {
		return new Ingredient(ItemStack.EMPTY);
	}

	private final List<ItemStack> baseStacks = new ArrayList<>();
	private boolean simple = true;
	private net.minecraft.item.crafting.Ingredient ingredient;

	public Ingredient (final Object... stacks) {
		addStacks(stacks);
	}

	public Ingredient addStacks (final Object... stacks) {
		for (final Object stackIn : stacks) {
			this.baseStacks.addAll(IngredientHelper.getStacks(stackIn));
		}

		ingredient = null; // next time the ingredient is retrieved it will be regenerated
		return this;
	}

	public List<ItemStack> getStacks () {
		return baseStacks;
	}

	public net.minecraft.item.crafting.Ingredient getIngredient () {
		if (ingredient != null) return ingredient;

		final net.minecraft.item.ItemStack[] stacks = getStacksForIngredient().toArray(new net.minecraft.item.ItemStack[0]);

		ingredient = new net.minecraft.item.crafting.Ingredient(stacks) {

			@Override
			public boolean isSimple () {
				return simple;
			}
		};

		return ingredient;
	}

	private List<net.minecraft.item.ItemStack> getStacksForIngredient () {
		final List<net.minecraft.item.ItemStack> stacks = new ArrayList<>();

		simple = true;

		for (final ItemStack stack : this.baseStacks) {
			if (stack.isEmpty())
				continue;

			if (stack.getMetadata() == OreDictionary.WILDCARD_VALUE) {
				if (stack.getItem().isDamageable())
					simple = false;

				final NonNullList<net.minecraft.item.ItemStack> nnl = NonNullList.create();
				stack.getItem().getItem().getSubItems(net.minecraft.creativetab.CreativeTabs.SEARCH, nnl);
				for (final net.minecraft.item.ItemStack substack : nnl) {
					substack.setCount(stack.getCount());
					stacks.add(substack);
				}

			} else {
				stacks.add(stack.getStack());
			}
		}

		return stacks;
	}
}
