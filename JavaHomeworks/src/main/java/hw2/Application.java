package hw2;

import hw2.constants.ConstStrings;

import java.util.Scanner;

public class Application {
    private final static University university = new University();

    /**
     * Возвращает рандомное число в промежутке от min до max. Оба числа включены в промежуток.
     *
     * @param min Минимальное значение из промежутка.
     * @param max Макисмальное значение из промежутка.
     * @return Рандомное число из промежутка.
     */
    public static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        System.out.println("Кто отвечает на паре?");

        do {
            System.out.println(ConstStrings.FUNCTIONALITY);
            System.out.print("Введите число: ");

            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Неверный ввод! Повторите попытку.");
                continue;
            }

            System.out.println();

            switch (input) {
                case 1:
                    System.out.print("Введите имя: ");
                    University.addStudent(new Student(scanner.nextLine()));
                    System.out.println("Студент добавлен!");
                    break;
                case 2:
                    System.out.print("Введите имя: ");
                    University.removeStudentByName(scanner.nextLine());
                    System.out.println("Студент удалён!");
                    break;
                case 3:
                    System.out.print("Введите имя: ");
                    University.askStudent(scanner.nextLine());
                    break;
                case 4:
                    System.out.println(university);
                    System.out.println();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Ошибка ввода! Недопустимый диапазон!");
                    break;
            }

        } while (input != 5);
    }
}
