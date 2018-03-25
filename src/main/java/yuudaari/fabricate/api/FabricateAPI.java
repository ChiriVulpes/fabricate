package yuudaari.fabricate.api;

import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import yuudaari.fabricate.recipe.Ingredient;

public class FabricateAPI {

	private IApiWrapper wrapper;

	public FabricateAPI (final IApiWrapper wrapper) {
		this.wrapper = wrapper;
	}

	public static FabricateAPI Fabricate = null;
	public static RegistryEvents RegistryEvent = new RegistryEvents();


	public void on (final Object event, final Consumer<Object> handler) {
		wrapper.addEventHandler(event, handler);
	}

	private static final Pattern STACK_PATTERN = Pattern.compile("([a-z]+:[a-zA-Z0-9/_-]+)(?:[@:]([0-9]+))?");

	public ItemStack stack (final String name) {
		return stack(name, 1);
	}

	public ItemStack stack (final String name, final int count) {
		final Matcher matcher = STACK_PATTERN.matcher(name);
		if (!matcher.matches()) return ItemStack.EMPTY;

		final MatchResult result = matcher.toMatchResult();

		final Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(result.group(1)));

		final String metaGroup = result.group(2);
		final int meta = metaGroup == null || metaGroup.equals("*") ? 32767 : Integer.parseInt(metaGroup);

		// Llog.info(item.getRegistryName(), count, meta);

		return new ItemStack(item, count, meta);
	}

	public Ingredient ingredient (final Object... inputs) {
		return Ingredient.create(inputs);
	}
}
