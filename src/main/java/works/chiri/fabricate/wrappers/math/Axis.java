package works.chiri.fabricate.wrappers.math;

import net.minecraft.util.EnumFacing;

public enum Axis {

	X (EnumFacing.Axis.X),
	Y (EnumFacing.Axis.Y),
	Z (EnumFacing.Axis.Z);

	public static Axis get (final EnumFacing.Axis axis) {
		for (final Axis a : Axis.values()) {
			if (a.axis == axis) return a;
		}

		throw new IllegalArgumentException(axis.name() + " is not a valid axis");
	}

	private final EnumFacing.Axis axis;

	private Axis (final EnumFacing.Axis axis) {
		this.axis = axis;
	}

	public boolean isVertical () {
		return this.axis.isVertical();
	}

	public boolean isHorizontal () {
		return this.axis.isHorizontal();
	}

	public EnumFacing.Axis getAxis () {
		return axis;
	}

	public Plane getPlane () {
		return Plane.get(this.axis.getPlane());
	}
}
