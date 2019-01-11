declare module "math" {

	export class IRotation { }

	export interface Rotation extends Enum {
		getRotation (): IRotation;
		add (rotation: Rotation): Rotation;
		rotate (facing: Facing): Facing;
		rotate (angle: int, nearest: int): int;
	}

	export module Rotation {
		export const NONE: Rotation;
		export const CLOCKWISE_90: Rotation;
		export const CLOCKWISE_180: Rotation;
		export const COUNTERCLOCKWISE_90: Rotation;
		export function get (rotation: IRotation): Rotation;
	}
}
