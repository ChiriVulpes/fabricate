declare module "fabricate" {
	import { ResourceLocation } from "utility";
	import { ItemStack, Recipe, IItemStack, Ingredient, IIngredient } from "recipe";

	export const enum RegistryEvent {
		Recipes = "Recipes",
		RegisteredRecipe = "RegisteredRecipe",
	}

	export type ParseableIngredientOutput = string | IItemStack | ItemStack;
	export type ParseableIngredientInput = ParseableIngredientOutput | ParseableIngredientOutput[] | IIngredient | Ingredient | null;

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
		 * Creates a stack.
		 */
		export function stack (stack: ParseableIngredientOutput): ItemStack;
		/**
		 * Creates a stack.
		 */
		export function stack (stack: ParseableIngredientOutput, count: number): ItemStack;

		/**
		 * Creates an ingredient.
		 */
		export function ingredient (...ingredients: ParseableIngredientInput[]): Ingredient;
	}
}