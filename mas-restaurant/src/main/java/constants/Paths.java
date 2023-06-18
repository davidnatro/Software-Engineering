package constants;

import java.nio.file.Path;

public final class Paths {
    private Paths() { }

    public static final Path VISITORS_PATH = Path.of("initial_data", "visitors.txt");

    public static final Path DISHES_PATH = Path.of("initial_data", "dishes.txt");

    public static final Path PRODUCTS_PATH = Path.of("initial_data", "products.txt");

    public static final Path COOKS_PATH = Path.of("initial_data", "cookers.txt");
}
