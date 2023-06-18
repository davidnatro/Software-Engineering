package game.players;

import game.boards.Board;
import game.boards.core.Move;
import game.constants.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private static int playersInGame = 0;

    private final int id;

    private final Color color;

    private Board board;

    protected Player(Color color) throws IllegalArgumentException {
        if (color == Color.Neutral) {
            throw new IllegalArgumentException("Нельзя оставить игрока без цвета!");
        }

        this.id = ++playersInGame;
        this.color = color;
    }

    /**
     * Получение цвета фишек игрока.
     * @return Цвет фишек игрока.
     */
    public final Color getColor() {
        return color;
    }

    /**
     * Получение текущей доски.
     * @return Текущая доска.
     */
    public final Board getBoard() {
        return board;
    }

    /**
     * Присвоение игроку доски.
     * @param board Доска.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Возвращает количество фишек игрока на доске.
     * @return Количество фишек игрока на доске
     */
    public final int getChipCount() {
        int count = 0;

        for(final Color[] row : board.getBoard()) {
            for (final Color chip : row) {
                if (chip.equals(color)) {
                    ++count;
                }
            }
        }

        return count;
    }

    /**
     * Определяет может ли игрок сделать еще какой-либо ход.
     * @return true, если может, иначе false.
     */
    public boolean canMove() {
        Board board = getBoard();

        for(int i = 0; i < 8; i++) {
          for(int j = 0; j < 8; j++) {
            if (board.getColorAt(i, j) == Color.Neutral) {
                if (board.isValidMove(i, j, getColor()).isValid()) {
                    return true;
                }
            }
          }
        }

        return false;
    }

    /**
     * Получение всевозможных валидных ходов некоторого цвета.
     * @return Всевозможные валидные ходы некоторого цвета.
     */
    public List<Move> getAllPossibleMovesByColor(Color color) {
        List<Move> moves = new ArrayList<>();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (getBoard().getColorAt(i, j) == Color.Neutral) {
                    Move move = getBoard().isValidMove(i, j, color);
                    if (move.isValid()) {
                        moves.add(move);
                    }
                }
            }
        }

        return moves;
    }

    /**
     * Выполнение игроком хода.
     */
    public abstract void move();

    @Override
    public String toString() {
        String colorTranslate = (color == Color.Black) ? "чёрный" : "белый";
        return "Игрок №" + id + " (" + colorTranslate + " ; очков: " + getChipCount() + ")";
    }
}
