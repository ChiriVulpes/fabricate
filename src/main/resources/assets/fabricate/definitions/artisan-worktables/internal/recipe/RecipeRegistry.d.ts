declare module "artisan-worktables/recipe" {
	import { List, Map } from "collections";
	import { ItemStack, FluidStack } from "recipe";
	import { ResourceLocation } from "utility";
	import { IRequirementContext } from "artisan-worktables/recipe/requirement";
	import { Tier } from "artisan-worktables/reference";

	export class RecipeRegistry {
		getRecipeListByTier (tier: Tier, result: List<IArtisanRecipe>): List<IArtisanRecipe>;
		addRecipe (recipe: IArtisanRecipe): IArtisanRecipe | null;
		hasRecipe (name: string): boolean;
		getRecipe (name: string): IArtisanRecipe | null;
		findRecipe (
			playerExperience: number,
			playerLevels: number,
			isPlayerCreative: boolean,
			tools: JArray<ItemStack>,
			craftingMatrix: ICraftingMatrixStackHandler,
			fluidStack: FluidStack | null,
			secondaryIngredientMatcher: ISecondaryIngredientMatcher,
			tier: Tier,
			requirementContextMap: Map<ResourceLocation, IRequirementContext>
		): IArtisanRecipe | null;
		containsRecipeWithToolInSlot (tool: ItemStack, toolIndex: number): boolean;
		containsRecipeWithToolInAnySlot (tool: ItemStack): boolean;
	}
}