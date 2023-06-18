package parsers.core;

public interface Parser<T> {
    /**
     * Парсинг строки начальных данных в определенный объект агента.
     *
     * @param line Строка начальных данных.
     * @return Объект агента.
     */
    T parse(final String line);
}
