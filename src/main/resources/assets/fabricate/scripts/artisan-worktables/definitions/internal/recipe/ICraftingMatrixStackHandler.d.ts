
declare module "artisan-worktables/recipe" {
	import { IItemHandlerModifiable } from "recipe";

	export interface ICraftingMatrixStackHandler extends IItemHandlerModifiable {
		getWidth (): number;
		getHeight (): number;
	}
}