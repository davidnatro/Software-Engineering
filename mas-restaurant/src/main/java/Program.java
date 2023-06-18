import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        executorService.execute(new App());
        executorService.shutdown();
    }
}
