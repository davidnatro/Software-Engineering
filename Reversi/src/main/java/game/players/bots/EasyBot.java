package game.players.bots;

import game.boards.core.Move;
import game.constants.Color;
import game.exceptions.CoordinatesDimensionException;
import game.players.bots.evaluations.EvaluateResult;

import java.util.List;

public class EasyBot extends Bot {
    public EasyBot(Color color) throws IllegalArgumentException {
        super(color);
    }

    @Override
    public EvaluateResult evaluate() {
        EvaluateResult evaluation = new EvaluateResult();
        List<Move> possibleMoves = getAllPossibleMovesByColor(getColor());

        double current_max;
        double max = 0;
        for (final Move move : possibleMoves) {
            current_max = getMoveCoefficient(move);

            if (current_max > max) {
                max = current_max;

                try {
                    evaluation.setCoordinates(move.getCoordinates());
                } catch (CoordinatesDimensionException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }

        return evaluation;
    }
}
