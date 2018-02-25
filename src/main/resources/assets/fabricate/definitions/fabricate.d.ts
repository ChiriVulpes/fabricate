declare module "fabricate" {
	export enum RegistryEvent {
		Recipes = 0,
		RegisteredRecipe = 1,
	}

	export module Fabricate {
		/**
		 * Register an event handler for recipe registration
		 */
		export function on (event: RegistryEvent.Recipes, handler: (recipeManager: RecipeRegistry) => void): void;
		/**
		 * This event is emitted for each registered recipe, after `RegistryEvent.Recipes`.
		 */
		export function on (event: RegistryEvent.RegisteredRecipe, handler: (recipe: IRecipe) => void): void;

		/**
		 * Gets a stack from a string. Used internally when passing strings to Fabricate methods.
		 */
		export function stack (stack: string): IStack;
		/**
		 * Gets a stack from a string. Used internally when passing strings to Fabricate methods.
		 */
		export function stack (stack: string, count: number): IStack;
	}

	export interface IStack {

	}

	export type IIngredientSimple = string | IStack;
	export type IIngredient = IIngredientSimple | IIngredientSimple[] | null;


	export type IResult = string | IStack;

	export interface IResourceLocation {
		getResourceDomain (): string;
		getResourcePath (): string;
		toString (): string;
	}

	export interface IRecipe {
		getRegistryName (): IResourceLocation;
		setRegistryName (name: string): void;
	}

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
		 * Filter the query
		 */
		filter (predicate: (recipe: IRecipe) => boolean): this;

		/**
		 * Call a handler for all recipes that match the filters. Return `true` to continue, and `false` to break.
		 */
		forEach (handler: (recipe: IRecipe) => boolean): void;

		/**
		 * Call a handler for the first recipe that matches the filters
		 */
		forFirst (handler: (recipe: IRecipe) => void): void;

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
		replaceAll (handler: (toReplace: IRecipe) => IRecipe): void;

		/**
		 * Replaces the first recipe that matches the filters
		 */
		replaceFirst (handler: (toReplace: IRecipe) => IRecipe): void;
	}

	export class RecipeRegistry {

		///////////////////////////////////
		// CREATION
		//

		/**
		 * Create a shaped recipe
		 */
		createShaped (result: IResult, recipe: IIngredient[][]): IRecipe;

		/**
		 * Create a shapeless recipe
		 */
		createShapeless (result: IResult, recipe: IIngredient[]): IRecipe;

		/**
		 * Creates an empty recipe
		 */
		createEmpty (): IRecipe;


		///////////////////////////////////
		// ADDING
		//

		/**
		 * Add the given recipe
		 */
		add (recipe: IRecipe): void;

		/**
		 * Add a shaped recipe
		 */
		addShaped (result: IResult, recipe: IIngredient[][]): void;

		/**
		 * Add a shapeless recipe
		 */
		addShapeless (result: IResult, recipe: IIngredient[]): void;


		///////////////////////////////////
		// QUERYING
		//

		/**
		 * Filter all recipes with the given output
		 */
		withOutput (ingredient: IIngredient): IRecipeQuery;

		/**
		 * Filter all recipes by name
		 */
		byName (name: string): IRecipeQuery;

		/**
		 * Filter all recipes by a predicate
		 */
		filter (predicate: (recipe: IRecipe) => boolean): IRecipeQuery;
	}
}