package agents;

import constants.Paths;
import data.structures.CookAgentPool;
import data.structures.objectpool.core.ObjectPool;
import initializers.CooksInitializer;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessAgent {
    private static final ObjectPool<CookAgent> cookers;

    static {
        List<CookAgent> cookAgents = CooksInitializer.getInstance()
                                                     .initializeData(Paths.COOKS_PATH);
        cookers = new CookAgentPool((cookAgents != null) ? cookAgents : List.of());
    }

    public void startCookingProcess(DishAgent dish) {
        CookAgent cookAgent = cookers.getObject();

        cookAgent.cook(dish);

        cookers.returnObject(cookAgent);
    }
}
