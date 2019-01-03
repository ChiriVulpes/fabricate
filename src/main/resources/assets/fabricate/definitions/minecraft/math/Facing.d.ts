declare module "math" {
	import { Random } from "random";

	export interface Facing extends Enum {
		getName2 (): string;
		getIndex (): number;
		getHorizontalIndex (): number;
		getAxis (): Axis;
		getAxisDirection (): AxisDirection;
		getOpposite (): Facing;
		rotateAround (axis: Axis): Facing;
		rotateY (): Facing;
		rotateX (): Facing;
		rotateZ (): Facing;
		rotateYCCW (): Facing;
		getFrontOffsetX (): number;
		getFrontOffsetY (): number;
		getFrontOffsetZ (): number;
		getHorizontalAngle (): number;
		getDirectionVec (): Vec3i;
	}

	export module Facing {
		export const DOWN: Facing;
		export const UP: Facing;
		export const NORTH: Facing;
		export const SOUTH: Facing;
		export const WEST: Facing;
		export const EAST: Facing;
		export function getDirectionFromEntityLiving (pos: BlockPos, placer: EntityLiving): Facing;
		export function getFacingFromAxis (direction: AxisDirection, axis: Axis): Facing;
		export function getFacingFromVector (x: number, y: number, z: number): Facing;
		export function random (rand: Random): Facing;
		export function fromAngle (angle: number): Facing;
		export function getHorizontal (horizontalIndex: number): Facing;
		export function getFront (index: number): Facing;
		export function byName (name: "down" | "up" | "north" | "south" | "west" | "east"): Facing;
		export function byName (name: string | null): Facing | null;
	}

	export interface Axis extends Enum {
		getName2 (): string;
		isVertical (): boolean;
		isHorizontal (): boolean;
		getPlane (): Plane;
	}

	export module Axis {
		export const X: Axis;
		export const Y: Axis;
		export const Z: Axis;
		export function byName (name: "x" | "y" | "z"): Axis;
		export function byName (name: string | null): Axis | null;
	}

	export interface AxisDirection extends Enum {
		getOffset (): number;
	}

	export module AxisDirection {
		export const POSITIVE: AxisDirection;
		export const NEGATIVE: AxisDirection;
	}

	export interface Plane extends Enum {
		facings (): JArray<Facing>;
		iterator (): Iterator<Facing>
		random (rand: Random): Facing;
	}

	export module Plane {
		export const HORIZONTAL: Plane;
		export const VERTICAL: Plane;
	}

}