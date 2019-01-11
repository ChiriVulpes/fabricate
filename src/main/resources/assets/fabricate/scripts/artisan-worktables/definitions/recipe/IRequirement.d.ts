
declare module "artisan-worktables/recipe/requirement" {
	import { IResourceLocation } from "utility";
	import { ICraftingContext } from "artisan-worktables/recipe";

	export interface IRequirementContext {
		initialize (context: ICraftingContext): void;
	}

	export interface IRequirement<C extends IRequirementContext = IRequirementContext> {
		getResourceLocation (): IResourceLocation;
		match (context: C): boolean;
	}

	export interface IRequirementBuilder<C extends IRequirementContext = IRequirementContext, R extends IRequirement<C> = IRequirement> {
		getRequirementId (): string;
		getResourceLocation (): IResourceLocation;
		create (): R | null;
	}
}