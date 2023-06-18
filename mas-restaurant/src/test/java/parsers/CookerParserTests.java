package parsers;

import agents.CookAgent;
import constants.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import parsers.core.Parser;

public class CookerParserTests {
    @Test
    public void parsingTest() throws IOException {
        final String data = Files.readString(Paths.COOKS_PATH);
        final Parser<List<CookAgent>> parser = new CookersParser();

        final List<CookAgent> cookers = parser.parse(data);

        Assertions.assertEquals(cookers.get(0).getId(), 1);
        Assertions.assertEquals(cookers.get(1).getId(), 2);
        Assertions.assertEquals(cookers.get(2).getId(), 3);

        Assertions.assertEquals(cookers.get(0).getName(), "Ivanov A. S.");
        Assertions.assertEquals(cookers.get(1).getName(), "Petrov I. V.");
        Assertions.assertEquals(cookers.get(2).getName(), "Sidorov G. S.");
    }
}
