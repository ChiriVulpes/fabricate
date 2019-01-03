declare module "math" {

	export class Vec3i {
		static readonly NULL_VECTOR: Vec3i;
		constructor(x: int, y: int, z: int);
		constructor(x: double, y: double, z: double);
		getX (): int;
		getY (): int;
		getZ (): int;
		crossProduct (vec: Vec3i): Vec3i;
		getDistance (x: int, y: int, z: int): int;
		distanceSq (vec: Vec3i): int;
		distanceSq (x: double, y: double, z: double): double;
		distanceSqToCenter (x: double, y: double, z: double): double;
	}
}
