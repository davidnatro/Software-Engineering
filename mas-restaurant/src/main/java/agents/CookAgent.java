package agents;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookAgent {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Getter @Setter private int id;
    @Getter @Setter private String name;

    /**
     * Повар готовит, получает блюдо, и обращается к нужному Equipment. Equipment хранится в
     * блюде(например, по getRecipe). А по getTime получает время, которое затратит Equipment
     *
     * @param dish Блюдо.
     */
    public synchronized void cook(DishAgent dish) {
        log.info("Cook {} started cooking {}", name, dish.getName());

        final Future<Boolean> dishCooked = executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                if (!StorageAgent.takeProducts(dish)) {
                    return false;
                }

                Thread.sleep(dish.getCookingTime() * 1000L);
                return true;
            }
        });

        boolean result = false;
        while (!dishCooked.isDone()) {
            try {
                Thread.sleep(1000L);
                result = dishCooked.get();
            } catch (final InterruptedException | ExecutionException exception) {
                log.error(exception.getMessage());
            }
        }

        if (result) {
            log.info("Cook {} finished cooking {}", name, dish.getName());
        } else {
            log.info("Cook {} failed to cook {}", name, dish.getName());
        }
    }

    @Override
    public String toString() {
        return "#" + id;
    }
}
