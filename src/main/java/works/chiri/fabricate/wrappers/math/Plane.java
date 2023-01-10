package works.chiri.fabricate.wrappers.math;

import net.minecraft.util.EnumFacing;

public enum Plane {
	HORIZONTAL (EnumFacing.Plane.HORIZONTAL),
	VERTICAL (EnumFacing.Plane.VERTICAL);

	public static Plane get (final EnumFacing.Plane plane) {
		for (final Plane p : Plane.values()) {
			if (p.plane == plane) return p;
		}

		throw new IllegalArgumentException(plane.name() + " is not a valid plane");
	}

	private final EnumFacing.Plane plane;

	private Plane (final EnumFacing.Plane plane) {
		this.plane = plane;
	}

	public EnumFacing.Plane getPlane () {
		return plane;
	}

	public Facing[] facings () {
		switch (this) {
			case HORIZONTAL:
				return new Facing[] {
					Facing.NORTH, Facing.EAST, Facing.SOUTH, Facing.WEST
				};
			case VERTICAL:
				return new Facing[] {
					Facing.UP, Facing.DOWN
				};
			default:
				throw new Error("Someone's been tampering with the universe!");
		}
	}
}
