package works.chiri.fabricate.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.annotation.Nullable;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import works.chiri.fabricate.api.IRecipeBuilder;
import works.chiri.fabricate.util.Llog;
import works.chiri.fabricate.wrappers.recipe.Ingredient;
import works.chiri.fabricate.wrappers.recipe.ItemStack;

public abstract class RecipeBuilder<I> implements IRecipeBuilder<I> {

	private static int recipeIndex = 0;

	@Nullable protected String name;
	@Nullable protected ItemStack output;
	@Nullable protected I[] ingredients;

	public RecipeBuilder (@Nullable final String name) {
		this.name = name;
	}

	@Nullable
	public String getName () {
		return name;
	}

	public RecipeBuilder<I> setName (final String name) {
		this.name = name;
		return this;
	}

	@Nullable
	public ItemStack getOutput () {
		return this.output;
	}

	public RecipeBuilder<I> setOutput (final Object input) {
		this.output = IngredientHelper.getStacks(input).get(0);
		return this;
	}

	public I[] getIngredients () {
		return ingredients;
	}

	public IRecipe create () {
		if (output == null || output.isEmpty()) {
			Llog.err("Created a recipe with no output.");
			output = ItemStack.EMPTY;
		}

		if (ingredients == null) {
			Llog.err("Created a recipe with no ingredients.");
			ingredients = getDefaultIngredients();
		}

		return build();
	}

	protected abstract I[] getDefaultIngredients ();

	protected abstract IRecipe build ();

	public static class Shaped extends RecipeBuilder<Ingredient[]> {

		public Shaped (@Nullable final String name) {
			super(name);
		}

		public Shaped setIngredients (final Object[][] ingredients) {
			try {
				List<Ingredient[]> result = new ArrayList<>();

				for (final Object[] ingredientRow : (Object[][]) ingredients) {
					final List<Ingredient> resultRow = new ArrayList<>();
					for (final Object ingredient : ingredientRow) {
						resultRow.add(new Ingredient(ingredient));
					}

					result.add(resultRow.toArray(new Ingredient[0]));
				}

				this.ingredients = result.toArray(new Ingredient[0][0]);

			} catch (final Exception e) {
				Llog.err("Tried to create a recipe with invalid ingredients:", ingredients);
				for (Object obj : ingredients) {
					Llog.err(obj);
				}
			}

			return this;
		}

		protected Ingredient[][] getDefaultIngredients () {
			return new Ingredient[][] {
				new Ingredient[] {
					Ingredient.getEmpty(), Ingredient.getEmpty(), Ingredient.getEmpty()
				},
				new Ingredient[] {
					Ingredient.getEmpty(), Ingredient.getEmpty(), Ingredient.getEmpty()
				},
				new Ingredient[] {
					Ingredient.getEmpty(), Ingredient.getEmpty(), Ingredient.getEmpty()
				}
			};
		}

		protected IRecipe build () {
			final Stack<Object> ingredients = new Stack<>();

			int i = 0;
			for (final Ingredient[] ingredientRow : (Ingredient[][]) this.ingredients) {
				String row = "";
				for (final Ingredient ingredient : ingredientRow) {
					final char symbol = ingredient == null ? ' ' : Character.forDigit(i++, 10);
					row += symbol;
					if (symbol == ' ' || ingredient == null) continue;

					ingredients.push(symbol);
					ingredients.push(ingredient.getIngredient());
				}
				ingredients.insertElementAt(row, (i - 1) / 3);
			}

			final IRecipe recipe = new ShapedOreRecipe(null, this.output.getStack(), ingredients.toArray(new Object[0]));
			recipe.setRegistryName(new ResourceLocation("fabricate", "recipe-" + recipeIndex++));

			return recipe;
		}
	}

	public static class Shapeless extends RecipeBuilder<Ingredient> {

		public Shapeless (@Nullable final String name) {
			super(name);
		}

		public Shapeless setIngredients (final Object[] ingredients) {
			try {
				List<Ingredient> result = new ArrayList<>();

				for (final Object ingredient : ingredients) {
					result.add(new Ingredient(ingredient));
				}

				this.ingredients = result.toArray(new Ingredient[0]);
			} catch (final Exception e) {
				Llog.err("Tried to create a recipe with invalid ingredients:", ingredients);
				for (Object obj : ingredients) {
					Llog.err(obj);
				}
			}

			return this;
		}

		@Override
		protected Ingredient[] getDefaultIngredients () {
			return new Ingredient[] {
				Ingredient.getEmpty()
			};
		}

		@Override
		protected IRecipe build () {
			final Object[] ingredients = new Object[this.ingredients.length];
			for (int i = 0; i < this.ingredients.length; i++) {
				ingredients[i] = ((Ingredient) this.ingredients[i]).getIngredient();
			}

			final IRecipe recipe = new ShapelessOreRecipe(null, this.output.getStack(), ingredients);
			recipe.setRegistryName(new ResourceLocation("fabricate", "recipe-" + recipeIndex++));

			return recipe;
		}
	}
}
