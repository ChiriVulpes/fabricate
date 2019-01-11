import { ParseableIngredientInput, Fabricate, ParseableIngredientOutput } from "fabricate";
import { ArtisanIngredient, ArtisanItemStack } from "artisan-worktables/recipe";

declare global {
	function artisanIngredient (...ingredients: ParseableIngredientInput[]): ArtisanIngredient;
	function artisanItemStack (stack: ParseableIngredientOutput): ArtisanItemStack;
}

export function artisanIngredient (...ingredients: ParseableIngredientInput[]) {
	return ArtisanIngredient.from(Fabricate.ingredient(...ingredients).getIngredient());
}

export function artisanItemStack (stack: ParseableIngredientOutput) {
	return ArtisanItemStack.from(Fabricate.stack(stack).getStack());
}