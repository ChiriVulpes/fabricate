declare module "artisan-worktables/recipe" {
	import { IItemStack, Item } from "recipe";

	export class ArtisanItemStack implements IArtisanItemStack {
		public static from (stack: IItemStack | null): ArtisanIngredient;
		getAmount (): int;
		getItem (): Item;
		isEmpty (): boolean;
		toItemStack (): IItemStack;
		copy (): IArtisanItemStack;
	}
}