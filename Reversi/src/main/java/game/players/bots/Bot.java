package game.players.bots;

import game.boards.core.Move;
import game.constants.Color;
import game.players.Player;
import game.players.bots.evaluations.EvaluateResult;

import java.util.List;

public abstract class Bot extends Player {
    /**
     * Коэффицент вражеской кромочной фишки.
     */
    int opponentChipOnBorder = 2;

    /**
     * Коэффицент вражеской не кромочной фишки.
     */
    int opponentChip = 1;

    /**
     * Коэффицент своей угловой фишки.
     */
    double chipOnCorner = 0.8;

    /**
     * Коэффицент своей кромочной фишки.
     */
    double chipOnBorder = 0.4;

    /**
     * Коэффицент своей фишки.
     */
    double chip = 0.0;

    protected Bot(Color color) throws IllegalArgumentException {
        super(color);
    }

    /**
     * Получение коэффицента хода.
     * @param move Ход.
     * @return Коэффицент хода.
     */
    protected double getMoveCoefficient(Move move) {
        double result = 0;

        for (final int[] coordinates : move.getCoordinatesToChangeColor()) {
            if (getBoard().isChipOnBorder(coordinates[0], coordinates[1])) {
                result += opponentChipOnBorder;
            } else {
                result += opponentChip;
            }
        }

        int[] coordinates = move.getCoordinates();
        if (getBoard().isChipOnCorner(coordinates[0], coordinates[1])) {
            result += chipOnCorner;
        } else if (getBoard().isChipOnBorder(coordinates[0], coordinates[1])) {
            result += chipOnBorder;
        } else {
            result += chip;
        }

        return result;
    }

    /**
     * Оценочная функция.
     * @return Кооридинаты самого выгодного хода.
     */
    protected abstract EvaluateResult evaluate();

    @Override
    public void move() {
        EvaluateResult evaluation = evaluate();
        Move move = getBoard()
                .isValidMove(evaluation.getCoordinates()[0], evaluation.getCoordinates()[1], getColor());

        if (move.isValid()) {
            List<int[]> coordinatesToChangeColor = move.getCoordinatesToChangeColor();
            try {
                getBoard().setColorAt(evaluation.getCoordinates()[0], evaluation.getCoordinates()[1], getColor());
                for (final int[] changeColorCoordinates : coordinatesToChangeColor) {
                    getBoard().setColorAt(changeColorCoordinates[0], changeColorCoordinates[1], getColor());
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
