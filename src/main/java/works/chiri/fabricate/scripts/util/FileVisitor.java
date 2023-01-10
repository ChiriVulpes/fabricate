package works.chiri.fabricate.scripts.util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class FileVisitor extends SimpleFileVisitor<Path> {

	private final PathMatcher matcher;
	private final IVisit visitMethod;

	public FileVisitor (final String glob, final IVisit onVisit) {
		matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
		this.visitMethod = onVisit;
	}

	public void walk (final Path path) throws IOException {
		Files.walkFileTree(path, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, this);
	}

	@Override
	public FileVisitResult visitFile (final Path path, final BasicFileAttributes attrs) throws IOException {
		if (matcher.matches(path)) visitMethod.visit(path);
		return FileVisitResult.CONTINUE;
	}

	public static interface IVisit {

		public void visit (final Path path) throws IOException;
	}
}
