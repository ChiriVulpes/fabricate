
declare module "artisan-worktables" {
	import { List } from "collections";
	import { ItemStack } from "recipe";
	import { ResourceLocation } from "utility";
	import { RecipeRegistry, IArtisanRecipe } from "artisan-worktables/recipe";
	import { IRequirementContext, IRequirement, IRequirementBuilder } from "artisan-worktables/recipe/requirement";

	export module ArtisanAPI {

		export function MOD_ID (): string;

		export const WORKTABLE_NAME_LIST: List<string>;

		/**
		 * @return unmodifiable list of worktable names
		 */
		export function getWorktableNames (): List<string>;

		/**
		 * @param name the worktable name
		 * @return true if the worktable name is valid
		 */
		export function isWorktableNameValid (name: string): boolean;

		/**
		 * @param recipeName the recipe name
		 * @return the recipe with the given name
		 */
		export function getRecipe (recipeName: string): IArtisanRecipe | null;

		/**
		 * Throws an `IllegalStateException` if there is no `RecipeRegistry`
		 * for the given table name.
		 *
		 * @param tableName the table name
		 * @return the recipe registry for the given table name
		 */
		export function getWorktableRecipeRegistry (tableName: string): RecipeRegistry;

		/**
		 * @param itemStack the tool item stack
		 * @return true if any recipe registry contains a recipe that uses the given tool
		 */
		export function containsRecipeWithTool (itemStack: ItemStack): boolean;

		export function getRequirementContext (key: ResourceLocation): IRequirementContext;

		export function getRequirementBuilder<C extends IRequirementContext, R extends IRequirement<C>> (key: ResourceLocation): IRequirementBuilder<C, R>;
	}
}