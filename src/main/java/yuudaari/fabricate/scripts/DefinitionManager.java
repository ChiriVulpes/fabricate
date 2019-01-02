package yuudaari.fabricate.scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import yuudaari.fabricate.util.Llog;

public class DefinitionManager {

	private final Path DIRECTORY;

	public static class Module {

		private boolean simple = true;
		private Map<String, String> exports;
		private String root;

		public Module (final String path) {
			this.root = path;
		}

		public Module (final String root, final Map<String, String> exports) {
			this.root = root;
			this.exports = exports;
			simple = false;
		}

		public boolean isSimple () {
			return simple;
		}

		public String getPath () {
			return root;
		}

		public Map<String, String> getExports () {
			return exports;
		}
	}

	public DefinitionManager (final String directory) {
		DIRECTORY = Paths.get(directory, ScriptManager.SCRIPTS_FOLDER, "definitions");
	}

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private ModContainer modContainer;
	private final Map<String, Module> MODULES = new HashMap<>();

	public Map<String, Module> getModuleMap () {
		return MODULES;
	}

	/**
	 * Refreshes the `/scripts/definitions/` folder from the definitions of all present mods.
	 */
	public void load () {
		try {
			FileUtils.forceDelete(new File(DIRECTORY.toString()));
		} catch (final IOException e) {
			if (!(e instanceof FileNotFoundException)) {
				Llog.warn("Couldn't clean the definitions folder", e);
				return;
			}
		}

		final ModContainer thisContainer = Loader.instance().activeModContainer();
		final Loader loader = Loader.instance();
		loader.setActiveModContainer(null);
		loader.getActiveModList().forEach(this::loadDefinitions);
		loader.setActiveModContainer(thisContainer);
	}

	/**
	 * Loads the definitions provided by a mod
	 */
	private boolean loadDefinitions (final ModContainer mod) {
		modContainer = mod;
		final String definitionsAssetsDirectory = "assets/" + mod.getModId() + "/definitions";

		return CraftingHelper.findFiles(mod, definitionsAssetsDirectory, //
			this::loadDefinitionsRoot, //
			this::loadDefinition, //
			true, true);
	}

	/**
	 * Handles the root of the `/definitions/` folder in the mod assets.
	 */
	private boolean loadDefinitionsRoot (final Path root) {
		final Path fPath = root.resolve("_modules.json");
		if (fPath != null && Files.exists(fPath)) {
			BufferedReader reader = null;
			try {
				reader = Files.newBufferedReader(fPath);
				final JsonObject json = JsonUtils.fromJson(GSON, reader, JsonObject.class);

				for (final Entry<String, JsonElement> module : json.entrySet()) {
					deserializeModule(module);
				}

			} catch (final IOException e) {
				Llog.err("Error loading _exports.json: ", e);
				return false;
			} finally {
				IOUtils.closeQuietly(reader);
			}
		}
		return true;
	}

	private void deserializeModule (final Entry<String, JsonElement> module) {
		final JsonElement moduleJson = module.getValue();
		Module moduleInstance;

		if (moduleJson.isJsonPrimitive() && moduleJson.getAsJsonPrimitive().isString()) {
			moduleInstance = new Module(moduleJson.getAsString());

		} else if (moduleJson.isJsonObject()) {
			final JsonObject moduleObject = moduleJson.getAsJsonObject();

			final JsonElement root = moduleObject.get("root");
			if (root != null && (!root.isJsonPrimitive() || !root.getAsJsonPrimitive().isString())) {
				Llog.warn("Invalid 'root' of module definition for '", module.getKey(), "'");
				return;
			}

			final JsonElement exportsJson = moduleObject.get("exports");
			if (exportsJson == null || !exportsJson.isJsonObject()) {
				Llog.warn("Invalid 'exports' of module definition for '", module.getKey(), "'");
				return;
			}

			final Map<String, String> exports = new HashMap<>();
			for (final Map.Entry<String, JsonElement> entry : exportsJson.getAsJsonObject().entrySet()) {
				if (!entry.getValue().isJsonPrimitive() || !entry.getValue().getAsJsonPrimitive().isString()) {
					Llog.warn("Invalid 'exports' of module definition for '", module.getKey(), "'");
					return;
				}

				exports.put(entry.getKey(), entry.getValue().getAsString());
			}

			moduleInstance = new Module(root == null ? "" : root.getAsString(), exports);

		} else {
			Llog.warn("Invalid module definition '", module.getKey(), "'");
			return;
		}

		MODULES.put(module.getKey(), moduleInstance);
	}

	/**
	 * Handles any files in the `/definitions/` folder in the mod assets.
	 * Any file ending with "ts" is copied to the run folder `/scripts/definitions/`
	 */
	private boolean loadDefinition (final Path root, final Path file) {
		Loader.instance().setActiveModContainer(modContainer);

		final String relative = root.relativize(file).toString();
		if (!"ts".equals(FilenameUtils.getExtension(file.toString())) || relative.startsWith("_"))
			return true;

		final String name = FilenameUtils.removeExtension(relative).replaceAll("\\\\", "/");

		try {
			final Path outputFile = Paths.get(DIRECTORY.toString(), modContainer.getModId(), name + ".ts");
			FileUtils.forceMkdirParent(outputFile.toFile());
			Files.copy(file, outputFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (final IOException e) {
			Llog.info(e);
		}

		return true;
	}
}
