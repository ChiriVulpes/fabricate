declare module "fabricate" {
	import { IItemStack, IRecipeBuilder, Ingredient, Recipe } from "recipe";

	export type IResult = string | IItemStack;

	export class RecipeRegistry {
		addShaped (initializer: (builder: IRecipeBuilder<Ingredient[]>) => IRecipeBuilder<Ingredient[]>): RecipeRegistry;
		addShaped (recipeName: string, initializer: (builder: IRecipeBuilder<Ingredient[]>) => IRecipeBuilder<Ingredient[]>): RecipeRegistry;
		addShapeless (initializer: (builder: IRecipeBuilder<Ingredient>) => IRecipeBuilder<Ingredient>): RecipeRegistry;
		addShapeless (recipeName: string, initializer: (builder: IRecipeBuilder<Ingredient>) => IRecipeBuilder<Ingredient>): RecipeRegistry;
		createBlank (): Recipe;
		query (): IRecipeQuery;
	}
}