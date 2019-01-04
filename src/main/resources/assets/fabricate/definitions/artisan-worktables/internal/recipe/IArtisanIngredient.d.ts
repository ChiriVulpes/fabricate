declare module "artisan-worktables/recipe" {
	import { Item, ItemStack, Ingredient } from "recipe";

	export interface IArtisanIngredient {
		isEmpty (): boolean;
		getAmount (): boolean;
		matches (stack: IArtisanItemStack): boolean;
		matches (stack: IArtisanItemStack): boolean;
		matchesIgnoreAmount (stack: ItemStack): boolean;
		matchesIgnoreAmount (stack: ItemStack): boolean;
		getMatchingStacks (): IArtisanItemStack[];
		toIngredient (): Ingredient;
	}
}
