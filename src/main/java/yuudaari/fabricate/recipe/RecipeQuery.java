package yuudaari.fabricate.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import yuudaari.fabricate.api.IRecipeQuery;
import yuudaari.fabricate.util.Llog;

public class RecipeQuery implements IRecipeQuery {

	private final IForgeRegistryModifiable<IRecipe> REGISTRY;
	private final List<Function<IRecipe, Boolean>> PREDICATES = new ArrayList<>();

	public RecipeQuery (final IForgeRegistryModifiable<IRecipe> registry, final Function<IRecipe, Boolean> predicate) {
		REGISTRY = registry;
		PREDICATES.add(predicate);
	}

	/**
	 * Further filter the remaining recipes
	 */
	@Override
	public IRecipeQuery filter (Function<IRecipe, Boolean> predicate) {
		PREDICATES.add(predicate);
		return this;
	}

	/**
	 * Get all recipes that match the filters
	 */
	@Override
	public IRecipe[] getAll () {
		final List<IRecipe> matches = new ArrayList<>();

		TOP:
		for (final IRecipe recipe : REGISTRY.getValues()) {
			for (final Function<IRecipe, Boolean> predicate : PREDICATES) {
				if (!predicate.apply(recipe)) continue TOP;
			}

			matches.add(recipe);
		}

		return matches.toArray(new IRecipe[0]);
	}

	/**
	 * Get the first recipe that matches the filters
	 */
	@Override
	public IRecipe getFirst () {
		TOP:
		for (final IRecipe recipe : REGISTRY.getValues()) {
			for (final Function<IRecipe, Boolean> predicate : PREDICATES) {
				if (!predicate.apply(recipe)) continue TOP;
			}

			return recipe;
		}

		return null;
	}

	/**
	 * Remove all recipes that match the filters
	 */
	@Override
	public void removeAll () {
		final List<ResourceLocation> toRemove = new ArrayList<>();

		TOP:
		for (final Entry<ResourceLocation, IRecipe> recipe : REGISTRY.getEntries()) {
			for (final Function<IRecipe, Boolean> predicate : PREDICATES) {
				if (!predicate.apply(recipe.getValue())) continue TOP;
			}

			toRemove.add(recipe.getKey());
		}

		for (final ResourceLocation recipeName : toRemove) {
			Llog.info("removing " + recipeName);
			REGISTRY.remove(recipeName);
		}
	}

	/**
	 * Remove the first recipe that matches the filters, then return
	 */
	@Override
	public void removeFirst () {
		TOP:
		for (final Entry<ResourceLocation, IRecipe> recipe : REGISTRY.getEntries()) {
			for (final Function<IRecipe, Boolean> predicate : PREDICATES) {
				if (!predicate.apply(recipe.getValue())) continue TOP;
			}

			REGISTRY.remove(recipe.getKey());
		}
	}
}
