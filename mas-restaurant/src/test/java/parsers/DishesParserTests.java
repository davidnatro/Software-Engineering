package parsers;

import agents.DishAgent;
import constants.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import parsers.core.Parser;

public class DishesParserTests {
    @Test
    public void parsingTest() throws IOException {
        final String data = Files.readString(Paths.DISHES_PATH);
        final Parser<List<DishAgent>> parser = new DishesParser();

        final List<DishAgent> dishes = parser.parse(data);

        Assertions.assertEquals(dishes.get(0).getId(), 1);
        Assertions.assertEquals(dishes.get(1).getId(), 2);
        Assertions.assertEquals(dishes.get(2).getId(), 3);

        Assertions.assertEquals(dishes.get(0).getName(), "dish #1");
        Assertions.assertEquals(dishes.get(1).getName(), "dish #2");
        Assertions.assertEquals(dishes.get(2).getName(), "dish #3");

        Assertions.assertEquals(dishes.get(0).getPrice(), 100);
        Assertions.assertEquals(dishes.get(1).getPrice(), 200);
        Assertions.assertEquals(dishes.get(2).getPrice(), 300);

        Assertions.assertTrue(dishes.get(0).isInStock());
        Assertions.assertTrue(dishes.get(1).isInStock());
        Assertions.assertFalse(dishes.get(2).isInStock());

        Assertions.assertTrue(dishes.get(0).getNeededProducts().keySet().stream().toList()
                                    .containsAll(List.of(1, 2)));

        Assertions.assertTrue(dishes.get(1).getNeededProducts().keySet().stream().toList()
                                    .contains(1));

        Assertions.assertTrue(dishes.get(2).getNeededProducts().keySet().stream().toList()
                                    .containsAll(List.of(2, 3, 1)));
    }
}
