package service;

import model.*;
import repository.*;

public class TransactionService {

    private BookManager bookRepo;
    private UserManager userRepo;
    private RecordManager recordRepo;

    public TransactionService(BookManager b, UserManager u, RecordManager r) {
        this.bookRepo = b;
        this.userRepo = u;
        this.recordRepo = r;
    }

    public void lendBook(String userId, String bookId) {

        LibraryUser user = userRepo.getUser(userId);
        LibraryBook book = bookRepo.getBook(bookId);

        if (user == null || book == null || book.getStock() <= 0) {
            System.out.println("user id or book id is invalid");
            return;
        }

        book.reduceStock();
        user.getBorrowedBooks().add(bookId);

        BorrowRecord entry = new BorrowRecord(
                recordRepo.nextId(), userId, bookId);

        recordRepo.saveRecord(entry);

        System.out.println("Book issued successfully.");
    }

    public void acceptReturn(String userId, String bookId) {
         LibraryUser user = userRepo.getUser(userId);
    LibraryBook book = bookRepo.getBook(bookId);

    if (user == null) {
        System.out.println("Invalid User ID!");
        return;
    }

    if (book == null) {
        System.out.println("Invalid Book ID!");
        return;
    }

    BorrowRecord entry = recordRepo.findOpenRecord(userId, bookId);

    if (entry == null) {
        System.out.println("No active transaction found!");
        return;
    }

    entry.closeRecord();
    book.increaseStock();

    long fine = entry.computePenalty();

    System.out.println("Book returned successfully.");

    if (fine > 0) {
        System.out.println("Fine: ₹" + fine);
    } else {
        System.out.println("No fine.");
    }

    }
    public void listAllRecords() {
    if (recordRepo.fetchAll().isEmpty()) {
        System.out.println("No transactions available.");
    } else {
        for (BorrowRecord r : recordRepo.fetchAll()) {
            System.out.println(r);
        }   
    }
}
}