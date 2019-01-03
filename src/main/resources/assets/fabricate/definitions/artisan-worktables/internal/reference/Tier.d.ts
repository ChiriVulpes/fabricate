declare module "artisan-worktables/reference" {
	export interface Tier extends Enum {
		getId (): number;
	}

	export module Tier {
		export const WORKTABLE: Tier;
		export const WORKSTATION: Tier;
		export const WORKSHOP: Tier;
		export function fromId (id: number): Tier;
	}
}