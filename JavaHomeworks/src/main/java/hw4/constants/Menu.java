package hw4.constants;

public final class Menu {
    private Menu() { }

    public final static String NAME = "Библиотека";

    public final static String MAIN_MENU = """
            /all - вывести все книги.
            /get {название книги} - взять книгу.
            /put {название книги} - вернуть книгу.
            /list - вывести взятые книги.
            /exit - выход.
            """;

    public final static String NO_BOOK_AT_LIBRARY = "Данной книги нет в библиотеке!";

    public final static String BOOK_TAKEN = "Вы взяли книгу!";

    public final static String USER_DONT_HAVE_BOOK = "У вас нет такой книги!";

    public final static String BOOK_ADDED_BACK = "Вы вернули книгу!";

    public final static String INCORRECT_INPUT = "Неверный ввод!";

    public final static String INPUT = "Ввод: ";
}
