package parsers;

import agents.ProductAgent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.core.Parser;

public class ProductsParser implements Parser<List<ProductAgent>> {
    @Override
    public List<ProductAgent> parse(String line) {
        List<ProductAgent> productsAgents = new ArrayList<>();

        JSONObject json = new JSONObject(line);
        JSONArray productsJson = json.getJSONArray("products");
        for (int i = 0; i < productsJson.length(); i++) {
            JSONObject productJson = productsJson.getJSONObject(i);

            ProductAgent productAgent = new ProductAgent();

            productAgent.setId(productJson.getInt("id"));
            productAgent.setName(productJson.getString("name"));
            productAgent.setQuantity(productJson.getInt("quantity"));

            productsAgents.add(productAgent);
        }

        return productsAgents;
    }
}
