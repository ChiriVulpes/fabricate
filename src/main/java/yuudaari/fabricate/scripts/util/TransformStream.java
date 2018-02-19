package yuudaari.fabricate.scripts.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TransformStream extends InputStream {

	private final InputStream STREAM;
	private final Map<byte[], byte[]> REPLACEMENTS = new HashMap<>();
	private ReplaceFilterInputStream replaceStream;

	private TransformStream (InputStream stream) {
		this.STREAM = stream;
	}

	private void addReplacement (final String from, final String to) {
		REPLACEMENTS.put(from.getBytes(), to.getBytes());
	}

	private void init () {
		replaceStream = new ReplaceFilterInputStream(STREAM, REPLACEMENTS);
	}

	@Override
	public int read () throws IOException {
		return replaceStream.read();
	}

	public static class Factory {

		private final TransformStream STREAM;

		public Factory (final InputStream stream) {
			STREAM = new TransformStream(stream);
		}

		public Factory transform (final String from, final String to) {
			STREAM.addReplacement(from, to);
			return this;
		}

		public TransformStream create () {
			STREAM.init();
			return STREAM;
		}
	}
}
