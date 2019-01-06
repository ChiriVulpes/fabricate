declare module "artisan-worktables/recipe" {
	import { IIngredient } from "recipe";

	export class ArtisanIngredient {
		public static from (ingredient: IIngredient | null): ArtisanIngredient;
	}
}