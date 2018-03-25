package yuudaari.fabricate.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeEmpty implements IRecipe {

	protected ResourceLocation name;

	public RecipeEmpty () {}

	public RecipeEmpty (final ResourceLocation name) {
		setRegistryName(name);
	}

	public IRecipe setRegistryName (final ResourceLocation name) {
		this.name = name;
		return this;
	}

	public ResourceLocation getRegistryName () {
		return name;
	}

	public Class<IRecipe> getRegistryType () {
		return IRecipe.class;
	}

	@Override
	public ItemStack getRecipeOutput () {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean matches (InventoryCrafting inv, World worldIn) {
		return false;
	}

	@Override
	public boolean canFit (int width, int height) {
		return false;
	}

	@Override
	public ItemStack getCraftingResult (InventoryCrafting inv) {
		return ItemStack.EMPTY;
	}
}
