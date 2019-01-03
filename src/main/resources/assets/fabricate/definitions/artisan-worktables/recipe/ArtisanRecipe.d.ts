
declare module "artisan-worktables/recipe" {
	import { Random } from "random";
	import { FluidStack, ItemStack } from "recipe";
	import { List, Map } from "collections";
	import { ResourceLocation } from "utility";
	import { Tier } from "artisan-worktables/reference";
	import { ICraftingContext } from "artisan-worktables/recipe";
	import { IRequirement, IRequirementContext } from "artisan-worktables/recipe/requirement";

	export class ArtisanRecipe implements IArtisanRecipe {
		constructor(
			name: string | null,
			requirementMap: Map<ResourceLocation, IRequirement>,
			output: List<OutputWeightPair>,
			tools: JArray<ToolEntry>,
			ingredients: List<IArtisanIngredient>,
			secondaryIngredients: List<IArtisanIngredient>,
			consumeSecondaryIngredients: boolean,
			fluidIngredient: FluidStack | null,
			experienceRequired: int,
			levelRequired: int,
			consumeExperience: boolean,
			extraOutputs: JArray<ExtraOutputChancePair>,
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
		getTools (toolIndex: int): JArray<IArtisanItemStack>;
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
		getRequirement (resourceLocation: ResourceLocation): IRequirement;
		getRequirements (): Map<ResourceLocation, IRequirement>;
		isHidden (): boolean;
		isValidTool (tool: ItemStack, toolIndex: ItemStack): boolean;
		hasSufficientToolDurability (tool: ItemStack, toolIndex: int): boolean;
		selectOutput (context: ICraftingContext, random: Random): IArtisanItemStack;
		matches (
			requirementContextMap: Map<ResourceLocation, IRequirementContext>,
			playerExperienceTotal: int,
			playerLevels: int,
			isPlayerCreative: boolean,
			tools: JArray<ItemStack>,
			craftingMatrix: ICraftingMatrixStackHandler,
			fluidStack: FluidStack,
			secondaryIngredientMatcher: ISecondaryIngredientMatcher,
			tier: Tier
		): boolean;
		matchesRequirements (requirementContextMap: Map<ResourceLocation, IRequirementContext>): boolean;
		matchTier (tier: Tier): boolean;
		doCraft (context: ICraftingContext): void;
	}
}