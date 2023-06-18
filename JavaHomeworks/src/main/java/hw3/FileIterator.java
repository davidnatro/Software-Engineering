package hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

public class FileIterator implements Iterator<String> {
    private final BufferedReader bufferedReader;
    private String currentLine;

    public FileIterator(final BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public boolean hasNext() {
        try {
            return (currentLine = bufferedReader.readLine()) != null;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public String next() {
        return currentLine;
    }
}
