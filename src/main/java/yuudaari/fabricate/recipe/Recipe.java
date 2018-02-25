package yuudaari.fabricate.recipe;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

public abstract class Recipe implements IRecipe {

	protected ResourceLocation name;

	public Recipe () {}

	public Recipe (final ResourceLocation name) {
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
}
