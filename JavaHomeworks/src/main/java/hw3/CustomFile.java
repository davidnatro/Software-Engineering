package hw3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

public class CustomFile implements Iterable<String> {
    private final BufferedReader bufferedReader;

    public CustomFile(final String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.isFile() || !file.exists()) {
            throw new FileNotFoundException("Файл не существует!");
        }

        bufferedReader = new BufferedReader(new FileReader(filePath));
    }

    @Override
    public Iterator<String> iterator() {
        return new FileIterator(bufferedReader);
    }
}
