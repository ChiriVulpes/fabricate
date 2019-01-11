declare module "math" {

	export class InternalVec2f { }

	export class Vec2f extends InternalVec2f {
		static readonly ZERO: Vec2f;
		static readonly ONE: Vec2f;
		static readonly UNIT_X: Vec2f;
		static readonly UNIT_Y: Vec2f;
		constructor(vec: InternalVec2f);
		constructor(x: float, y: float);
		x (): float;
		y (): float;
		plus (vec: InternalVec2f): Vec2f;
		plus (x: float, y: float): Vec2f;
		minus (vec: InternalVec2f): Vec2f;
		minus (x: float, y: float): Vec2f;
	}
}