package parsers;

import agents.ProductAgent;
import constants.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import parsers.core.Parser;

public class ProductsParserTests {
    @Test
    public void parsingTest() throws IOException {
        final String data = Files.readString(Paths.PRODUCTS_PATH);
        final Parser<List<ProductAgent>> parser = new ProductsParser();

        final List<ProductAgent> products = parser.parse(data);

        Assertions.assertEquals(products.get(0).getId(), 1);
        Assertions.assertEquals(products.get(1).getId(), 2);
        Assertions.assertEquals(products.get(2).getId(), 3);

        Assertions.assertEquals(products.get(0).getName(), "product #1");
        Assertions.assertEquals(products.get(1).getName(), "product #2");
        Assertions.assertEquals(products.get(2).getName(), "product #3");

        Assertions.assertEquals(products.get(0).getQuantity(), 5);
        Assertions.assertEquals(products.get(1).getQuantity(), 6);
        Assertions.assertEquals(products.get(2).getQuantity(), 2);
    }
}
