package service;

import model.LibraryUser;
import model.LibraryUser.MemberCategory;
import repository.UserManager;

public class LibraryUserService {

    private UserManager manager;
    private int counter = 1;

    public LibraryUserService(UserManager manager) {
        this.manager = manager;
    }

    // ✅ MATCHES MainApp
    public void addUser(String name, MemberCategory type) {
        String id = "U" + counter++;
        LibraryUser user = new LibraryUser(id, name, type);
        manager.addUser(user);
        System.out.println("User added: " + id);
    }

    // ✅ MATCHES MainApp
    public void showUsers() {
        if (manager.getAllUsers().isEmpty()) {
            System.out.println("No users available.");
        } else {
            manager.getAllUsers().forEach(System.out::println);
        }
    }
}