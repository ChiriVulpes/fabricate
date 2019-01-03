declare module "math" {
	export class BlockPos extends Vec3i {

		constructor(x: int, y: int, z: int);
		constructor(source: Entity);
		constructor(vec: Vec3i)

		add (x: int, y: int, z: int): BlockPos;
		add (x: float, y: float, z: float): BlockPos;
		add (vec: Vec3i): BlockPos;
		subtract (vec: Vec3i): BlockPos;
		up (amount?: int): BlockPos;
		down (amount?: int): BlockPos;
		north (amount?: int): BlockPos;
		south (amount?: int): BlockPos;
		east (amount?: int): BlockPos;
		west (amount?: int): BlockPos;
		offset (facing: Facing, amount?: int): BlockPos;
		rotate (rotation: Rotation): BlockPos;
		crossProduct (vec: Vec3i): BlockPos;
		toLong (): number;
	}

	export module BlockPos {
		export function fromLong (long: number): BlockPos;
		export function getAllInBox (from: BlockPos, to: BlockPos): Iterable<BlockPos>;
		export function getAllInBox (x1: number, y1: number, z1: number, x2: number, y2: number, z2: number): Iterable<BlockPos>;
	}
}
