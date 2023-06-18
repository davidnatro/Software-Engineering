package agents;

import constants.Paths;
import initializers.DishesInitializer;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SupervisorAgent {
    private static int idCount = 0;
    public static final ExecutorService executorService = Executors.newCachedThreadPool();

    @Getter private static final List<DishAgent> menu;
    private final int id = ++idCount;

    static {
        menu = DishesInitializer.getInstance().initializeData(Paths.DISHES_PATH);
    }

    public void takeOrder(OrderAgent orderAgent) {
        log.info(this + " took order from Visitor #" + orderAgent.getVisitorId());
        ProcessAgent processAgent = new ProcessAgent();

        orderAgent.getDishes().entrySet().parallelStream().forEach(dish -> {
            executorService.execute(() -> {
                processAgent.startCookingProcess(dish.getKey());
            });
        });
    }

    @Override
    public String toString() {
        return "Supervisor #" + id;
    }
}

