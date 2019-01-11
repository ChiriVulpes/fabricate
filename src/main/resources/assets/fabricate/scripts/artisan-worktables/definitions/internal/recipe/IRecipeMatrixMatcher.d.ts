
declare module "artisan-worktables/recipe" {
	import { FluidStack } from "recipe";

	export interface IRecipeMatrixMatcher {
		matches (recipe: IArtisanRecipe, craftingMatrix: ICraftingMatrixStackHandler, fluidStack: FluidStack | null): boolean;
	}

	export module IRecipeMatrixMatcher {
		export const SHAPED: IRecipeMatrixMatcher;
		export const SHAPELESS: IRecipeMatrixMatcher;
	}
}