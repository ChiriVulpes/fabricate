declare module "artisan-worktables/recipe" {
	export class ToolIngredientEntry {
		constructor(tool: IArtisanIngredient, damage: int);
		getTool (): IArtisanIngredient;
		getDamage (): int;
	}

	export class ToolEntry {
		constructor(entry: ToolIngredientEntry);
		constructor(tool: IArtisanItemStack[], damage: int);
		getTool (): IArtisanItemStack[];
		getDamage (): int;
	}
}