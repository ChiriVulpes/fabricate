declare module "log" {
	export class Log {
		public static info (...toLog: any[]): void;
		public static warn (...toLog: any[]): void;
		public static err (...toLog: any[]): void;
	}
}