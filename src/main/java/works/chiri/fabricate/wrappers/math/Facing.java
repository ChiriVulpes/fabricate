package works.chiri.fabricate.wrappers.math;

import net.minecraft.util.EnumFacing;

public enum Facing {

	DOWN (EnumFacing.DOWN),
	UP (EnumFacing.UP),
	NORTH (EnumFacing.NORTH),
	SOUTH (EnumFacing.SOUTH),
	WEST (EnumFacing.WEST),
	EAST (EnumFacing.EAST);

	public static Facing get (final EnumFacing facing) {
		for (final Facing f : Facing.values()) {
			if (f.facing == facing) return f;
		}

		throw new IllegalArgumentException(facing.name() + " is not a valid Facing");
	}

	private final EnumFacing facing;

	private Facing (final EnumFacing facing) {
		this.facing = facing;
	}

	public EnumFacing getFacing () {
		return facing;
	}

	public Axis getAxis () {
		return Axis.get(facing.getAxis());
	}

	public AxisDirection getAxisDirection () {
		return AxisDirection.get(facing.getAxisDirection());
	}

	public Facing opposite () {
		return Facing.get(getFacing().getOpposite());
	}

	public Facing rotateAround (final Axis axis) {
		return rotateAround(axis.getAxis());
	}

	public Facing rotateAround (final EnumFacing.Axis axis) {
		return Facing.get(facing.rotateAround(axis));
	}

	public Facing rotateY () {
		return Facing.get(getFacing().rotateY());
	}

	public Facing rotateYCCW () {
		return Facing.get(getFacing().rotateYCCW());
	}

	public Facing rotateX () {
		switch (this) {
			case NORTH:
				return DOWN;
			case SOUTH:
				return UP;
			case UP:
				return NORTH;
			case DOWN:
				return SOUTH;
			default:
				throw new IllegalStateException("Unable to get X-rotated facing of " + this);
		}
	}

	public Facing rotateZ () {
		switch (this) {
			case EAST:
				return DOWN;
			case WEST:
				return UP;
			case UP:
				return EAST;
			case DOWN:
				return WEST;
			default:
				throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
		}
	}

	public int getFrontOffsetX () {
		return facing.getFrontOffsetX();
	}

	public int getFrontOffsetY () {
		return facing.getFrontOffsetY();
	}

	public int getFrontOffsetZ () {
		return facing.getFrontOffsetZ();
	}

	public float getHorizontalAngle () {
		return facing.getHorizontalAngle();
	}

	public Vec3i getDirectionVec () {
		return new Vec3i(facing.getDirectionVec());
	}
}
