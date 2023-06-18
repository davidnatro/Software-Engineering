package data.structures.objectpool.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ObjectPool<T> {
    protected final Queue<T> objects;
    protected final List<T> inUseObjects;

    protected ObjectPool(final int poolSize) throws IllegalArgumentException {
        if (poolSize <= 0) {
            throw new IllegalArgumentException("Invalid pool size!");
        }

        objects = new ArrayDeque<T>(poolSize);
        inUseObjects = new ArrayList<T>(poolSize);
    }

    public synchronized T getObject() {
        T object;
        while ((object = objects.poll()) == null) {
            try {
                wait();
            } catch (final InterruptedException exception) {
                log.error(exception.getMessage());
            }
        }

        inUseObjects.add(object);

        return object;
    }

    public synchronized void returnObject(final T object) {
        if (object == null) {
            return;
        }

        inUseObjects.remove(object);
        objects.add(object);

        notifyAll();
    }
}
