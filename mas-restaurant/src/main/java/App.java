import agents.SupervisorAgent;
import agents.VisitorAgent;
import constants.Paths;
import data.structures.SupervisorAgentPool;
import data.structures.objectpool.core.ObjectPool;
import initializers.VisitorsInitializer;
import java.util.List;

public class App implements Runnable {
    private final ObjectPool<SupervisorAgent> supervisorAgentPool;

    private final List<VisitorAgent> visitorAgents;

    public App() {
        this.supervisorAgentPool = new SupervisorAgentPool(5);

        this.visitorAgents = VisitorsInitializer.getInstance().initializeData(Paths.VISITORS_PATH);
    }

    /**
     * Точка входа в мультиагентную систему управления рестораном. Посетители, обращаясь к
     * официантам, оформляют заказы.
     */
    @Override
    public void run() {
        visitorAgents.parallelStream().forEach(visitorAgent -> {
            SupervisorAgent supervisorAgent = supervisorAgentPool.getObject();

            visitorAgent.placeOrder(SupervisorAgent.getMenu(), supervisorAgent);

            supervisorAgentPool.returnObject(supervisorAgent);
        });
    }
}
