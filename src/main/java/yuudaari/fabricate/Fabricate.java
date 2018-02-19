package yuudaari.fabricate;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yuudaari.fabricate.scripts.ScriptManager;

@Mod(modid = Fabricate.MODID, name = Fabricate.NAME, version = "@VERSION@", acceptedMinecraftVersions = "[1.12.2]")
@Mod.EventBusSubscriber
public class Fabricate {

	public static final String NAME = "Fabricate";
	public static final String MODID = "fabricate";

	public ScriptManager scripts;

	@EventHandler
	public void preInit (final FMLPreInitializationEvent event) {
		final String directory = event.getModConfigurationDirectory().getParentFile().getAbsolutePath();
		scripts = new ScriptManager(directory);
		scripts.load();
	}



	@SubscribeEvent
	public static void registerRecipes (final RegistryEvent.Register<IRecipe> event) {

	}
}
