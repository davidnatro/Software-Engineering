package parsers;

import agents.DishAgent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.core.Parser;

public class DishesParser implements Parser<List<DishAgent>> {
    @Override
    public List<DishAgent> parse(final String line) {
        List<DishAgent> dishesAgents = new ArrayList<>();

        JSONObject json = new JSONObject(line);
        JSONArray dishesJson = json.getJSONArray("dishes");
        for (int i = 0; i < dishesJson.length(); i++) {
            JSONObject productJson = dishesJson.getJSONObject(i);

            DishAgent dishAgent = new DishAgent();

            dishAgent.setId(productJson.getInt("id"));
            dishAgent.setName(productJson.getString("name"));
            dishAgent.setPrice(productJson.getInt("price"));
            dishAgent.setInStock(productJson.getBoolean("in_stock"));
            dishAgent.setCookingTime(productJson.getInt("cooking_time_seconds"));

            JSONArray productsIdsJson = productJson.getJSONArray("products_ids");
            List<Integer> productsIds = new ArrayList<>();
            for (int j = 0; j < productsIdsJson.length(); j++) {
                productsIds.add(productsIdsJson.getInt(j));
            }

            JSONArray productsQuantitiesJson = productJson.getJSONArray("product_quantity");
            List<Integer> productsQuantities = new ArrayList<>();
            for (int j = 0; j < productsQuantitiesJson.length(); j++) {
                productsQuantities.add(productsQuantitiesJson.getInt(j));
            }

            for (int j = 0; j < productsIdsJson.length(); j++) {
                dishAgent.getNeededProducts().put(productsIds.get(j), productsQuantities.get(j));
            }

            dishesAgents.add(dishAgent);
        }

        return dishesAgents;
    }
}
