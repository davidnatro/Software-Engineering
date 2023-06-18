package game.constants;

/**
 * Сообщения-подсказки, выводящиеся в главном меню.
 */
public final class Menu {
    private Menu() { }

    public final static String GREETING = "Добро пожаловать в реверси!";

    public final static String NAME = "Главное меню:";

    public final static String CHOOSE = "Выбор: ";

    public final static String MENU = """
            1. Играть
            2. Лучший результат
            3. Выход
            """;

    public final static String GAME_MODES = """
            1. Играть против игрока
            2. Тренировка с ботами
            """;

    public final static String BOT_MODES = """
            1. Легкий бот
            2. Профессиональный бот
            """;

    public final static String BEST_RESULT = "Лучший результат: ";

    public final static String FAREWELL = "Заходите ещё!";
}
