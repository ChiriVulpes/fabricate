declare module "recipe" {
	import { ParseableIngredientOutput, ParseableIngredientInput } from "fabricate";

	export interface IRecipeBuilder<I extends Ingredient | Ingredient[]> {
		getName (): string;
		setName (name: string): this;
		getOutput (): ItemStack;
		setOutput (output: ParseableIngredientOutput): this;
		getIngredients (): I[];
		setIngredients (ingredients: I extends Ingredient ? ParseableIngredientInput[] : ParseableIngredientInput[][]): this;
		create (): IRecipe;
	}
}