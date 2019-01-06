package yuudaari.fabricate.wrappers.recipe;

import net.minecraft.item.crafting.IRecipe;
import yuudaari.fabricate.wrappers.util.ResourceLocation;

public class Recipe {

	private IRecipe recipe;

	public Recipe (final IRecipe recipe) {
		this.recipe = recipe;
	}

	public IRecipe getRecipe () {
		return recipe;
	}

	public ResourceLocation getRegistryName () {
		return new ResourceLocation(recipe.getRegistryName());
	}

	public ItemStack getOutput () {
		return new ItemStack(recipe.getRecipeOutput());
	}
}
