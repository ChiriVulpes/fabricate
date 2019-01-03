
declare module "artisan-worktables/recipe" {
	import { IItemHandler, IItemHandlerModifiable, IFluidHandler } from "recipe";
	import { BlockPos } from "math";
	import { Type, Tier } from "artisan-worktables/reference";

	export interface ICraftingContext {
		getWorld (): World;
		getPlayer (): Player;
		getCraftingMatrixHandler (): ICraftingMatrixStackHandler;
		getToolHandler (): IItemHandlerModifiable;
		getSecondaryOutputHandler (): IItemHandler;
		getSecondaryIngredientHandler (): IItemHandlerModifiable | null;
		getFluidHandler (): IFluidHandler;
		getToolReplacementHandler (): IItemHandler | null;
		getType (): Type;
		getTier (): Tier;
		getPosition (): BlockPos;
	}
}