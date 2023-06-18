package game.exceptions;

/**
 * Исключение выбрасывается при попытке поставить фишку на запрещенную позицию.
 */
public class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }
}
