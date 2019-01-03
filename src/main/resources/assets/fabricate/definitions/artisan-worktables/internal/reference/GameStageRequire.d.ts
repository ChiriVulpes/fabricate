declare module "artisan-worktables/reference" {
	export interface GameStageRequire extends Enum { }

	export module GameStageRequire {
		export const ALL: GameStageRequire;
		export const ANY: GameStageRequire;
		export function fromName (name: string): GameStageRequire;
	}
}