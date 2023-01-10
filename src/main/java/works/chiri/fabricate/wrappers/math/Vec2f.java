package works.chiri.fabricate.wrappers.math;

public class Vec2f extends net.minecraft.util.math.Vec2f {

	public static final Vec2f ZERO = new Vec2f(0, 0);
	public static final Vec2f ONE = new Vec2f(1, 1);
	public static final Vec2f UNIT_X = new Vec2f(1, 0);
	public static final Vec2f UNIT_Y = new Vec2f(0, 1);

	public Vec2f (final net.minecraft.util.math.Vec2f vec) {
		super(vec.x, vec.y);
	}

	public Vec2f (final float x, final float y) {
		super(x, y);
	}

	public float x () {
		return x;
	}

	public float y () {
		return y;
	}

	public Vec2f plus (final net.minecraft.util.math.Vec2f vec) {
		return new Vec2f(this.x + vec.x, this.y + vec.y);
	}

	public Vec2f plus (final float x, final float y) {
		return new Vec2f(this.x + x, this.y + y);
	}

	public Vec2f minus (final net.minecraft.util.math.Vec2f vec) {
		return new Vec2f(this.x - vec.x, this.y - vec.y);
	}

	public Vec2f minus (final float x, final float y) {
		return new Vec2f(this.x - x, this.y - y);
	}
}
