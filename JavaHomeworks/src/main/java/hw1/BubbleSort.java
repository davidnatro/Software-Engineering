package hw1;

public class BubbleSort {
    public static void sort(int[] data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
    }
}
