declare module "random" {
	export class Random {
		constructor(seed?: float);
		setSeed (seed: float): void;
		nextBytes (bytes: JArray<byte>): void;
		nextInt (): int;
		nextInt (bound: int): int;
		nextLong (): long;
		nextBoolean (): boolean;
		nextFloat (): float;
		nextDouble (): double;
		nextGaussian (): double;
	}
}