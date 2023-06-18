package game.boards;

import game.boards.core.Move;
import game.boards.core.MoveValidator;
import game.constants.Color;

import java.util.ArrayList;
import java.util.List;

public class Board implements MoveValidator {
    private final Color[][] board;

    public Board() {
        board = new Color[8][8];
        for(int i = 0; i < 8; i++) {
          for(int j = 0; j < 8; j++) {
            board[i][j] = Color.Neutral;
          }
        }

        board[3][3] = Color.White;
        board[4][4] = Color.White;

        board[3][4] = Color.Black;
        board[4][3] = Color.Black;
    }

    /**
     * Получение матрицы(массив массивов) 8x8, содержащей все фишки текущей доски.
     * @return Матрица(массив массивов) 8x8 цветов.
     */
    public final Color[][] getBoard() {
        return board;
    }

    /**
     * Возвращает цвет определенного места на доске
     * @param row Номер ряда.
     * @param column Номер столбца
     * @return Цвет данной позиции.
     */
    public final Color getColorAt(int row, int column) throws IndexOutOfBoundsException {
        if ((row > 7 || row < 0) || (column > 7 || column < 0)) {
            throw new IndexOutOfBoundsException("Вы вышли за границы доски!");
        }

        return board[row][column];
    }

    /**
     * Устанавливает фишку на некоторую позицию.
     * @param row Номер ряда.
     * @param column Номер столбца.
     * @param color Цвет.
     * @throws IllegalArgumentException При попытке убрать фишку с доски.
     */
    public void setColorAt(int row, int column, Color color) throws IllegalArgumentException {
        if (color == Color.Neutral) {
            throw new IllegalArgumentException("Нельзя убрать фишку с доски!");
        }

        board[row][column] = color;
    }

    /**
     * Проверка доски на заполненность.
     * @return true, если доска заполнена, иначе false.
     */
    public final boolean isFull() {
        for(int i = 0; i < 8; i++) {
          for(int j = 0; j < 8; j++) {
            if (board[i][j] == Color.Neutral){
                return false;
            }
          }
        }

        return true;
    }

    /**
     * Определяет является ли клетка доски кромочной.
     * @param row Номер строки.
     * @param column Номер столбца.
     * @return true, если клетка кромочная, иначе false.
     */
    public final boolean isChipOnBorder(int row, int column) {
        return (row == 0 || row == 7) || (column == 0 || column == 7);
    }

    /**
     * Определяет является ли клетка угловой.
     * @param row Номер строки.
     * @param column Номер столбца.
     * @return true, если клетка является угловой, иначе false.
     */
    public final boolean isChipOnCorner(int row, int column) {
        return (row == 0 && column == 0) || (row == 7 && column == 7) ||
                (row == 0 && column == 7) || (row == 7 && column == 0);
    }

    @Override
    public Move isValidMove(int row, int column, Color color) {
        Move move = new Move();
        List<int[]> chipsCoordinates = new ArrayList<>();

        if (board[row][column] != Color.Neutral || color == Color.Neutral) {
            return move;
        }

        move.setCoordinates(row, column);

        Color oppositeColor = (color == Color.Black) ? Color.White : Color.Black;

        // Проверяем цвета всех слотов вокруг данной позиции.
        for(int i = row - 1; i <= row + 1; i++) {
          for(int j = column - 1; j <= column + 1; j++) {
            if ((i < 0 || i > 7) || (j < 0 || j > 7)) {
                continue;
            }

            if (board[i][j] == oppositeColor) {
                // Представим доску в виде ПДСК(Прямоугольная декартова система координат) xOy

                // Фишка выше оси x ; правее оси y
                if (i < row && j > column) {
                    int iCopy = i;
                    int jCopy = j;
                    while (iCopy >= 0 && jCopy <= 7) {
                        if (board[iCopy][jCopy] == Color.Neutral) {
                            break;
                        }
                        chipsCoordinates.add(new int[] {iCopy, jCopy});
                        if (board[iCopy][jCopy] == color) {
                            move.setIsValid(true);
                            move.addCoordinatesToChangeColor(chipsCoordinates);
                            chipsCoordinates.clear();
                            break;
                        }
                        iCopy -= 1;
                        jCopy += 1;
                    }
                    chipsCoordinates.clear();
                    continue;
                }

                // Фишка выше оси x ; на оси y
                if (i < row && j == column) {
                    int iCopy = i;
                    while (iCopy >= 0) {
                        if (board[iCopy][j] == Color.Neutral) {
                            break;
                        }
                        chipsCoordinates.add(new int[] {iCopy, j});
                        if (board[iCopy][j] == color) {
                            move.setIsValid(true);
                            move.addCoordinatesToChangeColor(chipsCoordinates);
                            chipsCoordinates.clear();;
                            break;
                        }
                        iCopy -= 1;
                    }
                    chipsCoordinates.clear();
                    continue;
                }

                // Фишка выше оси x ; левее оси y
                if (i < row && j < row) {
                    int iCopy = i;
                    int jCopy = j;
                    while (iCopy >= 0 && jCopy >= 0) {
                        if (board[iCopy][jCopy] == Color.Neutral) {
                            break;
                        }
                        chipsCoordinates.add(new int[] {iCopy, jCopy});
                        if (board[iCopy][jCopy] == color) {
                            move.setIsValid(true);
                            move.addCoordinatesToChangeColor(chipsCoordinates);
                            chipsCoordinates.clear();
                            break;
                        }
                        iCopy -= 1;
                        jCopy -= 1;
                    }
                    chipsCoordinates.clear();
                    continue;
                }

                // Фишка на оси x ; правее оси y
                if (i == row && j > column) {
                    int jCopy = j;
                    while (jCopy <= 7) {
                        if (board[i][jCopy] == Color.Neutral) {
                            break;
                        }
                        chipsCoordinates.add(new int[] {i, jCopy});
                        if(board[i][jCopy] == color) {
                            move.setIsValid(true);
                            move.addCoordinatesToChangeColor(chipsCoordinates);
                            chipsCoordinates.clear();
                            break;
                        }
                        jCopy += 1;
                    }
                    chipsCoordinates.clear();
                    continue;
                }

                // Фишка на оси x ; левее оси y
                if (i == row && j < column) {
                    int jCopy = j;
                    while (jCopy >= 0) {
                        if (board[i][jCopy] == Color.Neutral) {
                            break;
                        }
                        chipsCoordinates.add(new int[] {i, jCopy});
                        if (board[i][jCopy] == color) {
                            move.setIsValid(true);
                            move.addCoordinatesToChangeColor(chipsCoordinates);
                            chipsCoordinates.clear();
                            break;
                        }
                        jCopy -= 1;
                    }
                    chipsCoordinates.clear();
                    continue;
                }

                // Фишка ниже оси x ; правее оси y
                if (i > row && j > column) {
                    int iCopy = i;
                    int jCopy = j;
                    while (iCopy <= 7 && jCopy <= 7) {
                        if (board[iCopy][jCopy] == Color.Neutral) {
                            break;
                        }
                        chipsCoordinates.add(new int[] {iCopy, jCopy});
                        if (board[iCopy][jCopy] == color) {
                            move.setIsValid(true);
                            move.addCoordinatesToChangeColor(chipsCoordinates);
                            chipsCoordinates.clear();
                            break;
                        }
                        iCopy += 1;
                        jCopy += 1;
                    }
                    chipsCoordinates.clear();
                    continue;
                }

                // Фишка ниже оси x ; на оси y
                if (i > row && j == column) {
                    int iCopy = i;
                    while (iCopy <= 7) {
                        if (board[iCopy][j] == Color.Neutral) {
                            break;
                        }
                        chipsCoordinates.add(new int[] {iCopy, j});
                        if (board[iCopy][j] == color) {
                            move.setIsValid(true);
                            move.addCoordinatesToChangeColor(chipsCoordinates);
                            chipsCoordinates.clear();
                            break;
                        }
                        iCopy += 1;
                    }
                    chipsCoordinates.clear();
                    continue;
                }

                // Фишка ниже оси x ; левее оси y
                if (i > row && j < column) {
                    int iCopy = i;
                    int jCopy = j;
                    while ((iCopy <= 7 &&  jCopy >= 0)) {
                        if (board[iCopy][jCopy] == Color.Neutral) {
                            break;
                        }
                        chipsCoordinates.add(new int[] {iCopy, jCopy});
                        if (board[iCopy][jCopy] == color) {
                            move.setIsValid(true);
                            move.addCoordinatesToChangeColor(chipsCoordinates);
                            chipsCoordinates.clear();
                            break;
                        }
                        iCopy += 1;
                        jCopy -= 1;
                    }
                    chipsCoordinates.clear();
                    continue;
                }
            }
          }
        }

        return move;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("   1 2 3 4 5 6 7 8 \n");

        for(int i = 0; i < 8; i++) {
            result.append(i + 1);
            result.append(" |");
          for(int j = 0; j < 8; j++) {
              Color color = getColorAt(i, j);
              if (color== Color.Neutral) {
                  result.append("*");
              } else {
                  result.append((color == Color.Black) ? "B" : "W");
              }
              result.append("|");
          }

          result.append(" ");
          result.append(i + 1);
          result.append("\n");
        }


        result.append("   1 2 3 4 5 6 7 8 ");

        return result.toString();
    }
}
