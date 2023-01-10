package works.chiri.fabricate.wrappers.recipe;

public class ItemStack {

	public static final ItemStack EMPTY = new ItemStack(net.minecraft.item.ItemStack.EMPTY);

	private net.minecraft.item.ItemStack stack;

	public ItemStack (final net.minecraft.item.ItemStack stack) {
		this.stack = stack;
	}

	public ItemStack (final net.minecraft.item.Item item, final int count, final int meta) {
		this.stack = new net.minecraft.item.ItemStack(item, count, meta);
	}

	public ItemStack (final Item item, final int count, final int meta) {
		this.stack = new net.minecraft.item.ItemStack(item.getItem(), count, meta);
	}

	public net.minecraft.item.ItemStack getStack () {
		return stack;
	}

	public Item getItem () {
		return new Item(stack.getItem());
	}

	public boolean isEmpty () {
		return this.stack.isEmpty();
	}

	public int getCount () {
		return this.stack.getCount();
	}

	public ItemStack setCount (final int newCount) {
		this.stack.setCount(newCount);
		return this;
	}

	public int getMetadata () {
		return this.stack.getMetadata();
	}
}
