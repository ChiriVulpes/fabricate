package yuudaari.fabricate.wrappers.math;

public class Vec3d extends net.minecraft.util.math.Vec3d {

	public static final Vec3d ZERO = new Vec3d(0, 0, 0);
	public static final Vec3d ONE = new Vec3d(1, 1, 1);
	public static final Vec3d UNIT_X = new Vec3d(1, 0, 0);
	public static final Vec3d UNIT_Y = new Vec3d(0, 1, 0);
	public static final Vec3d UNIT_Z = new Vec3d(0, 0, 1);

	public Vec3d (final double x, final double y, final double z) {
		super(x, y, z);
	}

	public Vec3d (final net.minecraft.util.math.Vec3i vec) {
		super(vec);
	}

	public Vec3d (final net.minecraft.util.math.Vec3d vec) {
		super(vec.x, vec.y, vec.z);
	}

	public double x () {
		return x;
	}

	public double y () {
		return y;
	}

	public double z () {
		return z;
	}

	public Vec3d getNormalized () {
		return new Vec3d(normalize());
	}

	public Vec3d plus (final net.minecraft.util.math.Vec3d vec) {
		return plus(vec.x, vec.y, vec.z);
	}

	public Vec3d plus (final net.minecraft.util.math.Vec3i vec) {
		return plus(vec.getX(), vec.getY(), vec.getZ());
	}

	public Vec3d plus (final double x, final double y, final double z) {
		return new Vec3d(this.x + x, this.y + y, this.z + z);
	}

	public Vec3d minus (final net.minecraft.util.math.Vec3d vec) {
		return minus(vec.x, vec.y, vec.z);
	}

	public Vec3d minus (final net.minecraft.util.math.Vec3i vec) {
		return minus(vec.getX(), vec.getY(), vec.getZ());
	}

	public Vec3d minus (final double x, final double y, final double z) {
		return new Vec3d(this.x - x, this.y - y, this.z - z);
	}

	public double getDistanceTo (final int x, final int y, final int z) {
		return Math.sqrt(squareDistanceTo(x, y, z));
	}

	public double getSquareDistanceTo (final net.minecraft.util.math.Vec3i vec) {
		return getSquareDistanceTo(vec.getX(), vec.getY(), vec.getZ());
	}

	public double getSquareDistanceTo (final double x, final double y, final double z) {
		return getSquareDistanceTo(x, y, z);
	}

	public double getLength () {
		return lengthVector();
	}

	public double getSquareLength () {
		return lengthSquared();
	}

	public Vec3d getRotatedByPitch (final float pitch) {
		return new Vec3d(rotatePitch(pitch));
	}

	public Vec3d getRotatedByYaw (final float yaw) {
		return new Vec3d(rotateYaw(yaw));
	}

	public Vec3d getScaled (final double factor) {
		return new Vec3d(scale(factor));
	}
}
