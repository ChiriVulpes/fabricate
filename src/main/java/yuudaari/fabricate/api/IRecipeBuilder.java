package yuudaari.fabricate.api;

import net.minecraft.item.crafting.IRecipe;
import yuudaari.fabricate.wrappers.recipe.ItemStack;

public interface IRecipeBuilder<I> {

	public String getName ();

	public IRecipeBuilder<I> setName (final String name);

	public ItemStack getOutput ();

	public IRecipeBuilder<I> setOutput (final Object input);

	public I[] getIngredients ();

	public IRecipe create ();
}
