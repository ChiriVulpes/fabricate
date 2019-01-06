package yuudaari.fabricate.recipe;

import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import yuudaari.fabricate.api.IRecipeBuilder;
import yuudaari.fabricate.api.IRecipeRegistry;
import yuudaari.fabricate.wrappers.recipe.Recipe;

public class RecipeRegistry implements IRecipeRegistry {

	private final IForgeRegistryModifiable<IRecipe> REGISTRY;

	public RecipeRegistry (final IForgeRegistry<IRecipe> registry) {
		REGISTRY = (IForgeRegistryModifiable<IRecipe>) registry;
	}

	/**
	 * Begin constructing a shaped recipe
	 */
	public RecipeRegistry addShaped (final Function<RecipeBuilder.Shaped, IRecipeBuilder<?>> initializer) {
		addShaped(null, initializer);
		return this;
	}

	/**
	 * Begin constructing a shaped recipe
	 */
	public RecipeRegistry addShaped (@Nullable final String recipeName, final Function<RecipeBuilder.Shaped, IRecipeBuilder<?>> initializer) {
		IRecipeBuilder<?> recipeBuilder = initializer.apply(new RecipeBuilder.Shaped(recipeName));
		REGISTRY.register(recipeBuilder.create());
		return this;
	}

	/**
	 * Begin constructing a shapeless recipe
	 */
	public RecipeRegistry addShapeless (final Function<RecipeBuilder.Shapeless, IRecipeBuilder<?>> initializer) {
		addShapeless(null, initializer);
		return this;
	}

	/**
	 * Begin constructing a shapeless recipe
	 */
	public RecipeRegistry addShapeless (@Nullable final String recipeName, final Function<RecipeBuilder.Shapeless, IRecipeBuilder<?>> initializer) {
		IRecipeBuilder<?> recipeBuilder = initializer.apply(new RecipeBuilder.Shapeless(recipeName));
		REGISTRY.register(recipeBuilder.create());
		return this;
	}

	public Recipe createBlank () {
		return new Recipe(new RecipeEmpty());
	}

	public RecipeQuery query () {
		return new RecipeQuery(REGISTRY);
	}
}
