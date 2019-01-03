declare module "math" {

	export class Vec2f {
		static readonly ZERO: Vec2f;
		static readonly ONE: Vec2f;
		static readonly UNIT_X: Vec2f;
		static readonly NEGATIVE_UNIT_X: Vec2f;
		static readonly UNIT_Y: Vec2f;
		static readonly NEGATIVE_UNIT_Y: Vec2f;
		static readonly MAX: Vec2f;
		static readonly MIN: Vec2f;
		readonly x: float;
		readonly y: float;
		constructor(x: float, y: float);
	}
}