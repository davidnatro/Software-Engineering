package game.boards.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Содержит информацию о валидности хода и
 * координаты точек, требующих перекраску.
 */
public class Move {
    private boolean result = false;

    private final List<int[]> chipsToChangeColor = new ArrayList<int[]>();

    private final int[] coordinates = new int[2];

    /**
     * Возвращает значение валидности хода.
     * @return Значение валидности хода.
     */
    public final boolean isValid() {
        return result;
    }

    /**
     * Устанавливает значение валидности хода.
     * @param isValidMove Значение валидности хода.
     */
    public void setIsValid(boolean isValidMove) {
        this.result = isValidMove;
    }

    /**
     * Получение кооррдинат текущего хода.
     * @return Координаты текущего хода.
     */
    public final int[] getCoordinates(){
        return coordinates;
    }

    /**
     * Установка координат текущего хода.
     * @param row Номер столбца.
     * @param column Номер строки.
     */
    public void setCoordinates(int row, int column) {
        this.coordinates[0] = row;
        this.coordinates[1] = column;
    }

    /**
     * Возвращает список координат, требующих перекраску.
     * @return Список массивов координат, требующих перекраску.
     */
    public final List<int[]> getCoordinatesToChangeColor() {
        return chipsToChangeColor;
    }

    /**
     * Добавление координат фишки, требующей перекраску, в список.
     * @param coordinates Список массивов координат длины 2.
     */
    public void addCoordinatesToChangeColor(List<int[]> coordinates) {
        chipsToChangeColor.addAll(coordinates);
    }
}