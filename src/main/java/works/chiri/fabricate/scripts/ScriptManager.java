package works.chiri.fabricate.scripts;

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
import org.apache.commons.io.IOUtils;
import works.chiri.fabricate.scripts.DefinitionManager.Module;
import works.chiri.fabricate.scripts.util.FileVisitor;
import works.chiri.fabricate.scripts.util.TransformStream;
import works.chiri.fabricate.util.Llog;

public class ScriptManager {

	public static final String SCRIPTS_FOLDER = "scripts";

	private final Path DIRECTORY;
	private final Map<String, Module> MODULES;
	private ScriptEngine engine;

	public ScriptManager (final String directory, final Map<String, Module> modules) {
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

		boolean errored = false;
		try {
			engine.eval(scriptReader);
		} catch (final ScriptException e) {
			Llog.err(e);
			errored = true;
		}

		if (errored) {
			// for viewing the output. note: prevents the script reader from working
			Llog.info(path.toFile().getAbsolutePath());

			try {
				String result = IOUtils.toString(scriptReader);
				Llog.info(result);
			} catch (IOException e) {
				Llog.info(e);
			}
		}
	}

	private Reader getScriptReader (final Path path) throws FileNotFoundException {
		final TransformStream.Factory streamFactory = new TransformStream.Factory(new FileInputStream(path.toFile()))
			.transform("\"use strict\";", "")
			.transform("exports.__esModule = true;", "");

		for (final Entry<String, Module> module : MODULES.entrySet()) {
			final String moduleReplacement = getModuleReplacement(module.getValue());
			streamFactory.transform("require(\"" + module.getKey() + "\")", moduleReplacement);
			streamFactory.transform("\"" + module.getKey() + "[]\")", "\"" + module.getValue() + "[]\")");
		}

		final TransformStream stream = streamFactory.create();
		return new InputStreamReader(stream);
	}

	private String getModuleReplacement (final Module module) {
		if (module.isSimple()) return "Java.type(\"" + module.getPath() + "\")";

		final StringBuilder replacement = new StringBuilder();
		replacement.append('{');

		for (final Map.Entry<String, String> export : module.getExports().entrySet()) {
			replacement.append('"');
			replacement.append(export.getKey());
			replacement.append("\":");
			replacement.append("Java.type(\"");
			if (module.getPath().length() > 0) {
				replacement.append(module.getPath());
				replacement.append('.');
			}
			replacement.append(export.getValue());
			replacement.append("\")");
			replacement.append(',');
		}

		replacement.replace(replacement.length() - 1, replacement.length(), "}");

		return replacement.toString();
	}


}
