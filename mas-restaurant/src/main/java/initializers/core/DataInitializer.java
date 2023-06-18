package initializers.core;

import java.nio.file.Path;
import java.util.List;

public interface DataInitializer<T> {
    List<T> initializeData(final Path path);
}
