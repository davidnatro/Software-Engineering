package initializers;

import agents.DishAgent;
import constants.Paths;
import initializers.core.DataInitializer;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class DishesInitializerTests {
    @Test
    public void initializeTest() throws IOException {
        DataInitializer<DishAgent> initializer = DishesInitializer.getInstance();
        List<DishAgent> dishAgents = initializer.initializeData(Paths.DISHES_PATH);

        for (int i = 1; i <= 3; i++) {
            Assertions.assertEquals(dishAgents.get(i - 1).getId(), i);
        }
    }
}
