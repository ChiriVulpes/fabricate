declare module "math" {

	export class InternalVec3i { }

	export interface IVec3i {
		x (): int;
		y (): int;
		z (): int;
		getDistanceTo (x: int, y: int, z: int): double;
		getSquareDistanceTo (vec: Vec3i): double;
		getSquareDistanceTo (x: double, y: double, z: double): double;
		getSquareDistanceToCenter (x: double, y: double, z: double): double;
	}

	export class Vec3i extends InternalVec3i implements IVec3i {
		static readonly ZERO: Vec3i;
		constructor(vec: InternalVec3i);
		constructor(x: int, y: int, z: int);
		constructor(x: double, y: double, z: double);
		x (): int;
		y (): int;
		z (): int;
		getDistanceTo (x: int, y: int, z: int): double;
		getSquareDistanceTo (vec: Vec3i): double;
		getSquareDistanceTo (x: double, y: double, z: double): double;
		getSquareDistanceToCenter (x: double, y: double, z: double): double;
	}
}
