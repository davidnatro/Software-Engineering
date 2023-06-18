package hw7;

import hw7.constants.Commands;
import hw7.constants.Errors;
import hw7.constants.Inputs;
import hw7.constants.Menu;

import java.util.Scanner;

public class App {
    private final static Scanner scanner = new Scanner(System.in);

    private String[] sliceArray(int startInclusive, int endExclusive, final String[] array) {
        String[] result = new String[endExclusive - startInclusive];

        for (int i = startInclusive, index = 0; i < endExclusive; i++, index++) {
          result[index] = array[i];
        }

        return result;
    }

    public void run() {
        User user = new User();
        String[] input;

        System.out.println(Menu.HELP_INFO);


        do {
            System.out.print(Inputs.INPUT);
            input = scanner.nextLine().split(" ");
            if (input.length == 0) {
                continue;
            }

            switch (input[0]) {
                case Commands.DESTINATION -> {
                    if (input.length == 2) {
                        user.setPath(input[1]);
                    }
                }
                case Commands.LOAD -> {
                    if (user.getPath() == null) {
                        System.out.println(Errors.PATH_NOT_SET);
                        continue;
                    }

                    new Thread(new Downloader(user.getPath(), sliceArray(1, input.length, input))).start();
                }
                case Commands.HELP -> System.out.println(Menu.HELP_INFO);
                case Commands.EXIT -> {
                    return;
                }
            }
        } while (true);
    }
}
