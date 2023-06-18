package hw4;

import hw4.constants.Commands;
import hw4.constants.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private final Library library = new Library();
    private final List<Book> takenBooks = new ArrayList<Book>();

    private void printTakenBooks() {
        if (takenBooks.size() == 0) {
            System.out.println("У вас нет книг!\n");
            return;
        }

        StringBuilder result = new StringBuilder();
        int count = 1;

        for (final Book book : takenBooks) {
            result.append("#").append(count++).append(" ").append("\n");
            result.append("Название: ").append(book.getBookName()).append("\n");
            result.append("Автор: ").append(book.getAuthorName()).append("\n");
            result.append("Год издания: ").append(book.getYearOfPublishing()).append("\n");
        }

        System.out.println(result);
    }

    private Book findBook(String bookName) {
        for (final Book book : takenBooks) {
            if (book.getBookName().equals(bookName)) {
                return book;
            }
        }

        return null;
    }

    private String concatBookName(String[] splittedString) {
        StringBuilder result = new StringBuilder();
        for(int i = 1; i < splittedString.length; i++) {
          result.append(splittedString[i]);

          if (i != splittedString.length - 1) {
              result.append(" ");
          }
        }

        return result.toString();
    }

    private void fillLibrary() {
        for(int i = 1; i <= 10; i++) {
          Book book = new Book("Книга #" + i, "Автор #" + i, 1870 + i);
          for(int j = 1; j <= i; j++) {
            library.addBookToLibrary(book);
          }
        }
    }

    public void run() {
        fillLibrary();

        Scanner scanner = new Scanner(System.in);

        System.out.println(Menu.NAME + "\n");
        System.out.println(Menu.MAIN_MENU);
        System.out.print(Menu.INPUT);

        String[] commands = scanner.nextLine().split(" ");
        System.out.println();

        Book book;

        while (!commands[0].equals(Commands.EXIT)) {
            switch (commands[0]) {
                case Commands.ALL -> System.out.println(library);
                case Commands.GET -> {
                    book = library.getBookByName(concatBookName(commands));
                    if (book == null) {
                        System.out.println(Menu.NO_BOOK_AT_LIBRARY + "\n");
                    } else {
                        takenBooks.add(book);
                        System.out.println(Menu.BOOK_TAKEN + "\n");
                    }
                }
                case Commands.PUT-> {
                    book = findBook(concatBookName(commands));
                    if (book == null) {
                        System.out.println(Menu.USER_DONT_HAVE_BOOK + "\n");
                    } else {
                        takenBooks.remove(book);
                        library.addBookToLibrary(book);
                        System.out.println(Menu.BOOK_ADDED_BACK + "\n");
                    }
                }
                case Commands.LIST -> printTakenBooks();
                default -> System.out.println(Menu.INCORRECT_INPUT + "\n");
            }

            System.out.println(Menu.MAIN_MENU);
            System.out.print(Menu.INPUT);
            commands = scanner.nextLine().split(" ");
            System.out.println();
        }
    }
}
