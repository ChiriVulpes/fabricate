declare module "recipe" {

	export class IRecipe { }

	export class Recipe {
		constructor(recipe: IRecipe);
		getRecipe (): IRecipe;
	}
}