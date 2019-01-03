declare module "artisan-worktables/recipe" {
	export interface IRecipeBuilderCopyStrategy {
		noInput (): this;
		noOutput (): this;
		replaceInput (toReplace: IArtisanIngredient | null, replacement: IArtisanIngredient | null): this;
		replaceShapedInput (col: int, row: int, replacement: IArtisanIngredient | null): this;
		replaceOutput (replacement: IArtisanItemStack): this;
	}
}