package game.exceptions;

/**
 * Исключение выбрасывается при попытке создания игры с игроками, имеющими одинаковые цвета.
 */
public class IdenticalColorsException extends Exception{
    public IdenticalColorsException(String message) {
        super(message);
    }
}
