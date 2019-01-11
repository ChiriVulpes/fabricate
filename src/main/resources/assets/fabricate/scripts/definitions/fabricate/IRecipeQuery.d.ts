import { ParseableIngredientInput, Recipe, IRecipe } from "recipe";

declare module "fabricate" {
	export interface IRecipeQuery {

		/**
		 * Sets the query to resolve immediately. 
		 * By default queries are all processed at once, after all scripts are finished. 
		 * Each query which runs immediately requires a loop over all existing recipes.
		 * When a lot of recipes need to be removed or replaced, it's not recommended
		 * to use immediate mode to replace them.
		 */
		setImmediate (): this;

		/**
		 * Filter all recipes with the given output
		 */
		filterByOutput (ingredient: ParseableIngredientInput): this;

		/**
		 * Filter all recipes by name
		 */
		filterByName (name: string): this;

		/**
		 * Filter the query
		 */
		filter (predicate: (recipe: Recipe) => boolean): this;

		/**
		 * Call a handler for all recipes that match the filters. Return `true` to continue, and `false` to break.
		 */
		forEach (handler: (recipe: Recipe) => boolean): void;

		/**
		 * Call a handler for the first recipe that matches the filters
		 */
		forFirst (handler: (recipe: Recipe) => void): void;

		/**
		 * Remove all recipes that match the filters
		 */
		removeAll (): void;

		/**
		 * Remove the first recipe that matches the filters, then return
		 */
		removeFirst (): void;

		/**
		 * Replaces all recipes that match the filters
		 */
		replaceAll (handler: (toReplace: Recipe) => Recipe | IRecipe): void;

		/**
		 * Replaces the first recipe that matches the filters
		 */
		replaceFirst (handler: (toReplace: Recipe) => Recipe | IRecipe): void;
	}
}