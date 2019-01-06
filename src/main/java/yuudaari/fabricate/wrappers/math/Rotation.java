package yuudaari.fabricate.wrappers.math;

public enum Rotation {
	NONE (net.minecraft.util.Rotation.NONE),
	CLOCKWISE_90 (net.minecraft.util.Rotation.CLOCKWISE_90),
	CLOCKWISE_180 (net.minecraft.util.Rotation.CLOCKWISE_180),
	COUNTERCLOCKWISE_90 (net.minecraft.util.Rotation.COUNTERCLOCKWISE_90);

	public static Rotation get (final net.minecraft.util.Rotation rotation) {
		for (final Rotation r : Rotation.values()) {
			if (r.rotation == rotation) return r;
		}

		throw new IllegalArgumentException(rotation.name() + " is not a valid rotation");
	}

	private final net.minecraft.util.Rotation rotation;

	private Rotation (final net.minecraft.util.Rotation rotation) {
		this.rotation = rotation;
	}

	public net.minecraft.util.Rotation getRotation () {
		return rotation;
	}

	public Rotation add (final Rotation rotation) {
		return Rotation.get(this.rotation.add(rotation.rotation));
	}

	public Facing rotate (final Facing facing) {
		return Facing.get(this.rotation.rotate(facing.getFacing()));
	}
}
