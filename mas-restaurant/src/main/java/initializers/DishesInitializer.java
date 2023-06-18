package initializers;

import agents.DishAgent;
import initializers.core.DataInitializer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import parsers.DishesParser;
import parsers.core.Parser;

@Slf4j
public final class DishesInitializer implements DataInitializer<DishAgent> {
    private static DishesInitializer instance;

    private DishesInitializer() { }

    /**
     * Получение экземпляра-одиночки класса.
     *
     * @return экземпляр класса.
     */
    public static DishesInitializer getInstance() {
        synchronized (DishesInitializer.class) {
            if (instance == null) {
                synchronized (DishesInitializer.class) {
                    instance = new DishesInitializer();
                }
            }
        }

        return instance;
    }

    @Override
    public List<DishAgent> initializeData(final Path path) {
        List<DishAgent> dishAgents;

        try {
            String line = Files.readString(path);
            Parser<List<DishAgent>> parser = new DishesParser();

            dishAgents = parser.parse(line);
        } catch (final IOException exception) {
            log.error("Ошибка при чтении файла с данными о блюдах: {}", exception.getMessage());
            return null;
        }

        return dishAgents;
    }
}
