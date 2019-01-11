declare module Java {
	export function type (name: string): any;
	export function type<T> (name: string): T;
	export function to (data: number[], to: "int[]"): int[];
	export function to (data: number[], to: "float[]"): float[];
	export function to<T> (data: T[], to: string): T[];
}

declare interface Enum {
	name (): string;
	ordinal (): number;
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