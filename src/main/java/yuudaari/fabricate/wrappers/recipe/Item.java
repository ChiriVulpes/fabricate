package yuudaari.fabricate.wrappers.recipe;

public class Item {

	private final net.minecraft.item.Item item;

	public Item (final net.minecraft.item.Item item) {
		this.item = item;
	}

	public net.minecraft.item.Item getItem () {
		return item;
	}

	public boolean isDamageable () {
		return item.isDamageable();
	}
}
