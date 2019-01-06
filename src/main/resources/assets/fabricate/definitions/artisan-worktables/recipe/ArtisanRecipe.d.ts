
declare module "artisan-worktables/recipe" {
	import { Random } from "random";
	import { FluidStack, ItemStack } from "recipe";
	import { List, Map } from "collections";
	import { IResourceLocation } from "utility";
	import { Tier } from "artisan-worktables/reference";
	import { ICraftingContext } from "artisan-worktables/recipe";
	import { IRequirement, IRequirementContext } from "artisan-worktables/recipe/requirement";

	export class ArtisanRecipe implements IArtisanRecipe {
		constructor(
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
		);

		getName (): string;
		getExperienceRequired (): int;
		getLevelRequired (): int;
		consumeExperience (): boolean;
		getSecondaryOutput (): IArtisanItemStack;
		getSecondaryOutputChance (): float;
		getTertiaryOutput (): IArtisanItemStack;
		getTertiaryOutputChance (): float;
		getQuaternaryOutput (): IArtisanItemStack;
		getQuaternaryOutputChance (): float;
		getSecondaryIngredients (): List<IArtisanIngredient>;
		consumeSecondaryIngredients (): boolean;
		getTools (toolIndex: int): IArtisanItemStack[];
		getIngredientList (): List<IArtisanIngredient>;
		getFluidIngredient (): FluidStack | null;
		getOutputWeightPairList (): List<OutputWeightPair>;
		getBaseOutput (context: ICraftingContext): IArtisanItemStack;
		hasMultipleWeightedOutputs (): boolean;
		getToolDamage (toolIndex: int): int;
		getWidth (): int;
		getHeight (): int;
		isShaped (): boolean;
		isMirrored (): boolean;
		getToolCount (): int;
		getRequirement (resourceLocation: IResourceLocation): IRequirement;
		getRequirements (): Map<IResourceLocation, IRequirement>;
		isHidden (): boolean;
		isValidTool (tool: ItemStack, toolIndex: int): boolean;
		hasSufficientToolDurability (tool: ItemStack, toolIndex: int): boolean;
		selectOutput (context: ICraftingContext, random: Random): IArtisanItemStack;
		matches (
			requirementContextMap: Map<IResourceLocation, IRequirementContext>,
			playerExperienceTotal: int,
			playerLevels: int,
			isPlayerCreative: boolean,
			tools: ItemStack[],
			craftingMatrix: ICraftingMatrixStackHandler,
			fluidStack: FluidStack,
			secondaryIngredientMatcher: ISecondaryIngredientMatcher,
			tier: Tier
		): boolean;
		matchesRequirements (requirementContextMap: Map<IResourceLocation, IRequirementContext>): boolean;
		matchTier (tier: Tier): boolean;
		doCraft (context: ICraftingContext): void;
	}
}