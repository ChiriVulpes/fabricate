declare interface Iterable<T> {
	iterator (): Iterator<T>;
}

declare interface Iterator<T> {
	hasNext (): boolean;
	next (): T;
	forEachRemaining (consumer: (value: T) => void): void;
}