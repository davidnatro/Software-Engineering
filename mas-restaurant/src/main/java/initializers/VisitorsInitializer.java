package initializers;

import agents.VisitorAgent;
import initializers.core.DataInitializer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import parsers.VisitorsParser;
import parsers.core.Parser;

@Slf4j
public final class VisitorsInitializer implements DataInitializer<VisitorAgent> {
    private static VisitorsInitializer instance;

    private VisitorsInitializer() { }

    /**
     * Получение экземпляра-одиночки класса.
     *
     * @return экземпляр класса.
     */
    public static VisitorsInitializer getInstance() {
        synchronized (VisitorsInitializer.class) {
            if (instance == null) {
                synchronized (VisitorsInitializer.class) {
                    instance = new VisitorsInitializer();
                }
            }
        }

        return instance;
    }

    @Override
    public List<VisitorAgent> initializeData(final Path path) {
        List<VisitorAgent> visitorAgents;

        try {
            String line = Files.readString(path);
            Parser<List<VisitorAgent>> parser = new VisitorsParser();

            visitorAgents = parser.parse(line);
        } catch (final IOException exception) {
            log.error("Ошибка при чтении файла с данными о посетителях: {}",
                    exception.getMessage());
            return null;
        }

        return visitorAgents;
    }
}
