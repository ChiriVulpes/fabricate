declare module "artisan-worktables" {
	import { FluidStack } from "recipe";
	import { IArtisanIngredient, IArtisanItemStack, IRecipeFactory, IRecipeBuilderCopyStrategy } from "artisan-worktables/recipe";
	import { IRequirementBuilder } from "artisan-worktables/recipe/requirement";

	export const enum ExtraOutputSlot {
		One,
		Two,
		Three
	}

	export interface IRecipeBuilder {
		/**
		 * Sets the name of this recipe
		 */
		setName (name: string): this;
		/**
		 * Sets the ingredients (**shaped**).
		 */
		setIngredients (ingredients: IArtisanIngredient[][]): this;
		/**
		 * Sets the ingredients (**shapeless**).
		 */
		setIngredients (ingredients: IArtisanIngredient[]): this;
		/**
		 * Sets a fluid ingredient requirement.
		 */
		setFluidIngredient (fluid: FluidStack): this;
		/**
		 * Sets secondary ingredient requirements.
		 */
		setSecondaryIngredients (ingredients: IArtisanIngredient[]): this;
		/**
		 * Sets whether the secondary ingredients should be consumed.
		 */
		setConsumeSecondaryIngredients (consume: boolean): this;
		/**
		 * If this is a **shaped** recipe, calling this method allows the recipe to be mirrored horizontally.
		 */
		setMirrored (): this;
		/**
		 * Adds a tool requirement.
		 * @param tool An ingredient representing the required tool. 
		 * @param damage The damage that will be applied to the tool on craft.
		 */
		addTool (tool: IArtisanIngredient, damage: number): this;
		/**
		 * Adds an output.
		 * @param output The item stack to output.
		 * @param weight The weight of using this stack vs a different output.
		 */
		addOutput (output: IArtisanItemStack, weight: number): this;
		/**
		 * Sets an extra output.
		 * @param index This output's slot.
		 * @param output The stack that should output.
		 * @param chance The chance of this item being outputted on craft.
		 */
		setExtraOutput (index: ExtraOutputSlot, output: IArtisanItemStack, chance: number): this;
		/**
		 * Sets the minimum worktable tier.
		 * @param tier The tier of the worktable.
		 * Example: `.setMinimumTier(Tier.WORKSTATION.getId())`
		 */
		setMinimumTier (tier: number): this;
		/**
		 * Sets the maximum worktable tier.
		 * @param tier The tier of the worktable.
		 * Example: `.setMaximumTier(Tier.WORKSTATION.getId())`
		 */
		setMaximumTier (tier: number): this;
		/**
		 * Sets the experience required.
		 */
		setExperienceRequired (cost: number): this;
		/**
		 * Sets the level required.
		 */
		setLevelRequired (cost: number): this;
		/**
		 * Sets whether the required experience will be consumed.
		 */
		setConsumeExperience (consumeExperience: boolean): this;
		/**
		 * Sets this recipe to be copied from another recipe.
		 * @param copyStrategy The strategy to find another recipe & what to copy from the recipe.
		 * Example: `.setCopy(ArtisanBuilder.Copy.byName("minecraft:furnace"))`
		 */
		setCopy (copyStrategy: IRecipeBuilderCopyStrategy): this;
		/**
		 * Adds a requirement.
		 * @param matchRequirementBuilder 
		 */
		addRequirement (matchRequirementBuilder: IRequirementBuilder): this;
		/**
		 * Sets whether this recipe is hidden.
		 */
		setHidden (hidden: boolean): this;
		/**
		 * Registers the recipe.
		 */
		create (): this;
	}

	export module ArtisanBuilder {
		/**
		 * @param tableName The type of table this builder is for
		 * @returns A new recipe builder
		 */
		export function get (tableName: string): IRecipeBuilder;
		// /**
		//  * @param tableName The type of table this builder is for
		//  * @param factory ?
		//  * @returns A new recipe builder
		//  */
		// export function get (tableName: string, factory: IRecipeFactory): IRecipeBuilder;

		export module Copy {
			export function byName (recipeName: string): IRecipeBuilderCopyStrategy;
			export function byOutput (outputs: IArtisanIngredient[]): IRecipeBuilderCopyStrategy;
			// we can't use `byRecipe` because it's specifically for crafttweaker
			// export function byRecipe (recipe: ICraftingRecipe): IRecipeBuilderCopyStrategy;
		}
	}
}