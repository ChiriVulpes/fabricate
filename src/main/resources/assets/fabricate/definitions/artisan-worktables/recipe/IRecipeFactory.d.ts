declare module "artisan-worktables/recipe" {
	import { IRequirement } from "artisan-worktables/recipe/requirement";
	import { IResourceLocation } from "utility";
	import { Map, List } from "collections";
	import { OutputWeightPair, IArtisanIngredient } from "artisan-worktables/recipe";
	import { FluidStack } from "recipe";

	export interface IRecipeFactory {
		create (
			name: string | null,
			requirementMap: Map<IResourceLocation, IRequirement>,
			output: List<OutputWeightPair>,
			tools: ToolEntry[],
			ingredients: List<IArtisanIngredient>,
			secondaryIngredients: List<IArtisanIngredient>,
			consumeSecondaryIngredients: boolean,
			fluidIngredient: FluidStack | null,
			experienceRequired: int,
			levelRequired: int,
			consumeExperience: boolean,
			extraOutputs: ExtraOutputChancePair[],
			recipeMatrixMatcher: IRecipeMatrixMatcher,
			mirrored: boolean,
			width: int,
			height: int,
			minimumTier: int,
			maximumTier: int,
			hidden: boolean
		): IArtisanRecipe;
	}

	export module IRecipeFactory {
		export const DEFAULT: () => ArtisanRecipe;
	}
}