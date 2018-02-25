package yuudaari.fabricate.scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import yuudaari.fabricate.scripts.util.FileVisitor;
import yuudaari.fabricate.scripts.util.TransformStream;
import yuudaari.fabricate.util.Llog;

public class ScriptManager {

	public static final String SCRIPTS_FOLDER = "scripts";

	private final Path DIRECTORY;
	private final Map<String, String> MODULES;
	private ScriptEngine engine;

	public ScriptManager (final String directory, final Map<String, String> modules) {
		DIRECTORY = Paths.get(directory, SCRIPTS_FOLDER);
		MODULES = modules;
	}

	public void load () {
		engine = new ScriptEngineManager(null).getEngineByName("nashorn");
		try {
			new FileVisitor("**/*.js", this::onScriptFile)
				.walk(DIRECTORY);
		} catch (final IOException e) {
			Llog.err(e);
		}
	}

	public void onScriptFile (final Path path) throws FileNotFoundException {
		final Reader scriptReader = getScriptReader(path);

		try {
			engine.eval(scriptReader);
		} catch (final ScriptException e) {
			Llog.err(e);
		}
	}

	private Reader getScriptReader (final Path path) throws FileNotFoundException {
		final TransformStream.Factory streamFactory = new TransformStream.Factory(new FileInputStream(path.toFile()))
			.transform("\"use strict\";", "")
			.transform("exports.__esModule = true;", "");

		for (final Entry<String, String> module : MODULES.entrySet()) {
			streamFactory.transform("require(\"" + module.getKey() + "\")", "Java.type(\"" + module.getValue() + "\")");
		}

		final TransformStream stream = streamFactory.create();

		/*
		Llog.info(path.toFile().getAbsolutePath());
		
		try {
			String result = IOUtils.toString(stream, StandardCharsets.UTF_8);
			Llog.info(result);
		} catch (IOException e) {
			Llog.info(e);
		}
		*/

		return new InputStreamReader(stream);
	}


}
