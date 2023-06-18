package agents;

import constants.Paths;
import initializers.ProductsInitializer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StorageAgent {
    private static final Map<Integer, ProductAgent> products;

    static {
        products = new HashMap<>();

        final List<ProductAgent> productsList = ProductsInitializer.getInstance().initializeData(
                Paths.PRODUCTS_PATH);

        if (productsList != null) {
            for (final ProductAgent productAgent : productsList) {
                products.put(productAgent.getId(), productAgent);
            }
        }
    }

    /**
     * Проверка наличия продуктов для приготовления блюда.
     *
     * @param dishAgent блюдо.
     * @return true, если есть все продукты, false, если нет.
     */
    private static synchronized boolean checkProducts(final DishAgent dishAgent) {
        for (final Entry<Integer, Integer> neededProduct : dishAgent.getNeededProducts()
                                                                    .entrySet()) {
            if (!products.containsKey(neededProduct.getKey())) {
                return false;
            }

            if (neededProduct.getValue() > products.get(neededProduct.getKey()).getQuantity()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Убирает продукты, требуемые для пригтовления блюда, из хранилища.
     *
     * @param dishAgent блюдо.
     */
    public static synchronized boolean takeProducts(final DishAgent dishAgent) {
        if (!checkProducts(dishAgent)) {
            return false;
        }

        for (final Entry<Integer, Integer> pair : dishAgent.getNeededProducts().entrySet()) {
            if (products.containsKey(pair.getKey())) {
                if (products.get(pair.getKey()).getQuantity() == pair.getValue()) {
                    products.remove(pair.getKey());
                } else if (products.get(pair.getKey()).getQuantity() > pair.getValue()) {
                    products.get(pair.getKey()).setQuantity(
                            products.get(pair.getKey()).getQuantity() - pair.getValue());
                }
            }
        }

        return true;
    }
}
