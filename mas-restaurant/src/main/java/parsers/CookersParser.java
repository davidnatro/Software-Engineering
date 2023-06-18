package parsers;

import agents.CookAgent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.core.Parser;

public class CookersParser implements Parser<List<CookAgent>> {
    @Override
    public List<CookAgent> parse(final String line) {
        List<CookAgent> cookAgents = new ArrayList<>();

        JSONObject json = new JSONObject(line);
        JSONArray cookersJson = json.getJSONArray("cookers");
        for (int i = 0; i < cookersJson.length(); i++) {
            JSONObject cookerJson = cookersJson.getJSONObject(i);

            CookAgent cookAgent = new CookAgent();

            cookAgent.setId(cookerJson.getInt("id"));
            cookAgent.setName(cookerJson.getString("name"));

            cookAgents.add(cookAgent);
        }

        return cookAgents;
    }
}
