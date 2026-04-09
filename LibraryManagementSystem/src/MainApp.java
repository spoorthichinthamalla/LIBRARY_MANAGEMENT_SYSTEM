import model.LibraryUser.MemberCategory;
import repository.*;
import service.*;

import java.util.Scanner;

public class MainApp {

    private static final Scanner sc = new Scanner(System.in);

    private static LibraryBookService bookService;
    private static LibraryUserService userService;
    private static TransactionService transactionService;

    public static void main(String[] args) {

        BookManager bookRepo = new BookManager();
        UserManager userRepo = new UserManager();
        RecordManager recordRepo = new RecordManager();

        bookService = new LibraryBookService(bookRepo);
        userService = new LibraryUserService(userRepo);
        transactionService = new TransactionService(bookRepo, userRepo, recordRepo);

        boolean running = true;

        while (running) {

            showMenu();

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {

                case 1 -> addBookFlow();
                case 2 -> bookService.showBooks();
                case 3 -> updateBookFlow();
                case 4 -> removeBookFlow();
                case 5 -> addUserFlow();
                case 6 -> userService.showUsers();
                case 7 -> issueFlow();
                case 8 -> returnFlow();
                case 9 -> transactionService.listAllRecords();

                case 0 -> {
                    running = false;
                    System.out.println("Exiting system...");
                }

                default -> System.out.println("Invalid option!");
            }
        }

        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add Book");
        System.out.println("2. View Books");
        System.out.println("3. Update Book");
        System.out.println("4. Remove Book");
        System.out.println("5. Add User");
        System.out.println("6. View Users");
        System.out.println("7. Issue Book");
        System.out.println("8. Return Book");
        System.out.println("9. View Transactions");
        System.out.println("0. Exit");
    }

    private static void addBookFlow() {
        String id = getStringInput("Enter Book ID: ");
        String name = getStringInput("Enter Book Name: ");
        String author = getStringInput("Enter Author: ");
        int stock = getIntInput("Enter Stock: ");

        bookService.addBook(id, name, author, stock);
    }

    private static void updateBookFlow() {
        String id = getStringInput("Enter Book ID to update: ");
        String name = getStringInput("Enter new name: ");
        String author = getStringInput("Enter new author: ");
        int stock = getIntInput("Enter new stock: ");

        bookService.updateBook(id, name, author, stock);
    }

    private static void removeBookFlow() {
        String id = getStringInput("Enter Book ID to remove: ");
        bookService.removeBook(id);
    }

    private static void addUserFlow() {
        String name = getStringInput("Enter User Name: ");

        System.out.println("1 - STUDENT");
        System.out.println("2 - FACULTY");
        System.out.println("3 - PUBLIC");

        int type = getIntInput("Enter option: ");

        MemberCategory category = switch (type) {
            case 1 -> MemberCategory.STUDENT;
            case 2 -> MemberCategory.FACULTY;
            default -> MemberCategory.PUBLIC;
        };

        userService.addUser(name, category);
    }

    private static void issueFlow() {
        String userId = getStringInput("Enter User ID: ");
        String bookId = getStringInput("Enter Book ID: ");

        transactionService.lendBook(userId, bookId);
    }

    private static void returnFlow() {
        String userId = getStringInput("Enter User ID: ");
        String bookId = getStringInput("Enter Book ID: ");

        transactionService.acceptReturn(userId, bookId);
    }

    private static String getStringInput(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    private static int getIntInput(String msg) {
        System.out.print(msg);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}