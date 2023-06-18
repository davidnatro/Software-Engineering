package game.players.bots;

import game.boards.Board;
import game.boards.core.Move;
import game.constants.Color;
import game.exceptions.CoordinatesDimensionException;
import game.players.bots.evaluations.EvaluateResult;

import java.util.List;

public class ProfessionalBot extends Bot {
    public ProfessionalBot(Color color) throws IllegalArgumentException {
        super(color);
    }

    @Override
    public EvaluateResult evaluate() {
        Board currentBoard = getBoard();
        Color opponentColor = (getColor() == Color.Black) ? Color.White : Color.Black;

        List<Move> possibleMoves = getAllPossibleMovesByColor(getColor());
        double current_max;
        double max = 0;

        EvaluateResult result = new EvaluateResult();
        try {
            result.setCoordinates(possibleMoves.get(0).getCoordinates());
        } catch (CoordinatesDimensionException exception) {
            System.out.println(exception.getMessage());
        }


        for (final Move move : possibleMoves) {
            // Клонируем текущую доску
            setBoard(new Board());
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if (currentBoard.getColorAt(i, j) != Color.Neutral) {
                        getBoard().setColorAt(i, j, currentBoard.getColorAt(i, j));
                    }
                }
            }

            double moveCoefficient = getMoveCoefficient(move);

            int[] moveCoordinates = move.getCoordinates();
            getBoard().setColorAt(moveCoordinates[0], moveCoordinates[1], getColor());
            for (final int[] coordinates : move.getCoordinatesToChangeColor()) {
                getBoard().setColorAt(coordinates[0], coordinates[1], getColor());
            }

            double opponentMaxCoefficient = 0;
            Move opponentBestMove = new Move();
            List<Move> opponentMoves = getAllPossibleMovesByColor(opponentColor);
            for (final Move opponentMove : opponentMoves) {
                double opponentMoveCoefficient = getMoveCoefficient(opponentMove);
                if (opponentMoveCoefficient > opponentMaxCoefficient) {
                    opponentMaxCoefficient = opponentMoveCoefficient;
                    opponentBestMove = opponentMove;
                }
            }

            current_max = moveCoefficient - getMoveCoefficient(opponentBestMove);
            if (current_max > max) {
                max = current_max;
                try {
                    result.setCoordinates(move.getCoordinates());
                } catch (CoordinatesDimensionException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }

        setBoard(currentBoard);

        return result;
    }
}