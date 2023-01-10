package works.chiri.fabricate.util;

import java.util.Arrays;
import java.util.stream.Collectors;
import net.minecraftforge.fml.common.FMLLog;
import works.chiri.fabricate.Fabricate;

public class Llog {

	private static String getLogString (final Object... toLog) {
		return Arrays.asList(toLog)
			.stream()
			.map(o -> o == null ? "null" : o.toString())
			.collect(Collectors.joining(" "));
	}

	public static void info (final Object... toLog) {
		FMLLog.log.info("[" + Fabricate.MODID + "] " + getLogString(toLog));
	}

	public static void warn (final Object... toLog) {
		FMLLog.log.warn("[" + Fabricate.MODID + "] " + getLogString(toLog));
	}

	public static void err (final Object... toLog) {
		FMLLog.log.error("[" + Fabricate.MODID + "] " + getLogString(toLog));
	}
}
