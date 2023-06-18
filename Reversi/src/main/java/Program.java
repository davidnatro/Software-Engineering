import game.Game;
import game.boards.Board;
import game.constants.Banner;
import game.constants.Color;
import game.constants.Errors;
import game.constants.Menu;
import game.players.HumanPlayer;
import game.players.bots.EasyBot;
import game.players.bots.ProfessionalBot;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        System.out.println(Banner.BANNER);
        System.out.println(Menu.GREETING);

        while (true) {
            try{
                System.out.println(Menu.NAME + "\n" + Menu.MENU);
                System.out.print(Menu.CHOOSE);
                int menuChoice = Integer.parseInt(scanner.nextLine());
                if (menuChoice == 1) {
                    while(true) {
                        try{
                            System.out.println(Menu.GAME_MODES);
                            System.out.print(Menu.CHOOSE);
                            int modeChoice = Integer.parseInt(scanner.nextLine());
                            if (modeChoice == 1) {
                                game.setPlayer1(new HumanPlayer(Color.Black));
                                game.setPlayer2(new HumanPlayer(Color.White));
                            } else if (modeChoice == 2) {
                                while (true) {
                                    try{
                                        System.out.println(Menu.BOT_MODES);
                                        System.out.print(Menu.CHOOSE);
                                        int botChoice = Integer.parseInt(scanner.nextLine());
                                        if (botChoice == 1) {
                                            game.setPlayer1(new HumanPlayer(Color.Black));
                                            game.setPlayer2(new EasyBot(Color.White));
                                        } else if (botChoice == 2) {
                                            game.setPlayer1(new HumanPlayer(Color.Black));
                                            game.setPlayer2(new ProfessionalBot(Color.White));
                                        } else {
                                            System.out.println("\n" + Errors.IncorrectInput + "\n");
                                            continue;
                                        }
                                        break;
                                    } catch (NumberFormatException exception) {
                                        System.out.println(Errors.IncorrectInput);
                                    }
                                }
                            } else {
                                System.out.println("\n" + Errors.IncorrectInput + "\n");
                                continue;
                            }
                            game.setBoard(new Board());
                            game.play();
                            break;
                        } catch (NumberFormatException exception) {
                            System.out.println(Errors.IncorrectInput);
                        }

                    }
                } else if (menuChoice == 2) {
                    System.out.println(Menu.BEST_RESULT + game.getMaxScore());
                    System.out.print("Нажмите <ENTER> для продолжения!");
                    scanner.nextLine();
                } else if (menuChoice == 3) {
                    break;
                }
            } catch (NumberFormatException exception) {
                System.out.println(Errors.IncorrectInput);
            }
        }

        System.out.println("\n" + Menu.FAREWELL);
    }
}