package initializers;

import agents.VisitorAgent;
import constants.Paths;
import initializers.core.DataInitializer;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class VisitorsInitializerTests {
    @Test
    public void initializeTest() throws IOException {
        DataInitializer<VisitorAgent> initializer = VisitorsInitializer.getInstance();
        List<VisitorAgent> visitorAgents = initializer.initializeData(Paths.VISITORS_PATH);

        for (int i = 1; i <= 3; i++) {
            Assertions.assertEquals(visitorAgents.get(i - 1).getId(), i);
        }
    }
}
