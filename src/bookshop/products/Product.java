package bookshop.products;

public class Product {
    private static int nextId = 1;

    private int id;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(String name, double price, int stockQuantity) {
        this.id = nextId;
        nextId++;
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: name cannot be empty");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("Error: price must be greater than 0");
        }
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        } else {
            System.out.println("Error: stock quantity cannot be negative");
        }
    }

    public void displayInfo() {
        System.out.println("ID: " + id + " | " + name + " | " + price + " tg | In stock: " + stockQuantity);
    }
}