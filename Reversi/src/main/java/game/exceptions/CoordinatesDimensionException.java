package game.exceptions;

/**
 * Исключение выбрасывается, когда размерность координат не равна 2.
 */
public class CoordinatesDimensionException extends Exception{
    public CoordinatesDimensionException(String message) {
        super(message);
    }
}
