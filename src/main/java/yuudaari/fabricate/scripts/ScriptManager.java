package yuudaari.fabricate.scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.commons.io.IOUtils;
import yuudaari.fabricate.scripts.util.FileVisitor;
import yuudaari.fabricate.scripts.util.TransformStream;
import yuudaari.fabricate.util.Llog;

public class ScriptManager {

	public static final String SCRIPTS_FOLDER = "scripts";

	private final Path DIRECTORY;
	private ScriptEngine engine;

	public ScriptManager (final String directory) {
		DIRECTORY = Paths.get(directory, SCRIPTS_FOLDER);
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
		final TransformStream stream = new TransformStream.Factory(new FileInputStream(path.toFile()))
			.transform("exports.__esModule = true;", "")
			.transform("\"use strict\";", "")
			.transform("= require(\"fabricate\");", "= Java.type(\"yuudaari.fabricate.api.Fabricate\");")
			.create();

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
