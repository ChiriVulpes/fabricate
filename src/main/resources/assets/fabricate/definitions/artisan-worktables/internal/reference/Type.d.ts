declare module "artisan-worktables/reference" {
	export interface Type extends IVariant {
		getTextOutlineColor (): number;
		getGuiTabTextureOffsetY (): number;
	}

	export module Type {
		export const TAILOR: Type;
		export const CARPENTER: Type;
		export const MASON: Type;
		export const BLACKSMITH: Type;
		export const JEWELER: Type;
		export const BASIC: Type;
		export const ENGINEER: Type;
		export const MAGE: Type;
		export const SCRIBE: Type;
		export const CHEMIST: Type;
		export const FARMER: Type;
		export const CHEF: Type;
		export const DESIGNER: Type;
		export const TANNER: Type;
		export const POTTER: Type;
		export const NAMES: JArray<string>;
		export function fromName (name: string): Type;
		export function fromMeta (meta: number): Type;
	}
}