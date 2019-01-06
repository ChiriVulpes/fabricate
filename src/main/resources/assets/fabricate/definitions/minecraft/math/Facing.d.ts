declare module "math" {
	import { Random } from "random";

	export class IFacing { }

	export interface Facing extends Enum {
		getFacing (): IFacing;
		getAxis (): Axis;
		getAxisDirection (): AxisDirection;
		opposite (): Facing;
		rotateAround (axis: Axis | IAxis): Facing;
		rotateY (): Facing;
		rotateYCCW (): Facing;
		rotateX (): Facing;
		rotateZ (): Facing;
		getFrontOffsetX (): int;
		getFrontOffsetY (): int;
		getFrontOffsetZ (): int;
		getHorizontalAngle (): float;
		getDirectionVec (): Vec3i;
	}

	export module Facing {
		export const DOWN: Facing;
		export const UP: Facing;
		export const NORTH: Facing;
		export const SOUTH: Facing;
		export const WEST: Facing;
		export const EAST: Facing;
		export function get (facing: IFacing): Facing;
	}


	export class IAxis { }

	export interface Axis extends Enum {
		getAxis (): IAxis;
		isVertical (): boolean;
		isHorizontal (): boolean;
		getPlane (): Plane;
	}

	export module Axis {
		export const X: Axis;
		export const Y: Axis;
		export const Z: Axis;
		export function get (axis: IAxis): Axis;
	}


	export class IAxisDirection { }

	export interface AxisDirection extends Enum {
		getAxisDirection (): IAxisDirection;
		getOffset (): number;
	}

	export module AxisDirection {
		export const POSITIVE: AxisDirection;
		export const NEGATIVE: AxisDirection;
		export function get (axisDirection: IAxisDirection): AxisDirection;
	}


	export class IPlane { }

	export interface Plane extends Enum {
		getPlane (): IPlane;
		facings (): Facing[];
	}

	export module Plane {
		export const HORIZONTAL: Plane;
		export const VERTICAL: Plane;
		export function get (plane: IPlane): Plane;
	}
}