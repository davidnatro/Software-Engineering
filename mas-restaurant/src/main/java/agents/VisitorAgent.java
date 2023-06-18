package agents;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VisitorAgent {
    private static final Random random = new SecureRandom();
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static int idCount = 0;

    @Getter @Setter private int id = ++idCount;
    @Getter @Setter private String name;

    public void placeOrder(List<DishAgent> menu, SupervisorAgent supervisorAgent) {
        OrderAgent order = new OrderAgent(id);
        int orderSize = menu.size();
        for (int i = 0; i < orderSize; i++) {
            DishAgent dish = menu.get(random.nextInt(menu.size()));
            order.add(dish);
            log.info(this + " placed order for " + dish.getName());
        }

        executorService.execute(() -> supervisorAgent.takeOrder(order));
    }

    @Override
    public String toString() {
        return "Visitor #" + getId();
    }
}
