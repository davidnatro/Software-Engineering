package initializers;

import agents.CookAgent;
import initializers.core.DataInitializer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import parsers.CookersParser;
import parsers.core.Parser;

@Slf4j
public final class CooksInitializer implements DataInitializer<CookAgent> {
    private static CooksInitializer instance;

    private CooksInitializer() { }

    /**
     * Получение экземпляра-одиночки класса.
     *
     * @return экземпляр класса.
     */
    public static CooksInitializer getInstance() {
        synchronized (CooksInitializer.class) {
            if (instance == null) {
                synchronized (CooksInitializer.class) {
                    instance = new CooksInitializer();
                }
            }
        }

        return instance;
    }

    @Override
    public List<CookAgent> initializeData(final Path path) {
        List<CookAgent> cookAgents;

        try {
            String line = Files.readString(path);
            Parser<List<CookAgent>> parser = new CookersParser();

            cookAgents = parser.parse(line);
        } catch (final IOException exception) {
            log.error("Ошибка при чтении файла с данными о поварах: {}", exception.getMessage());
            return null;
        }

        return cookAgents;
    }
}
