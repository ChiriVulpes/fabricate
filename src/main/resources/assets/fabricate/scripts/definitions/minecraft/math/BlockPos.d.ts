declare module "math" {
	export class IBlockPos extends InternalVec3i { }

	export class BlockPos extends IBlockPos implements IVec3i {

		constructor(pos: IBlockPos);
		constructor(x: int, y: int, z: int);
		constructor(source: Entity);
		constructor(vec: InternalVec3i);

		x (): int;
		y (): int;
		z (): int;
		getDistanceTo (x: int, y: int, z: int): double;
		getSquareDistanceTo (vec: Vec3i): double;
		getSquareDistanceTo (x: double, y: double, z: double): double;
		getSquareDistanceToCenter (x: double, y: double, z: double): double;

		plus (x: int, y: int, z: int): BlockPos;
		plus (x: float, y: float, z: float): BlockPos;
		plus (vec: InternalVec3i): BlockPos;
		minus (vec: InternalVec3i): BlockPos;
		movedUp (amount?: int): BlockPos;
		movedDown (amount?: int): BlockPos;
		movedNorth (amount?: int): BlockPos;
		movedSouth (amount?: int): BlockPos;
		movedEast (amount?: int): BlockPos;
		movedWest (amount?: int): BlockPos;
		moved (facing: Facing, amount?: int): BlockPos;
		getRotated (rotation: Rotation): BlockPos;
	}
}
