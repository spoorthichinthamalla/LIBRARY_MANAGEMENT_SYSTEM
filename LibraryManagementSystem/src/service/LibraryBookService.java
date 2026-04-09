package service;

import model.LibraryBook;
import repository.BookManager;

public class LibraryBookService {

    private BookManager manager;

    public LibraryBookService(BookManager manager) {
        this.manager = manager;
    }

    public void addBook(String id, String name, String author, int stock) {
        LibraryBook book = new LibraryBook(id, name, author, stock);
        manager.addBook(book);
        System.out.println("Book added successfully!");
    }

    public void showBooks() {
        if (manager.getAllBooks().isEmpty()) {
            System.out.println("No books available.");
        } else {
            manager.getAllBooks().forEach(System.out::println);
        }
    }

    public void updateBook(String id, String name, String author, int stock) {

        LibraryBook book = manager.getBook(id);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (!name.isEmpty()) book.setName(name);
        if (!author.isEmpty()) book.setAuthor(author);
        if (stock >= 0) book.setStock(stock);

        System.out.println("Book updated successfully!");
    }

    public void removeBook(String id) {

        LibraryBook book = manager.getBook(id);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

       manager.removeBook(id);

        System.out.println("Book removed successfully!");
    }
}