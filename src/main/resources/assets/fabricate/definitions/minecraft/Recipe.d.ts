declare module "recipe" {

	export interface Item {

	}

	export interface ItemStack {

	}

	export interface Ingredient {

	}

	export interface FluidStack {

	}

	export interface IItemHandler {
		getSlots (): number;
		getStackInSlot (slot: number): ItemStack;
		insertItem (slot: number, stack: ItemStack, simulate: boolean): ItemStack;
		extractItem (slot: number, amount: number, simulate: boolean): ItemStack;
		getSlotLimit (slot: number): number;
		isItemValid (slot: number, stack: ItemStack): boolean;
	}

	export interface IItemHandlerModifiable extends IItemHandler {
		setStackInSlot (slot: number, stack: ItemStack): void;
	}

	export interface IFluidTankProperties {
		getContents (): FluidStack | null;
		getCapacity (): number;
		canFill (): boolean;
		canDrain (): boolean;
		canFillFluidType (stack: FluidStack): boolean;
		canDrainFluidType (stack: FluidStack): boolean;
	}

	export interface IFluidHandler {
		getTankProperties (): JArray<IFluidTankProperties>;
		fill (resource: FluidStack, doFill: boolean): number;
		drain (resource: FluidStack, doDrain: boolean): FluidStack | null;
		drain (maxDrain: number, doDrain: boolean): FluidStack | null;
	}
}
