package works.chiri.fabricate.wrappers.math;

public class Vec3i extends net.minecraft.util.math.Vec3i implements IVec3i {

	public static final Vec3i ZERO = new Vec3i(0, 0, 0);
	public static final Vec3i ONE = new Vec3i(1, 1, 1);
	public static final Vec3i UNIT_X = new Vec3i(1, 0, 0);
	public static final Vec3i UNIT_Y = new Vec3i(0, 1, 0);
	public static final Vec3i UNIT_Z = new Vec3i(0, 0, 1);

	public Vec3i (final net.minecraft.util.math.Vec3i vec) {
		super(vec.getX(), vec.getY(), vec.getZ());
	}

	public Vec3i (final int x, final int y, final int z) {
		super(x, y, z);
	}

	public Vec3i (final double x, final double y, final double z) {
		super(x, y, z);
	}

	public net.minecraft.util.math.Vec3i getVec3i () {
		return this;
	}
}
