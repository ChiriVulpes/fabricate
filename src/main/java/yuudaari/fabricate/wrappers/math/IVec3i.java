package yuudaari.fabricate.wrappers.math;

import net.minecraft.util.math.Vec3i;

public interface IVec3i {

	public Vec3i getVec3i ();

	public default int x () {
		return getVec3i().getX();
	}

	public default int y () {
		return getVec3i().getY();
	}

	public default int z () {
		return getVec3i().getZ();
	}

	public default double getDistanceTo (final int x, final int y, final int z) {
		return getVec3i().getDistance(x, y, z);
	}

	public default double getSquareDistanceTo (final net.minecraft.util.math.Vec3i vec) {
		return getVec3i().distanceSq(vec);
	}

	public default double getSquareDistanceTo (final double x, final double y, final double z) {
		return getVec3i().distanceSq(x, y, z);
	}

	public default double getSquareDistanceToCenter (final double x, final double y, final double z) {
		return getVec3i().distanceSqToCenter(x, y, z);
	}
}
