declare module "artisan-worktables" {
	import { FluidStack } from "recipe";
	import { IArtisanIngredient, IArtisanItemStack, IRecipeFactory, IRecipeBuilderCopyStrategy } from "artisan-worktables/recipe";
	import { IRequirementBuilder } from "artisan-worktables/recipe/requirement";

	export interface IRecipeBuilder {
		setName (name: string): this;
		setIngredients (ingredients: JArray<JArray<IArtisanIngredient>> | JArray<IArtisanIngredient>): this;
		setFluidIngredient (fluid: FluidStack): this;
		setSecondaryIngredients (ingredients: JArray<IArtisanIngredient>): this;
		setConsumeSecondaryIngredients (consume: boolean): this;
		setMirrored (): this;
		addTool (tool: IArtisanIngredient, damage: number): this;
		addOutput (output: IArtisanItemStack, weight: number): this;
		setExtraOutput (index: number, output: IArtisanItemStack, chance: number): this;
		setMinimumTier (tier: number): this;
		setMaximumTier (tier: number): this;
		setExperienceRequired (cost: number): this;
		setLevelRequired (cost: number): this;
		setConsumeExperience (consumeExperience: boolean): this;
		setCopy (copyStrategy: IRecipeBuilderCopyStrategy): this;
		addRequirement (matchRequirementBuilder: IRequirementBuilder): this;
		setHidden (hidden: boolean): this;
		create (): this;
	}

	export module RecipeBuilder {
		export function get (tableName: string): IRecipeBuilder;
		export function get (tableName: string, factory: IRecipeFactory): IRecipeBuilder;

		export module Copy {
			export function byName (recipeName: string): IRecipeBuilderCopyStrategy;
			export function byOutput (outputs: JArray<IArtisanIngredient>): IRecipeBuilderCopyStrategy;
			// we can't use `byRecipe` because it's specifically for crafttweaker
			// export function byRecipe (recipe: ICraftingRecipe): IRecipeBuilderCopyStrategy;
		}
	}
}