package game.players.bots.evaluations;

import game.exceptions.CoordinatesDimensionException;

public class EvaluateResult {
    private int[] coordinates;

    /**
     * Получение координат хода.
     * @return Координаты хода.
     */
    public final int[] getCoordinates() {
        return coordinates;
    }

    /**
     * Устанавливает координаты хода.
     * @param coordinates Координаты хода.
     * @throws CoordinatesDimensionException Выбрасывается при неверной размерности координат хода.
     */
    public void setCoordinates(int[] coordinates) throws CoordinatesDimensionException {
        if(coordinates.length != 2) {
            throw new CoordinatesDimensionException("Неверное количество координат!");
        }

        this.coordinates = coordinates;
    }
}
