package yuudaari.fabricate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import yuudaari.fabricate.api.FabricateAPI;
import yuudaari.fabricate.api.IApiWrapper;
import yuudaari.fabricate.recipe.RecipeRegistry;
import yuudaari.fabricate.scripts.ScriptManager;

@Mod(modid = Fabricate.MODID, name = Fabricate.NAME, version = "@VERSION@", acceptedMinecraftVersions = "[1.12.2]")
@Mod.EventBusSubscriber
public class Fabricate implements IApiWrapper {

	//////////////////////////////////////
	// STATIC
	//

	public static final String NAME = "Fabricate";
	public static final String MODID = "fabricate";

	@Mod.Instance(MODID) public static Fabricate INSTANCE;


	//////////////////////////////////////
	// INSTANCE
	//

	public ScriptManager scripts;
	private final Map<Integer, List<Consumer<Object>>> eventHandlers = new HashMap<>();


	//////////////////////////////////////
	// MAIN
	//

	@EventHandler
	public void preInit (final FMLPreInitializationEvent event) {
		FabricateAPI.Fabricate = new FabricateAPI(this);

		final String directory = event.getModConfigurationDirectory().getParentFile().getAbsolutePath();
		scripts = new ScriptManager(directory);
		scripts.load();
	}


	//////////////////////////////////////
	// EVENTS
	//

	@Override
	public void addEventHandler (final int event, final Consumer<Object> handler) {
		List<Consumer<Object>> handlerList = eventHandlers.get(event);
		if (handlerList == null) eventHandlers.put(event, handlerList = new ArrayList<>());

		handlerList.add(handler);
	}

	public void triggerEvent (final int event, final Object argument) {
		final List<Consumer<Object>> handlerList = eventHandlers.get(event);
		if (handlerList == null) return;

		for (final Consumer<Object> handler : handlerList) {
			handler.accept(argument);
		}
	}


	//////////////////////////////////////
	// REGISTRATION
	//

	@SubscribeEvent
	public static void registerRecipes (final RegistryEvent.Register<IRecipe> event) {
		INSTANCE.triggerEvent(FabricateAPI.RegistryEvent.Recipes, new RecipeRegistry(event.getRegistry()));

		final IForgeRegistryModifiable<IRecipe> REGISTRY = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
		for (final IRecipe recipe : REGISTRY.getValues()) {
			INSTANCE.triggerEvent(FabricateAPI.RegistryEvent.RegisteredRecipe, recipe);
		}
	}
}
