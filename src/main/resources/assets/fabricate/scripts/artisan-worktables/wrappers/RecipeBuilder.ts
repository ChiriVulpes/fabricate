import { IArtisanBuilder, ExtraOutputSlot } from "artisan-worktables";
import { ParseableIngredientInput, Fabricate, ParseableIngredientOutput } from "fabricate";
import { IArtisanIngredient, ArtisanIngredient, IArtisanItemStack, IRecipeBuilderCopyStrategy } from "artisan-worktables/recipe";
import { FluidStack } from "recipe";
import { IRequirementBuilder } from "artisan-worktables/recipe/requirement";

declare class IArtisanBuilderInternal {
	setName (name: string): this;
	setIngredients (ingredients: IArtisanIngredient[][]): this;
	setIngredients (ingredients: IArtisanIngredient[]): this;
	setFluidIngredient (fluid: FluidStack): this;
	setSecondaryIngredients (ingredients: IArtisanIngredient[]): this;
	setConsumeSecondaryIngredients (consume: boolean): this;
	setMirrored (): this;
	addTool (tool: IArtisanIngredient, damage: number): this;
	addOutput (output: IArtisanItemStack, weight: number): this;
	setExtraOutput (index: ExtraOutputSlot, output: IArtisanItemStack, chance: number): this;
	setMinimumTier (tier: number): this;
	setMaximumTier (tier: number): this;
	setExperienceRequired (cost: number): this;
	setLevelRequired (cost: number): this;
	setConsumeExperience (consumeExperience: boolean): this;
	setCopy (copyStrategy: InternalRecipeBuilderCopyStrategy): this;
	addRequirement (matchRequirementBuilder: IRequirementBuilder): this;
	setHidden (hidden: boolean): this;
	create (): this;
}

declare module IArtisanBuilderInternal {
	export function get (tableName: string): IArtisanBuilderInternal;
	export module Copy {
		export function byName (recipeName: string): InternalRecipeBuilderCopyStrategy;
		export function byOutput (outputs: IArtisanIngredient[]): InternalRecipeBuilderCopyStrategy;
	}
}

declare interface InternalRecipeBuilderCopyStrategy {
	noInput (): this;
	noOutput (): this;
	replaceInput (toReplace: IArtisanIngredient | null, replacement: IArtisanIngredient | null): this;
	replaceShapedInput (col: int, row: int, replacement: IArtisanIngredient | null): this;
	replaceOutput (replacement: IArtisanItemStack): this;
}

const ArtisanBuilderInternal = Java.type<typeof IArtisanBuilderInternal>("com.codetaylor.mc.artisanworktables.api.recipe.RecipeBuilder");

class ArtisanBuilder implements IArtisanBuilder {

	public constructor(private readonly internal: IArtisanBuilderInternal) { }

	public setName (name: string) {
		this.internal.setName(name);
		return this;
	}

	public setShaped (ingredients: ParseableIngredientInput[][]) {
		this.internal.setIngredients(ingredients.map(ingredientRow => ingredientRow.map(artisanIngredient)));
		return this;
	}

	public setShapeless (ingredients: ParseableIngredientInput[]) {
		this.internal.setIngredients(ingredients.map(artisanIngredient));
		return this;
	}

	public setFluidIngredient (fluid: FluidStack) {
		this.internal.setFluidIngredient(fluid)
		return this;
	}

	public setSecondaryIngredients (ingredients: ParseableIngredientInput[]) {
		this.internal.setSecondaryIngredients(ingredients.map(artisanIngredient));
		return this;
	}

	public setConsumeSecondaryIngredients (consume: boolean) {
		this.internal.setConsumeSecondaryIngredients(consume);
		return this;
	}

	public setMirrored () {
		this.internal.setMirrored();
		return this;
	}

	public addTool (tool: ParseableIngredientInput, damage: number) {
		this.internal.addTool(artisanIngredient(tool), damage);
		return this;
	}

	public addOutput (output: ParseableIngredientOutput, weight: number) {
		this.internal.addOutput(artisanItemStack(output), weight);
		return this;
	}

	public setExtraOutput (index: ExtraOutputSlot, output: ParseableIngredientOutput, chance: number) {
		this.internal.setExtraOutput(index, artisanItemStack(output), chance);
		return this;
	}

	public setMinimumTier (tier: number) {
		this.internal.setMinimumTier(tier);
		return this;
	}

	public setMaximumTier (tier: number) {
		this.internal.setMaximumTier(tier);
		return this;
	}

	public setExperienceRequired (cost: number) {
		this.internal.setExperienceRequired(cost);
		return this;
	}

	public setLevelRequired (cost: number) {
		this.internal.setLevelRequired(cost);
		return this;
	}

	public setConsumeExperience (consumeExperience: boolean) {
		this.internal.setConsumeExperience(consumeExperience);
		return this;
	}

	public setCopy (copyStrategy: IRecipeBuilderCopyStrategy) {
		this.internal.setCopy((copyStrategy as RecipeBuilderCopyStrategy).internal);
		return this;
	}

	public addRequirement (matchRequirementBuilder: IRequirementBuilder) {
		// TODO
		return this;
	}

	public setHidden (hidden: boolean) {
		this.internal.setHidden(hidden);
		return this;
	}

	public create () {
		this.internal.create();
		return this;
	}
}

module RecipeBuilder {
	/**
	 * @param tableName The type of table this builder is for
	 * @returns A new recipe builder
	 */
	export function get (tableName: string) {
		return new ArtisanBuilder(ArtisanBuilderInternal.get(tableName));
	}

	// /**
	//  * @param tableName The type of table this builder is for
	//  * @param factory ?
	//  * @returns A new recipe builder
	//  */
	// export function get (tableName: string, factory: IRecipeFactory): IRecipeBuilder;

	export module Copy {
		export function byName (recipeName: string) {
			return new RecipeBuilderCopyStrategy(IArtisanBuilderInternal.Copy.byName(recipeName));
		}

		export function byOutput (...outputs: ParseableIngredientInput[]) {
			return new RecipeBuilderCopyStrategy(IArtisanBuilderInternal.Copy.byOutput([artisanIngredient(...outputs)]));
		}

		// we can't use `byRecipe` because it's specifically for crafttweaker
		// export function byRecipe (recipe: ICraftingRecipe): IRecipeBuilderCopyStrategy;
	}
}

class RecipeBuilderCopyStrategy implements IRecipeBuilderCopyStrategy {
	public constructor(public readonly internal: InternalRecipeBuilderCopyStrategy) { }

	public noInput () {
		this.internal.noInput();
		return this;
	}

	public noOutput () {
		this.internal.noOutput();
		return this;
	}

	public replaceInput (toReplace: ParseableIngredientInput | null, replacement: ParseableIngredientInput | null) {
		this.internal.replaceInput(toReplace === null ? null : artisanIngredient(toReplace), replacement === null ? null : artisanIngredient(replacement));
		return this;
	}

	public replaceShapedInput (col: int, row: int, replacement: ParseableIngredientInput | null) {
		this.internal.replaceShapedInput(col, row, replacement === null ? null : artisanIngredient(replacement));
		return this;
	}

	public replaceOutput (replacement: ParseableIngredientOutput) {
		this.internal.replaceOutput(artisanItemStack(replacement));
		return this;
	}
}

export = RecipeBuilder;