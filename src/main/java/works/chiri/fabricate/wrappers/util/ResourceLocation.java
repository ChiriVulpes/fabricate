package works.chiri.fabricate.wrappers.util;

public class ResourceLocation extends net.minecraft.util.ResourceLocation {

	public ResourceLocation (final net.minecraft.util.ResourceLocation location) {
		super(location.getResourceDomain(), location.getResourcePath());
	}

	public ResourceLocation (final String name) {
		super(name);
	}

	public ResourceLocation (final String domain, final String path) {
		super(domain, path);
	}

	public String getPath () {
		return this.getResourcePath();
	}

	public String getDomain () {
		return this.getResourceDomain();
	}
}
