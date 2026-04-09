package repository;

import model.LibraryBook;
import java.util.*;

public class BookManager {

    private Map<String, LibraryBook> books = new HashMap<>();

    public void addBook(LibraryBook book) {
        books.put(book.getBookId(), book);
    }

    public LibraryBook getBook(String id) {
        return books.get(id);
    }

    public List<LibraryBook> getAllBooks() {
        return new ArrayList<>(books.values());
    }
    public void removeBook(String id) {
    books.remove(id);
}
}