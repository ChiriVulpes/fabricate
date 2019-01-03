declare module "artisan-worktables/recipe" {
	import { Item, ItemStack } from "recipe";

	export interface IArtisanItemStack {
		getAmount (): int;
		getItem (): Item;
		isEmpty (): boolean;
		/**
		 * Returns a copy of the internal item stack object.
		 * 
		 * Modifications to the returned `ItemStack` should not modify the internal object.
		 *
		 * @return a copy of the internal item stack object
		 */
		toItemStack (): ItemStack;
		copy (): IArtisanItemStack;
	}
}
