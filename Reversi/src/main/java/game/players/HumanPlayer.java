package game.players;

import game.boards.Board;
import game.boards.core.Move;
import game.constants.Color;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(Color color) throws IllegalArgumentException {
        super(color);
    }

    @Override
    public void move() {
        Scanner scanner = new Scanner(System.in);

        int row;
        int column;

        Move move;
        Board currentBoard = getBoard();

        while (true) {
            try {
                System.out.print("Номер ряда: ");
                row = Integer.parseInt(scanner.nextLine());
                System.out.print("Номер столбца: ");
                column = Integer.parseInt(scanner.nextLine());

                // Перевод в 0-индексированную систему.
                row -= 1;
                column -= 1;

                if ((row < 0 || row > 7) || (column < 0 || column > 7)) {
                    System.out.println("Не выходите за границы доски, пожалуйста!");
                    continue;
                }

                move = getBoard().isValidMove(row, column, getColor());

                if (!move.isValid()) {
                    System.out.println("Неверный ход!");
                    continue;
                }
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Введите число!");
            }
        }

        List<int[]> coordinatesToChangeColor = move.getCoordinatesToChangeColor();

        try {
            currentBoard.setColorAt(row, column, getColor());
            for (final int[] coordinates : coordinatesToChangeColor) {
                currentBoard.setColorAt(coordinates[0], coordinates[1], getColor());
            }
        } catch (IllegalArgumentException exception) {
          System.out.println(exception.getMessage());
        }
    }
}
