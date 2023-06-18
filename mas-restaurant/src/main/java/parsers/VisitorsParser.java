package parsers;

import agents.VisitorAgent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import parsers.core.Parser;

public class VisitorsParser implements Parser<List<VisitorAgent>> {
    @Override
    public List<VisitorAgent> parse(final String line) {
        List<VisitorAgent> visitorAgents = new ArrayList<>();

        JSONObject json = new JSONObject(line);
        JSONArray visitorsJson = json.getJSONArray("visitors");
        for (int i = 0; i < visitorsJson.length(); i++) {
            JSONObject visitorJson = visitorsJson.getJSONObject(i);

            VisitorAgent visitorAgent = new VisitorAgent();

            visitorAgent.setId(visitorJson.getInt("id"));
            visitorAgent.setName(visitorJson.getString("name"));

            visitorAgents.add(visitorAgent);
        }

        return visitorAgents;
    }
}
