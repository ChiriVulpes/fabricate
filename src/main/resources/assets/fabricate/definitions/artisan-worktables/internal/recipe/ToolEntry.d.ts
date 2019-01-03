declare module "artisan-worktables/recipe" {
	export class ToolIngredientEntry {
		constructor(tool: IArtisanIngredient, damage: int);
		getTool (): IArtisanIngredient;
		getDamage (): int;
	}

	export class ToolEntry {
		constructor(entry: ToolIngredientEntry);
		constructor(tool: JArray<IArtisanItemStack>, damage: int);
		getTool (): JArray<IArtisanItemStack>;
		getDamage (): int;
	}
}