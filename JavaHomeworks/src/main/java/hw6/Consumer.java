package hw6;

public class Consumer implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                int consumedNumber = Data.BUFFER.take();
                System.out.println("Consumed: " + consumedNumber);
                Thread.sleep(1000);
            } catch (final InterruptedException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
