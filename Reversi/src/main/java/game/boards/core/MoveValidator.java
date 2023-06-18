package game.boards.core;

import game.constants.Color;

public interface MoveValidator {
    /**
     * Валидация хода с координатами (row, column).
     * @param row Номер ряда.
     * @param column Номер столбца
     * @param color Цвет фишки.
     * @return Объект Move, содержащий результат валидации хода,
     * а так же координаты фишек, требующих перекраски, в случае валидности.
     */
    Move isValidMove(int row, int column, Color color);
}
