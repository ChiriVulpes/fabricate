package works.chiri.fabricate.wrappers.math;

import net.minecraft.util.EnumFacing;

public enum AxisDirection {
	POSITIVE (EnumFacing.AxisDirection.POSITIVE),
	NEGATIVE (EnumFacing.AxisDirection.NEGATIVE);

	public static AxisDirection get (final EnumFacing.AxisDirection axisDirection) {
		for (final AxisDirection a : AxisDirection.values()) {
			if (a.axisDirection == axisDirection) return a;
		}

		throw new IllegalArgumentException(axisDirection.name() + " is not a valid axis direction");
	}

	private final EnumFacing.AxisDirection axisDirection;

	private AxisDirection (final EnumFacing.AxisDirection axisDirection) {
		this.axisDirection = axisDirection;
	}

	public EnumFacing.AxisDirection getAxisDirection () {
		return axisDirection;
	}

	public int getOffset () {
		return axisDirection.getOffset();
	}
}
