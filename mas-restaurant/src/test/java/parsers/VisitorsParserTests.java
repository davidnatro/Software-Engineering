package parsers;

import agents.VisitorAgent;
import constants.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import parsers.core.Parser;

public class VisitorsParserTests {
    @Test
    public void parsingTest() throws IOException {
        final String data = Files.readString(Paths.VISITORS_PATH);
        final Parser<List<VisitorAgent>> parser = new VisitorsParser();

        final List<VisitorAgent> visitors = parser.parse(data);

        Assertions.assertEquals(visitors.get(0).getId(), 1);
        Assertions.assertEquals(visitors.get(1).getId(), 2);
        Assertions.assertEquals(visitors.get(2).getId(), 3);

        Assertions.assertEquals(visitors.get(0).getName(), "visitor #1");
        Assertions.assertEquals(visitors.get(1).getName(), "visitor #2");
        Assertions.assertEquals(visitors.get(2).getName(), "visitor #3");
    }
}
