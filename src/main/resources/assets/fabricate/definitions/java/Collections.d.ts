declare module "collections" {

	export interface Collection<T> {
		size (): number;
		isEmpty (): boolean;
		contains (object: any): boolean;
		iterator (): Iterator<T>;
		toArray (): JArray<any>;
		toArray (arr: JArray<T>): JArray<T>;
		add (value: T): boolean;
		remove (value: any): boolean;
		containsAll (collection: Collection<any>): boolean;
		addAll (collection: Collection<T>): boolean;
		removeAll (collection: Collection<any>): boolean;
		removeIf (filter: (item: T) => boolean): boolean;
		retailAll (collection: Collection<any>): boolean;
		clear (): void;

		// todo stream, spliterator types
	}

	export interface List<T> extends Collection<T> {
		addAll (collection: Collection<T>): boolean;
		addAll (insertIndex: number, collection: Collection<T>): boolean;
		replaceAll (operator: (value: T) => T): void;
		sort (comparator: (a: T, b: T) => number): void;
		get (index: number): T;
		set (index: number, value: T): T;
		add (value: T): boolean;
		add (index: number, value: T): void;
		remove (value: any): boolean;
		remove (index: number): T;
		indexOf (value: any): number;
		lastIndexOf (value: any): number;
		listIterator (startIndex?: number): ListIterator<T>;
		subList (fromIndex: number, toIndex: number): List<T>;
	}

	export interface ListIterator<T> extends Iterator<T> {
		hasPrevious (): boolean;
		previous (): T;
		nextIndex (): number;
		previousIndex (): number;
		remove (): void;
		set (value: T): void;
		add (value: T): void;
	}

	export interface Set<T> extends Collection<T> { }

	export interface Map<K, V> {
		size (): number;
		isEmpty (): boolean;
		containsKey (key: any): boolean;
		containsValue (value: any): boolean;
		get (key: any): V | null;
		put (key: K, value: V): V;
		putAll (map: Map<K, V>): void;
		remove (key: any): V | null;
		remove (key: any, value: any): boolean;
		clear (): void;
		keySet (): Set<K>;
		values (): Collection<V>;
		entrySet (): Set<Map.Entry<K, V>>;
		getOrDefault (key: any, defaultValue: V): V;
		forEach (consumer: (key: K, value: V) => void): void;
		replaceAll (operator: (key: K, value: V) => V): void;
		putIfAbsent (key: K, value: V): V;
		replace (key: K, value: V): boolean;
		replace (key: K, oldValue: V, newValue: V): boolean;
		compute (key: K, mapper: (key: K, oldValue: V | null) => V | null): V | null;
		computeIfAbsent (key: K, mapper: (key: K) => V | null): V | null;
		computeIfPresent (key: K, mapper: (key: K, oldValue: V) => V | null): V | null;
		merge (key: K, value: V, mapper: (oldValue: V, newValue: V) => V | null): V | null;
	}

	export module Map {
		export interface Entry<K, V> {
			getKey (): K;
			getValue (): V;
			setValue (value: V): V;
		}
	}
}