package hw3;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileIteratorTests {
    private static final String inputPath = "src/main/java/hw3/inputs/input_1";

    @Test
    public void fileIterationTest() throws IOException {
        FileIterator fileIterator = new FileIterator(new BufferedReader(new FileReader(inputPath)));

        List<String> lines = Files.readAllLines(Path.of(inputPath));

        int index = 0;

        while (fileIterator.hasNext()) {
            String line = fileIterator.next();
            Assertions.assertEquals(line, lines.get(index++));
        }
    }
}
