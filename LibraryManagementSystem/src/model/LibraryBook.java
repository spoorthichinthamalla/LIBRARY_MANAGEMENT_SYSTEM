package model;

public class LibraryBook {

    private String id;
    private String name;
    private String author;
    private int stock;

    public LibraryBook(String id, String name, String author, int stock) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.stock = stock;
    }

    public String getBookId() { return id; }
    public String getName() { return name; }
    public int getStock() { return stock; }

    public void reduceStock() {
        if (stock > 0) stock--;
    }

    public void increaseStock() {
        stock++;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + author + " | Stock: " + stock;
    }
    public void setName(String name) {
    this.name = name;
}

public void setAuthor(String author) {
    this.author = author;
}

public void setStock(int stock) {
    this.stock = stock;
}
}
