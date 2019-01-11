declare module "artisan-worktables/recipe" {
	import { IIngredient, ItemStack, Ingredient } from "recipe";

	export class ArtisanIngredient implements IArtisanIngredient {
		public static from (ingredient: IIngredient | null): ArtisanIngredient;
		isEmpty (): boolean;
		getAmount (): int;
		matches (stack: IArtisanItemStack): boolean;
		matches (stack: IArtisanItemStack): boolean;
		matchesIgnoreAmount (stack: ItemStack): boolean;
		matchesIgnoreAmount (stack: ItemStack): boolean;
		getMatchingStacks (): IArtisanItemStack[];
		toIngredient (): Ingredient;
	}
}