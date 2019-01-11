declare module "artisan-worktables/recipe" {
	export class ExtraOutputChancePair {
		constructor(output: IArtisanItemStack, chance: float);
		getOutput (): IArtisanItemStack;
		getChance (): float;
	}
}