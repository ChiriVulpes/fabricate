declare module "artisan-worktables/recipe" {
	export class OutputWeightPair {
		constructor(output: IArtisanItemStack, weight: int);
		getOutput (): IArtisanItemStack;
		getWeight (): int;
	}
}