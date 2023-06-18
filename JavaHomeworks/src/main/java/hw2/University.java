package hw2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import hw2.constants.ConstStrings;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class University {
    private static List<Student> students = new ArrayList<Student>();

    // Чтение json файла.
    static {
        Gson gson = new GsonBuilder().create();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ConstStrings.STORAGE_PATH));
            Type listType = new TypeToken<ArrayList<Student>>() {
            }.getType();
            students = gson.fromJson(bufferedReader, listType);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    // Сохраняет изменения в json файл.
    private static void saveChanges() {
        Gson gson = new GsonBuilder().create();
        try {
            FileWriter fileWriter = new FileWriter(ConstStrings.STORAGE_PATH);
            fileWriter.write(gson.toJson(students));
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void addStudent(Student student) {
        for (final Student student_temp : students) {
            if (student_temp.equals(student)) {
                System.out.println("Такой студент уже существует!");
                return;
            }
        }

        students.add(student);
        saveChanges();
    }

    public static void removeStudentByName(String fullName) {
        for (final Student student : students) {
            if (student.getFullName().equals(fullName)) {
                students.remove(student);
                saveChanges();
                return;
            }
        }
    }

    public static void askStudent(String fullName) {
        Student student = null;
        for (final Student student_temp : students) {
            if (student_temp.getFullName().equals(fullName)) {
                student = student_temp;
            }
        }

        if (student == null) return;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Студент присутсвует на паре?\n1. Да\n2. Нет\nВыбор: ");

        boolean isPresent = false;
        int choice = 0;
        while (choice != 1 && choice != 2) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> isPresent = true;
                    case 2 -> isPresent = false;
                    default -> System.out.println("Неверное значение!");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Неверное значение!");
            }
        }

        if (!isPresent) {
            student.addMark(0);
            System.out.println("Оценка: 0");
        } else {
            int studentAnswer = Application.getRandomNumber(0, 10);
            System.out.println("Оценка: " + studentAnswer);
            student.addMark(studentAnswer);
        }

        saveChanges();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Имя:\t\t\tОценки:\n");

        for (int i = 0; i < students.size(); i++) {
            stringBuilder.append(students.get(i));
            if (i != students.size() - 1) stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
