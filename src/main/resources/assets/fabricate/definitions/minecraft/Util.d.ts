declare module "utility" {

	export class IResourceLocation { }

	export class ResourceLocation extends IResourceLocation {
		constructor(resourceLocation: IResourceLocation);
		constructor(name: string);
		constructor(domain: string, path: string);
		getResourceDomain (): string;
		getResourcePath (): string;
		toString (): string;
	}
}
