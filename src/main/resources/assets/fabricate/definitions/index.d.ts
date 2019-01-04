declare interface JArray<T> {
	length: number;
	[key: number]: T;
}

declare module Java {
	export function type (name: string): any;
	export function to (data: number[], to: "int[]"): int[];
	export function to (data: number[], to: "float[]"): float[];
	export function to<T> (data: T[], to: string): T[];
}

declare interface Enum {
	getName (): string;
	getOrdinal (): number;
}

/**
 * Note: no type checking
 */
type float = symbol | number;
/**
 * Note: no type checking
 */
type double = symbol | number;
/**
 * Note: no type checking
 */
type byte = symbol | number;
/**
 * Note: no type checking
 */
type short = symbol | number;
/**
 * Note: no type checking
 */
type int = symbol | number;
/**
 * Note: no type checking
 */
type long = symbol | number;