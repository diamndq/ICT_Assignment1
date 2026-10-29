package bookshop.products;

public class Book extends Product {
    private String author;
    private String genre;

    public Book(String name, double price, int stockQuantity, String author, String genre) {
        super(name, price, stockQuantity);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Book | Author: " + author + " | Genre: " + genre);
    }
}