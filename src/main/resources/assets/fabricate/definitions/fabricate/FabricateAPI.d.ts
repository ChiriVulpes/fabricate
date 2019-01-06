declare module "fabricate" {
	import { ResourceLocation } from "utility";
	import { ItemStack, Recipe, IItemStack, Ingredient } from "recipe";

	export const enum RegistryEvent {
		Recipes = "Recipes",
		RegisteredRecipe = "RegisteredRecipe",
	}

	export type ParseableIngredientOutput = string | IItemStack;
	export type ParseableIngredientInput = ParseableIngredientOutput | ParseableIngredientOutput[] | null;

	export module Fabricate {
		/**
		 * Register an event handler for recipe registration
		 */
		export function on (event: RegistryEvent.Recipes, handler: (recipeManager: RecipeRegistry) => void): void;
		/**
		 * This event is emitted for each registered recipe, after `RegistryEvent.Recipes`.
		 */
		export function on (event: RegistryEvent.RegisteredRecipe, handler: (recipe: Recipe) => void): void;

		/**
		 * Gets a stack from a string. Used internally when passing strings to Fabricate methods.
		 */
		export function stack (stack: string): ItemStack;
		/**
		 * Gets a stack from a string. Used internally when passing strings to Fabricate methods.
		 */
		export function stack (stack: string, count: number): ItemStack;

		export function ingredient (...ingredients: ParseableIngredientInput[]): Ingredient;
	}
}