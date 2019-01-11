declare module "artisan-worktables/recipe" {
	import { ParseableIngredientInput, ParseableIngredientOutput } from "fabricate";

	export interface IRecipeBuilderCopyStrategy {
		noInput (): this;
		noOutput (): this;
		replaceInput (toReplace: ParseableIngredientInput | null, replacement: ParseableIngredientInput | null): this;
		replaceShapedInput (col: int, row: int, replacement: ParseableIngredientInput | null): this;
		replaceOutput (replacement: ParseableIngredientOutput): this;
	}
}