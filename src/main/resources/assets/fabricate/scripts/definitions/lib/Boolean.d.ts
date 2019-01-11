interface Boolean {
	/** Returns the primitive value of the specified object. */
	valueOf (): boolean;
}

interface BooleanConstructor {
	new(value?: any): Boolean;
	(value?: any): boolean;
	readonly prototype: Boolean;
}

declare const Boolean: BooleanConstructor;