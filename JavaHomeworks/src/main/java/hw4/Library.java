package hw4;

import java.util.HashMap;
import java.util.Map;

public class Library {
    private final HashMap<Book, Integer> books;

    public Library() {
        books = new HashMap<Book, Integer>();
    }

    public Library(Book book, Integer count) throws IllegalArgumentException {
        if (count < 1) {
            throw new IllegalArgumentException("Нельзя добавить меньше 1 книги в библиотеку!");
        }

        books = new HashMap<Book, Integer>();

        books.put(book, count);
    }

    public Library(HashMap<Book, Integer> books) {
        this.books = books;
    }

    public HashMap<Book, Integer> getAllBooks() {
        return books;
    }

    private Book findBook(Book book) {
        for (final Map.Entry<Book, Integer> bookOnShelf : books.entrySet()) {
            if (bookOnShelf.getKey().getBookName().equals(book.getBookName())) {
                return bookOnShelf.getKey();
            }
        }

        return null;
    }

    public Book getBookByName(String bookName) {
        Book book = new Book(bookName);
        if (!books.containsKey(book)) {
            return null;
        }

        if (books.get(book) > 1) {
            book = findBook(book);
            books.put(book, books.get(book) - 1);
        } else if (books.get(book) == 1) {
            book = findBook(book);
            books.remove(book);
        }

        return book;
    }

    public void addBookToLibrary(Book book) {
        if (books.containsKey(book)) {
            books.put(book, books.get(book) + 1);
            return;
        }

        books.put(book, 1);
    }

    @Override
    public String toString() {
        if (books.size() == 0) {
            return "В библиотеке закончились книги!\n";
        }

        StringBuilder result = new StringBuilder();

        for (final Map.Entry<Book, Integer> book : books.entrySet()) {
            result.append(book.getKey());
            result.append("\t:\t");
            result.append(book.getValue());
            result.append("\n");
        }

        return result.toString();
    }
}