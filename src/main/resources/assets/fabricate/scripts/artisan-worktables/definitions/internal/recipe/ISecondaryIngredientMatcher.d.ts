declare module "artisan-worktables/recipe" {
	import { Collection } from "collections";

	export interface ISecondaryIngredientMatcher {
		matches (requiredIngredients: Collection<IArtisanIngredient>): boolean;
	}

	export module ISecondaryIngredientMatcher {
		export const FALSE: () => false;
	}
}