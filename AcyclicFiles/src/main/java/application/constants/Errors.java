package application.constants;

public final class Errors {
    private Errors() { }

    public static final String INCORRECT_DIRECTORY = "Такая директория не существует!";

    public static final String ACCESS_DENIED = "К данной директории закрыт доступ!";

    public static final String NODE_NOT_IN_GRAPH = "Данный файл отсутвует в графе!";

    public static final String FILE_IS_DIR_OR_NOT_FOUND = "Файл не найден, либо является директорией!";

    public static final String CYCLIC_GRAPH = "Образовался циклический граф!";
}
