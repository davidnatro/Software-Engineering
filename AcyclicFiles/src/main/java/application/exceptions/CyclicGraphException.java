package application.exceptions;

/**
 * Исключение выбрасывающееся при циклической зависимости файлов.
 */
public class CyclicGraphException extends Exception {
    public CyclicGraphException(String message) {
        super(message);
    }
}
