
declare module "artisan-worktables/recipe/requirement" {
	import { ResourceLocation } from "utility";
	import { ICraftingContext } from "artisan-worktables/recipe";

	export interface IRequirementContext {
		initialize (context: ICraftingContext): void;
	}

	export interface IRequirement<C extends IRequirementContext = IRequirementContext> {
		getResourceLocation (): ResourceLocation;
		match (context: C): boolean;
	}

	export interface IRequirementBuilder<C extends IRequirementContext = IRequirementContext, R extends IRequirement<C> = IRequirement> {
		getRequirementId (): string;
		getResourceLocation (): ResourceLocation;
		create (): R | null;
	}
}