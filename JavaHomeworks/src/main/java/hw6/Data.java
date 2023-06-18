package hw6;

import java.util.concurrent.LinkedBlockingQueue;

public final class Data {
    private Data() { }

    public final static int MAX_QUEUE_SIZE = 15;

    public volatile static LinkedBlockingQueue<Integer> BUFFER = new LinkedBlockingQueue<>(MAX_QUEUE_SIZE);
}
