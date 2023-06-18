package hw6;

public class Producer implements Runnable{
    private final static int maxValue = 1000;
    private final static int minValue = -1000;
    private int generateRandomNumber() {
        return (int) ((Math.random() * (maxValue - minValue)) + minValue);
    }

    @Override
    public void run() {
        while (true) {
            try {
                int generatedNumber = generateRandomNumber();
                System.out.println("Produced: " + generatedNumber);
                Data.BUFFER.put(generatedNumber);
                Thread.sleep(500);
            } catch (final InterruptedException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
