package yuudaari.fabricate.api;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import yuudaari.fabricate.util.Llog;

public class Fabricate {

	public static Fabricate Fabricate = new Fabricate();
	public static RegistryEvents RegistryEvent = new RegistryEvents();

	public void on (int event, ScriptObjectMirror handler) {
		Llog.info(event, handler, handler.getClass());
	}

	public static interface IHandler {

		public void handle (Object argument);
	}
}
