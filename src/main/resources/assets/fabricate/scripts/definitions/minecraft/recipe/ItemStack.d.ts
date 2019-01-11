declare module "recipe" {

	export class IItemStack { }

	export class ItemStack {
		constructor(stack: IItemStack);
		constructor(item: Item, count: int, meta: int);
		getStack (): IItemStack;
	}
}