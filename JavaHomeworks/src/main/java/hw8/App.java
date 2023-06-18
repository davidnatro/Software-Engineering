package hw8;

import hw8.constants.Errors;
import hw8.constants.Messages;
import hw8.exceptions.UserAlreadyExistsException;
import hw8.server.Server;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean enteredValidName = false;
        User user = null;

        ExecutorService es = Executors.newSingleThreadExecutor();

        do {
            System.out.print(Messages.NAME_INPUT);
            String input = scanner.nextLine();

            try {
                user = new User(input);
                enteredValidName = true;
            } catch (final IOException exception) {
                System.out.println(exception.getMessage());
            } catch (final UserAlreadyExistsException exception) {
                System.out.println(Errors.USER_ALREADY_EXISTS);
            }
        } while (!enteredValidName);

        es.execute(new Thread(user));
    }
}
