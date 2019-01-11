declare module "math" {

	export class InternalVec3d { }

	export class Vec3d extends InternalVec3d {
		static readonly ZERO: Vec3d;
		static readonly ONE: Vec3d;
		static readonly UNIT_X: Vec3d;
		static readonly UNIT_Y: Vec3d;
		static readonly UNIT_Z: Vec3d;
		constructor(x: double, y: double, z: double);
		constructor(vec: Vec3i | Vec3d);
		x (): double;
		y (): double;
		z (): double;
		getNormalized (): Vec3d;
		plus (vec: InternalVec3i | InternalVec3d): Vec3d;
		plus (x: double, y: double, z: double): Vec3d;
		minus (vec: InternalVec3d | InternalVec3i): Vec3d;
		minus (x: double, y: double, z: double): Vec3d;
		getDistanceTo (x: int, y: int, z: int): double;
		getSquareDistanceTo (vec: InternalVec3i): double;
		getSquareDistanceTo (x: double, y: double, z: double): double;
		getLength (): double;
		getSquareLength (): double;
		getRotatedByPitch (pitch: float): Vec3d;
		getRotatedByYaw (yaw: float): Vec3d;
		getScaled (factor: double): Vec3d;
	}
}