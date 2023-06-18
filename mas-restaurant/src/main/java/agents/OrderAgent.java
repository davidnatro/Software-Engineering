package agents;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderAgent {
    @Getter private final int visitorId;
    @Getter private final Map<DishAgent, Integer> dishes;

    public OrderAgent(int visitorId) {
        this.visitorId = visitorId;
        dishes = new HashMap<>();
    }

    /**
     * Добавляем блюдо или увеличиваем количество таких блюд.
     *
     * @param dish Блюдо.
     */
    public void add(DishAgent dish) {
        if (dishes.containsKey(dish)) {
            dishes.put(dish, dishes.get(dish) + 1);
        } else {
            dishes.put(dish, 1);
        }
    }
}
