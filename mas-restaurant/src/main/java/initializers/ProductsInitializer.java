package initializers;

import agents.ProductAgent;
import initializers.core.DataInitializer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import parsers.ProductsParser;
import parsers.core.Parser;

@Slf4j
public final class ProductsInitializer implements DataInitializer<ProductAgent> {
    private static ProductsInitializer instance;

    private ProductsInitializer() { }

    /**
     * Получение экземпляра-одиночки класса.
     *
     * @return экземпляр класса.
     */
    public static ProductsInitializer getInstance() {
        synchronized (ProductsInitializer.class) {
            if (instance == null) {
                synchronized (ProductsInitializer.class) {
                    instance = new ProductsInitializer();
                }
            }
        }

        return instance;
    }

    @Override
    public List<ProductAgent> initializeData(final Path path) {
        List<ProductAgent> productAgents;

        try {
            String line = Files.readString(path);
            Parser<List<ProductAgent>> parser = new ProductsParser();

            productAgents = parser.parse(line);
        } catch (final IOException exception) {
            log.error("Ошибка при чтении файла с данными о продуктах: {}", exception.getMessage());
            return null;
        }

        return productAgents;
    }
}
