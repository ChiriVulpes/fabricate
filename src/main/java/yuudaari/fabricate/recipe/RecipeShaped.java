package yuudaari.fabricate.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipeShaped extends ShapedOreRecipe {

	public RecipeShaped (final Block result, final Object... recipe) {
		this(new ItemStack(result), recipe);
	}

	public RecipeShaped (final Item result, final Object... recipe) {
		this(new ItemStack(result), recipe);
	}

	public RecipeShaped (final ItemStack result, final Object... recipe) {
		this(result, CraftingHelper.parseShaped(recipe));
	}

	public RecipeShaped (final ItemStack result, final ShapedPrimer primer) {
		super(null, result, primer);
	}

	@Override
	public boolean canFit (final int width, final int height) {
		return width >= this.width && height >= this.height;
	}

	@Override
	public boolean matches (final InventoryCrafting inv, final World world) {
		for (int x = 0; x <= inv.getWidth() - width; x++) {
			for (int y = 0; y <= inv.getHeight() - height; ++y) {
				if (checkMatch(inv, x, y, false)) {
					return true;
				}

				if (mirrored && checkMatch(inv, x, y, true)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if the recipe matches at a specific position
	 */
	protected boolean checkMatch (final InventoryCrafting inv, final int startX, final int startY, final boolean mirror) {
		for (int x = 0; x < inv.getWidth(); x++) {
			for (int y = 0; y < inv.getHeight(); y++) {
				final int subX = x - startX;
				final int subY = y - startY;
				Ingredient target = Ingredient.EMPTY;

				if (subX >= 0 && subY >= 0 && subX < width && subY < height) {
					if (mirror) {
						target = input.get(width - subX - 1 + subY * width);
					} else {
						target = input.get(subX + subY * width);
					}
				}

				if (!target.apply(inv.getStackInRowAndColumn(x, y))) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public ItemStack getCraftingResult (final InventoryCrafting var1) {
		return output.copy();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems (InventoryCrafting inv) {
		return super.getRemainingItems(inv);
	}
}
