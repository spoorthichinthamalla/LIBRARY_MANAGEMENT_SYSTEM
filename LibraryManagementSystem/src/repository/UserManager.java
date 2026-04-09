package repository;

import model.LibraryUser;
import java.util.*;

public class UserManager {

    private Map<String, LibraryUser> users = new HashMap<>();

    public void addUser(LibraryUser user) {
        users.put(user.getId(), user);
    }

    public LibraryUser getUser(String id) {
        return users.get(id);
    }

    public List<LibraryUser> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}