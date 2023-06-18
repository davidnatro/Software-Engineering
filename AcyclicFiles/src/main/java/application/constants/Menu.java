package application.constants;

public final class Menu {
    private Menu() {
    }

    public static final String QUIT_COMMAND = "/q";

    public static final String ROOT_DIRECTORY_INPUT =
            String.format("Введите путь до корневой директории (%s - выход):", QUIT_COMMAND);

    public static final String QUITING = "Выход...";
}
