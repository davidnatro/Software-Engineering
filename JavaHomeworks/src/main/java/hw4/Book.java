package hw4;

public class Book {
    private final String bookName;

    private final String authorName;

    private final int yearOfPublishing;

    public Book(String bookName, String authorName, int yearOfPublishing) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.yearOfPublishing = yearOfPublishing;
    }

    public Book(String bookName) {
        this(bookName, "unknown", 0);
    }

    public Book(Book book) {
        this.bookName = book.bookName;
        this.authorName = book.authorName;
        this.yearOfPublishing = book.yearOfPublishing;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Book otherBook)) {
            return false;
        }

        return bookName.equals(otherBook.bookName);
    }

    /*
    Переопределяем hashCode только по названию книгу, чтобы
    по нему можно было искать книги в HasMap'е.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        int prime = 13;

        hash = prime * hash + (bookName != null ? bookName.hashCode() : 0);

        return hash;
    }

    @Override
    public String toString() {
        return bookName;
    }
}
