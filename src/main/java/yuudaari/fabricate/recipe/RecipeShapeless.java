package yuudaari.fabricate.recipe;

import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;

public class RecipeShapeless extends Recipe {

	protected ItemStack output = ItemStack.EMPTY;
	protected NonNullList<Ingredient> input = NonNullList.create();

	public RecipeShapeless (final Block result, final Object... recipe) {
		this(new ItemStack(result), recipe);
	}

	public RecipeShapeless (final Item result, final Object... recipe) {
		this(new ItemStack(result), recipe);
	}

	public RecipeShapeless (final NonNullList<Ingredient> input, final ItemStack result) {
		output = result.copy();
		this.input = input;
	}

	public RecipeShapeless (final ItemStack result, final Object... recipe) {
		output = result.copy();
		for (final Object in : recipe) {
			final Ingredient ing = CraftingHelper.getIngredient(in);
			if (ing != null) {
				input.add(ing);
			} else {
				String ret = "Invalid shapeless ore recipe: ";
				for (final Object tmp : recipe) {
					ret += tmp + ", ";
				}
				ret += output;
				throw new RuntimeException(ret);
			}
		}
	}

	@Override
	public ItemStack getRecipeOutput () {
		return output;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Override
	public ItemStack getCraftingResult (final InventoryCrafting var1) {
		return output.copy();
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches (final InventoryCrafting var1, final World world) {
		final NonNullList<Ingredient> required = NonNullList.create();
		required.addAll(input);

		for (int x = 0; x < var1.getSizeInventory(); x++) {
			final ItemStack slot = var1.getStackInSlot(x);

			if (!slot.isEmpty()) {
				boolean inRecipe = false;
				final Iterator<Ingredient> req = required.iterator();

				while (req.hasNext()) {
					if (req.next().apply(slot)) {
						inRecipe = true;
						req.remove();
						break;
					}
				}

				if (!inRecipe) {
					return false;
				}
			}
		}

		return required.isEmpty();
	}

	@Override
	public NonNullList<Ingredient> getIngredients () {
		return this.input;
	}

	@Override
	public String getGroup () {
		return "";
	}

	@Override
	public boolean canFit (final int width, final int height) {
		return width * height >= this.input.size();
	}
}
