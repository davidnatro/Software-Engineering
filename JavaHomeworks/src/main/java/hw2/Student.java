package hw2;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String fullName;
    private final List<Integer> marks;

    public Student(String fullName) {
        this.fullName = fullName;
        marks = new ArrayList<Integer>();
    }

    public String getFullName() {
        return fullName;
    }

    public void addMark(int mark) {
        marks.add(mark);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(fullName);
        stringBuilder.append("\t\t\t");

        for (int i = 0; i < marks.size(); i++) {
            stringBuilder.append(marks.get(i));

            if (i != marks.size() - 1) {
                stringBuilder.append(",");
            }
        }

        return stringBuilder.toString();
    }
}
