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

	public DefinitionManager (final String directory) {
		DIRECTORY = Paths.get(directory, ScriptManager.SCRIPTS_FOLDER, "definitions");
	}

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private ModContainer modContainer;
	private final Map<String, String> MODULES = new HashMap<>();

	public Map<String, String> getModuleMap () {
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
					final JsonElement modulePath = module.getValue();
					if (!modulePath.isJsonPrimitive() || !modulePath.getAsJsonPrimitive().isString()) {
						Llog.warn("Module must be mapped to a string path");
						continue;
					}

					MODULES.put(module.getKey(), modulePath.getAsString());
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
