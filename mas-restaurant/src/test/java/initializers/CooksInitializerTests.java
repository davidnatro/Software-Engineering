package initializers;

import agents.CookAgent;
import constants.Paths;
import initializers.core.DataInitializer;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CooksInitializerTests {
    @Test
    public void initializeTest() throws IOException {
        DataInitializer<CookAgent> initializer = CooksInitializer.getInstance();
        List<CookAgent> cookAgents = initializer.initializeData(Paths.COOKS_PATH);

        for (int i = 1; i <= 3; i++) {
            Assertions.assertEquals(cookAgents.get(i - 1).getId(), i);
        }
    }
}
