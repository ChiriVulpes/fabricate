
declare module "artisan-worktables/recipe" {
	import { Random } from "random";
	import { Map, List } from "collections";
	import { IResourceLocation } from "utility";
	import { ItemStack, FluidStack } from "recipe";
	import { Tier } from "artisan-worktables/reference";
	import { IRequirementContext, IRequirement } from "artisan-worktables/recipe/requirement";

	export interface IArtisanRecipe {

		/**
		 * @return this recipe's name
		 */
		getName (): string;

		/**
		 * Return true if this recipe matches the given criteria.
		 *
		 * @param requirementContextMap      the requirement context map
		 * @param playerExperienceTotal      the player's total experience
		 * @param playerLevels               the player's total level count
		 * @param isPlayerCreative           is the player in creative mode?
		 * @param tools                      the tools in the crafting GUI
		 * @param craftingMatrix             the crafting matrix stack handler
		 * @param fluidStack                 the fluid in the table
		 * @param secondaryIngredientMatcher the secondary ingredient matcher
		 * @param tier                       the table tier
		 * @return true if this recipe matches the given criteria
		 */
		matches (
			requirementContextMap: Map<IResourceLocation, IRequirementContext>,
			playerExperienceTotal: int,
			playerLevels: int,
			isPlayerCreative: boolean,
			tools: ItemStack[],
			craftingMatrix: ICraftingMatrixStackHandler,
			fluidStack: FluidStack | null,
			secondaryIngredientMatcher: ISecondaryIngredientMatcher,
			tier: Tier
		): boolean;

		/**
		 * @param requirementContextMap requirement contexts
		 * @return true if the recipe's requirements match the given context
		 */
		matchesRequirements (requirementContextMap: Map<IResourceLocation, IRequirementContext>): boolean;

		/**
		 * @param resourceLocation requirement resource location
		 * @return the recipe's requirement for the given resource location or null if none exists
		 */
		getRequirement (resourceLocation: IResourceLocation): IRequirement | null;

		/**
		 * @return a read-only view of this recipe's requirements
		 */
		getRequirements (): Map<IResourceLocation, IRequirement>;

		/**
		 * @param tool      the tool to check
		 * @param toolIndex the tool index
		 * @return true if the given tool is valid for this recipe in the given slot
		 */
		isValidTool (tool: ItemStack, toolIndex: int): boolean;

		/**
		 * @param tool      the tool to check
		 * @param toolIndex the tool index
		 * @return true if the given tool has sufficient durability to complete the craft
		 */
		hasSufficientToolDurability (tool: ItemStack, toolIndex: int): boolean;

		/**
		 * @param tier the tier to match
		 * @return true if this recipe can be crafted in the given tier
		 */
		matchTier (tier: Tier): boolean;

		/**
		 * @return the width of this recipe if shaped, otherwise 0
		 */
		getWidth (): int;

		/**
		 * @return the height of this recipe if shaped, otherwise 0
		 */
		getHeight (): int;

		/**
		 * @return true if this recipe is shaped
		 */
		isShaped (): boolean;

		/**
		 * @return true if this recipe is shaped and mirrored
		 */
		isMirrored (): boolean;

		/**
		 * @return a copy of the secondary output
		 */
		getSecondaryOutput (): IArtisanItemStack;

		/**
		 * @return secondary output chance, [0,1]
		 */
		getSecondaryOutputChance (): float;

		/**
		 * @return a copy of the tertiary output
		 */
		getTertiaryOutput (): IArtisanItemStack;

		/**
		 * @return tertiary output chance, [0,1]
		 */
		getTertiaryOutputChance (): float;

		/**
		 * @return a copy of the quaternary output
		 */
		getQuaternaryOutput (): IArtisanItemStack;

		/**
		 * @return quaternary output chance, [0,1]
		 */
		getQuaternaryOutputChance (): float;

		/**
		 * @return an immutable list of secondary ingredients
		 */
		getSecondaryIngredients (): List<IArtisanIngredient>;

		/**
		 * @return true if this recipe should consume the required secondary ingredients
		 */
		consumeSecondaryIngredients (): boolean;

		/**
		 * @param toolIndex the tool slot index [0,2]
		 * @return the array of acceptable tools for the given tool slot index
		 */
		getTools (toolIndex: int): IArtisanItemStack[];

		/**
		 * @param toolIndex the tool slot index [0,2]
		 * @return the amount of tool damage to be applied to the tool in the given slot index
		 */
		getToolDamage (toolIndex: int): int;

		/**
		 * @return the number of tools required for this recipe
		 */
		getToolCount (): int;

		/**
		 * @return an immutable list of the recipe's ingredients
		 */
		getIngredientList (): List<IArtisanIngredient>;

		/**
		 * @return a copy of the fluid requirement, or null if there isn't one
		 */
		getFluidIngredient (): FluidStack | null;

		/**
		 * @return the output weight pair list
		 */
		getOutputWeightPairList (): List<OutputWeightPair>;

		/**
		 * Selects and returns a random output from the list of weighted outputs.
		 *
		 * @param context the crafting context
		 * @param random  random
		 * @return the selected output
		 */
		selectOutput (context: ICraftingContext, random: Random): IArtisanItemStack;

		/**
		 * Returns the first, or base, output of the recipe. If multiple weighted
		 * outputs are available for this recipe, this is the output that is
		 * displayed to the player in the crafting GUI.
		 *
		 * @param context the crafting context
		 * @return the base output
		 */
		getBaseOutput (context: ICraftingContext): IArtisanItemStack;

		/**
		 * @return true if this recipe has multiple outputs
		 */
		hasMultipleWeightedOutputs (): boolean;

		/**
		 * @return the amount of experience required to craft this recipe
		 */
		getExperienceRequired (): int;

		/**
		 * @return the number of levels required to craft this recipe
		 */
		getLevelRequired (): int;

		/**
		 * @return true if crafting this recipe should consume the required experience
		 */
		consumeExperience (): boolean;

		/**
		 * @return true if the recipe should be hidden by default in JEI
		 */
		isHidden (): boolean;

		/**
		 * Perform the craft.
		 *
		 * @param context the crafting context
		 */
		doCraft (context: ICraftingContext): void;
	}
}