package game;

import game.boards.Board;
import game.boards.core.Move;
import game.constants.Color;
import game.players.HumanPlayer;
import game.players.Player;

import java.util.List;
import java.util.Scanner;

public class Game {
    private static int maxScore = 0;
    private Board board;
    private final Board previousBoard = new Board();
    private Player player1;
    private Player player2;

    /**
     * Добавляет нового игрока в игру.
     * @param player Игрок.
     */
    public void setPlayer1(Player player) {
        this.player1 = player;
    }

    /**
     * Добавляет нового игрока в игру.
     * @param player Игрок.
     */
    public void setPlayer2(Player player) {
        this.player2 = player;
    }

    /**
     * Добавляет новую доску в игру.
     * @param board Доска.
     */
    public void setBoard(Board board) {
        this.board = board;
        this.player1.setBoard(board);
        this.player2.setBoard(board);
    }

    /**
     * Получение максимального количества очков заработанное игроком(человеком) в текущей сессии.
     * @return Максимальное количество очков заработанное игроком(человеком) в текущей сессии.
     */
    public int getMaxScore() {
        return maxScore;
    }

    /**
     * Установка нового максимального количества очков, заработанного игроком(человеком),
     * если оно превыщает текущее значение.
     * @param player Игрок, чьи очки проверяют на превышение текущего рекорда.
     */
    private void setMaxScoreIfHigher(Player player) {
        if (player instanceof HumanPlayer) {
            if (player.getChipCount() > maxScore) {
                maxScore = player.getChipCount();
            }
        }
    }

    /**
     * Возвращает строку с визуализацией определенного хода.
     * @param move Ход.
     * @param player Игрок, чей ход визуазилруется.
     */
    private String visualizeMove(Move move, Player player) {
        StringBuilder result = new StringBuilder();
        result.append("=============================\n");
        result.append("Визуализация возможно хода №1\n");
        Board board = new Board();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (player.getBoard().getColorAt(i, j) != Color.Neutral) {
                    board.setColorAt(i, j, player.getBoard().getColorAt(i, j));
                }
            }
        }

        board.setColorAt(move.getCoordinates()[0], move.getCoordinates()[1], player.getColor());
        for (final int[] coordinates : move.getCoordinatesToChangeColor()) {
            board.setColorAt(coordinates[0], coordinates[1], player.getColor());
        }

        result.append(board);
        result.append("\n");
        result.append("=============================\n");

        return result.toString();
    }

    /**
     * Выводит на экран всевозможные ходы определенного игрока, а так же первый возможный ход.
     * @param player Игрок.
     */
    private void printPlayersPossibleMoves(Player player) {
        StringBuilder result = new StringBuilder();
        List<Move> moves = player.getAllPossibleMovesByColor(player.getColor());
        System.out.print("Возможные ходы:");

        for (final Move move : moves) {
            result.append(" (");
            result.append(move.getCoordinates()[0] + 1);
            result.append(", ");
            result.append(move.getCoordinates()[1] + 1);
            result.append(") ");
        }
        result.append("\n");

        System.out.println(result);
        System.out.println(visualizeMove(moves.get(0), player));
    }

    /**
     * Проверка на то, может ли игра продолжаться.
     * @return true, если игра может продолжаться, иначе false.
     */
    private boolean isGameOver() {
        return board.isFull() || !(player1.canMove() && player2.canMove());
    }

    /**
     * Сохраняет текущую доску на случай отмены хода.
     */
    private void saveCurrentBoard() {
        for(int i = 0; i < 8; i++) {
          for(int j = 0; j < 8; j++) {
            if (board.getColorAt(i, j) != Color.Neutral) {
                previousBoard.setColorAt(i, j, board.getColorAt(i, j));
            }
          }
        }
    }

    /**
     * Загружает доску с прошлым ходом.
     */
    private boolean loadPreviousBoard() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Хотите отменить ход? [y\\n]");
        boolean hasAnswered = false;

         while(!hasAnswered) {
             switch (scanner.nextLine()) {
                 case "y" -> hasAnswered = true;
                 case "n" -> {
                     return false;
                 }
                 default -> System.out.println("Неверное значение!");
             }
        }

        Board newBoard = new Board();
        for(int i = 0; i < 8; i++) {
          for(int j = 0; j < 8; j++) {
            if (previousBoard.getColorAt(i, j) != Color.Neutral) {
                newBoard.setColorAt(i, j, previousBoard.getColorAt(i, j));
            }
          }
        }

        setBoard(newBoard);
        player1.setBoard(newBoard);
        player2.setBoard(newBoard);

        return true;
    }

    /**
     * Начало игры.
     */
    public void play() {
        boolean isPlayer2Human = player2 instanceof HumanPlayer;

        System.out.println(board);
        while (!isGameOver()) {
            System.out.println("\nХод игрока: " + player1);
            printPlayersPossibleMoves(player1);
            saveCurrentBoard();

            player1.move();
            setMaxScoreIfHigher(player1);
            System.out.println(board);
            if (!isPlayer2Human) {
                if (loadPreviousBoard()) {
                    System.out.println("Доска восстановлена!");
                    System.out.println(board);
                    continue;
                }
            }

            if (isGameOver()) {
                break;
            }

            System.out.println("\nХод игрока: " + player2);
            if (isPlayer2Human) {
                printPlayersPossibleMoves(player2);
            }

            player2.move();
            setMaxScoreIfHigher(player2);
            System.out.println(board);
        }

        System.out.println("\n");
        if (player1.getChipCount() > player2.getChipCount()) {
            System.out.print(player1);
            System.out.println(" победил!");
            System.out.print(player2);
            System.out.println(" проиграл!");
        } else if (player1.getChipCount() < player2.getChipCount()) {
            System.out.print(player2);
            System.out.println(" победил!");
            System.out.print(player1);
            System.out.println(" проиграл!");
        } else {
            System.out.println("Игра оканчивается ничьей!");
        }
        System.out.println("\n");
    }
}
