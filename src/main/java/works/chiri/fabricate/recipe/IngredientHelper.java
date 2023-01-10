package works.chiri.fabricate.recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import works.chiri.fabricate.wrappers.recipe.ItemStack;

public class IngredientHelper {

	public static List<ItemStack> getStacks (Object input) {
		if (input instanceof Iterable<?>) {
			final List<ItemStack> result = new ArrayList<>();
			for (final Object obj : (Iterable<?>) input) {
				result.addAll(getStacks(obj));
			}
			return result;
		}

		if (input instanceof String) {
			String inputString = (String) input;

			// ore dictionary
			if (inputString.charAt(0) == '$') {
				return OreDictionary.getOres(inputString.substring(1))
					.stream()
					.map(ItemStack::new)
					.collect(Collectors.toList());
			}

			input = getStack(inputString);
		}

		if (input instanceof net.minecraft.item.ItemStack) {
			input = new ItemStack((net.minecraft.item.ItemStack) input);
		}

		if (input instanceof ItemStack) {
			return Collections.singletonList((ItemStack) input);
		}

		throw new IllegalArgumentException("Ingredient of type '" + input.getClass() + "'");
	}

	private static final Pattern STACK_PATTERN = Pattern.compile("([a-z]+:[a-zA-Z0-9/_-]+)(?:[@:]([0-9]+))?");

	private static ItemStack getStack (final String input) {
		final Matcher matcher = STACK_PATTERN.matcher((String) input);
		if (!matcher.matches()) return ItemStack.EMPTY;

		final MatchResult result = matcher.toMatchResult();
		final Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(result.group(1)));

		final String metaGroup = result.group(2);
		final int meta = metaGroup == null || metaGroup.equals("*") ? OreDictionary.WILDCARD_VALUE : Integer.parseInt(metaGroup);

		// Llog.info(item.getRegistryName(), count, meta);

		return new ItemStack(item, 1, meta);
	}
}
