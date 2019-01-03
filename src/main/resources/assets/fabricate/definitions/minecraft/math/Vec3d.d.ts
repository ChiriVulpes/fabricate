declare module "math" {

	export class Vec3d {
		static readonly ZERO: Vec3d;
		static fromPitchYaw (pitch: double, yaw: double): Vec3d;
		static fromPitchYawVector (vec: Vec2f): Vec3d;
		x: double;
		y: double;
		z: double;
		constructor(x: double, y: double, z: double);
		constructor(vec3i: Vec3i);
		addVector (x: double, y: double, z: double): Vec3d;
		add (vec: Vec3d): Vec3d;
		subtract (x: double, y: double, z: double): Vec3d;
		subtract (vec: Vec3d): Vec3d;
		subtractReverse (vec: Vec3d): Vec3d;
		normalize (): Vec3d;
		dotProduct (vec: Vec3d): double;
		crossProduct (vec: Vec3d): Vec3d;
		distanceTo (vec: Vec3d): double;
		squareDistanceTo (vec: Vec3d): double;
		squareDistanceTo (x: double, y: double, z: double): double;
		scale (factor: double): Vec3d;
		lengthVector (): double;
		lengthSquared (): double;
		getIntermediateWithXValue (vec: Vec3d, x: double): Vec3d | null;
		getIntermediateWithYValue (vec: Vec3d, x: double): Vec3d | null;
		getIntermediateWithZValue (vec: Vec3d, x: double): Vec3d | null;
		rotatePitch (pitch: double): Vec3d;
		rotateYaw (yaw: double): Vec3d;
	}
}