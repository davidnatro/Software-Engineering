package hw6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public void run() {
        ExecutorService es = Executors.newCachedThreadPool();

        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());

        es.execute(producer);
        es.execute(consumer);

        es.shutdown();
    }
}
