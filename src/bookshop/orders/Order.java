package bookshop.orders;

import bookshop.products.Product;
import bookshop.users.Customer;

import java.util.ArrayList;

public class Order {
    private static int nextId = 1;

    private int orderId;
    private Customer customer;
    private ArrayList<Product> products;
    private double totalAmount;

    public Order(Customer customer) {
        this.orderId = nextId;
        nextId++;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.totalAmount = 0;
    }

    public boolean addProduct(Product product) {
        if (product.getStockQuantity() == 0) {
            return false;
        }
        product.setStockQuantity(product.getStockQuantity() - 1);
        products.add(product);
        totalAmount = totalAmount + product.getPrice();
        return true;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void printReceipt() {
        System.out.println("========== RECEIPT ==========");
        System.out.println("Order #" + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Delivery address: " + customer.getAddress());
        System.out.println("-----------------------------");
        for (Product product : products) {
            System.out.println(product.getName() + " - " + product.getPrice() + " tg");
        }
        System.out.println("-----------------------------");
        System.out.println("TOTAL: " + totalAmount + " tg");
        System.out.println("=============================");
    }
}
