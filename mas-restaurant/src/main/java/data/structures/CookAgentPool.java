package data.structures;

import agents.CookAgent;
import data.structures.objectpool.core.ObjectPool;
import java.util.List;

public class CookAgentPool extends ObjectPool<CookAgent> {
    private CookAgentPool(int poolSize) throws IllegalArgumentException {
        super(poolSize);
    }

    public CookAgentPool(List<CookAgent> cooks) {
        this(cooks.size());
        objects.addAll(cooks);
    }
}
