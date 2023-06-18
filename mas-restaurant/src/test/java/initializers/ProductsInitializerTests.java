package initializers;

import agents.ProductAgent;
import constants.Paths;
import initializers.core.DataInitializer;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ProductsInitializerTests {
    @Test
    public void initializeTest() throws IOException {
        DataInitializer<ProductAgent> initializer = ProductsInitializer.getInstance();
        List<ProductAgent> productAgents = initializer.initializeData(Paths.PRODUCTS_PATH);

        for (int i = 1; i <= 3; i++) {
            Assertions.assertEquals(productAgents.get(i - 1).getId(), i);
        }
    }
}