package data.structures;

import agents.SupervisorAgent;
import data.structures.objectpool.core.ObjectPool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SupervisorAgentPool extends ObjectPool<SupervisorAgent> {
    public SupervisorAgentPool(final int poolSize)
            throws IllegalArgumentException {
        super(poolSize);

        for (int i = 0; i < poolSize; i++) {
            objects.add(new SupervisorAgent());
        }
    }
}