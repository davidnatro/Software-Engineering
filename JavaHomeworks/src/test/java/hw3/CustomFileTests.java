package hw3;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class CustomFileTests {
    @Test
    public void fileNotFoundTest() {
        String fileName = UUID.randomUUID().toString();

        while( Files.exists(Path.of(fileName))) {
            fileName = UUID.randomUUID().toString();
        }

        final String filePath = fileName;
        Assertions.assertThrows(FileNotFoundException.class, () -> new CustomFile(filePath));
    }
}
