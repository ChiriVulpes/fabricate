package yuudaari.fabricate.wrappers.math;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class BlockPos extends net.minecraft.util.math.BlockPos implements IVec3i {

	public BlockPos (final net.minecraft.util.math.BlockPos pos) {
		super(pos.getX(), pos.getY(), pos.getZ());
	}

	public BlockPos (final int x, final int y, final int z) {
		super(x, y, z);
	}

	public BlockPos (final double x, final double y, final double z) {
		super(x, y, z);
	}

	public BlockPos (final Entity source) {
		super(source);
	}

	public BlockPos (final Vec3d vec) {
		super(vec);
	}

	public BlockPos (final Vec3i source) {
		super(source);
	}

	public net.minecraft.util.math.Vec3i getVec3i () {
		return this;
	}

	public BlockPos plus (final double x, final double y, final double z) {
		return new BlockPos(super.add(x, y, z));
	}

	public BlockPos plus (final int x, final int y, final int z) {
		return new BlockPos(super.add(x, y, z));
	}

	public BlockPos plus (final Vec3i vec) {
		return this.plus(vec.getX(), vec.getY(), vec.getZ());
	}

	public BlockPos minus (final Vec3i vec) {
		return this.plus(-vec.getX(), -vec.getY(), -vec.getZ());
	}

	public BlockPos movedUp () {
		return this.movedUp(1);
	}

	public BlockPos movedUp (final int n) {
		return this.moved(EnumFacing.UP, n);
	}

	public BlockPos movedDown () {
		return this.movedDown(1);
	}

	public BlockPos movedDown (final int n) {
		return this.moved(EnumFacing.DOWN, n);
	}

	public BlockPos moveNorth () {
		return this.movedNorth(1);
	}

	public BlockPos movedNorth (final int n) {
		return this.moved(EnumFacing.NORTH, n);
	}

	public BlockPos movedSouth () {
		return this.movedSouth(1);
	}

	public BlockPos movedSouth (final int n) {
		return this.moved(EnumFacing.SOUTH, n);
	}

	public BlockPos movedWest () {
		return this.movedWest(1);
	}

	public BlockPos movedWest (final int n) {
		return this.moved(EnumFacing.WEST, n);
	}

	public BlockPos movedEast () {
		return this.movedEast(1);
	}

	public BlockPos movedEast (final int n) {
		return this.moved(EnumFacing.EAST, n);
	}

	public BlockPos moved (final EnumFacing facing) {
		return this.moved(facing, 1);
	}

	public BlockPos moved (final EnumFacing facing, final int n) {
		return new BlockPos(super.offset(facing, n));
	}

	public BlockPos getRotated (final net.minecraft.util.Rotation rotation) {
		return new BlockPos(super.rotate(rotation));
	}

	public BlockPos getRotated (final Rotation rotation) {
		return new BlockPos(super.rotate(rotation.getRotation()));
	}
}
