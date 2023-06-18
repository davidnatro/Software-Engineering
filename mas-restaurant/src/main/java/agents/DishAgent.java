package agents;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DishAgent {
    @Getter @Setter private int id;
    @Getter @Setter private String name;
    @Getter @Setter private int price;
    @Getter @Setter private boolean inStock;

    /**
     * id продукта - требуемое количество.
     */
    @Getter @Setter private Map<Integer, Integer> neededProducts = new HashMap<>();

    @Getter @Setter private int cookingTime;

    @Override
    public String toString() {
        return "Dish: " + getName();
    }
}
