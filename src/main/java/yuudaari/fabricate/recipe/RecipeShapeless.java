package yuudaari.fabricate.recipe;

import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeShapeless extends ShapelessOreRecipe {

	public RecipeShapeless (final Block result, final Object... recipe) {
		this(new ItemStack(result), recipe);
	}

	public RecipeShapeless (final Item result, final Object... recipe) {
		this(new ItemStack(result), recipe);
	}

	public RecipeShapeless (final NonNullList<Ingredient> input, final ItemStack result) {
		super(null, input, result);
	}

	public RecipeShapeless (final ItemStack result, final Object... recipe) {
		super(null, result, recipe);
	}

	@Override
	public boolean canFit (final int width, final int height) {
		return width * height >= input.size();
	}

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
	public ItemStack getCraftingResult (final InventoryCrafting var1) {
		return output.copy();
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems (InventoryCrafting inv) {
		return super.getRemainingItems(inv);
	}
}
