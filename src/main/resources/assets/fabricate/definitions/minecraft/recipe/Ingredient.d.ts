declare module "recipe" {

	export class IIngredient { }

	export class Ingredient {
		getIngredient (): IIngredient;
	}
}