package model;

import java.util.*;

public class LibraryUser {

    private String id;
    private String name;
    private MemberCategory category;
    private List<String> borrowed = new ArrayList<>();

    public enum MemberCategory {
        STUDENT, FACULTY, PUBLIC
    }

    // ✅ CORRECT CONSTRUCTOR
    public LibraryUser(String id, String name, MemberCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getId() { return id; }
    public List<String> getBorrowedBooks() { return borrowed; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + category;
    }
}