package hw1;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

public class BubbleSortTests {
    private static Stream<int[]> getTestingArray() {
        return Stream.of(new int [] {9, 8, 7, 6, 5, 4, 3, 2, 1},
                         new int [] {1, 2, 5, 3, 4}, new int[] {0, 0, 0, 0},
                         new int[] {1, 2, 3, 4, 5});
    }

    @ParameterizedTest
    @MethodSource("getTestingArray")
    public void sortTest(int[] array) {
        int[] arrayCopy = array.clone();

        BubbleSort.sort(array);
        Arrays.sort(arrayCopy);

        Assertions.assertArrayEquals(array, arrayCopy);
    }

    @Test
    public void nullParameterTest() {
        Assertions.assertThrows(NullPointerException.class, () -> BubbleSort.sort(null));
    }
}
