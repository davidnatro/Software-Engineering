package application.utils;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    private FileUtils() { }

    public static boolean isValidDirectory(final Path directory) {
        return directory.toFile().isDirectory();
    }

    public static boolean isValidFile(final Path file) {
        return file.toFile().isFile();
    }

    public static boolean isAccessible(final Path file) {
        return Files.isReadable(file);
    }
}