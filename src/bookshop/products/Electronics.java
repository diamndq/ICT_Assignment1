package bookshop.products;

public class Electronics extends Product {
    private String brand;
    private int warrantyMonths;

    public Electronics(String name, double price, int stockQuantity, String brand, int warrantyMonths) {
        super(name, price, stockQuantity);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   Electronics | Brand: " + brand + " | Warranty: " + warrantyMonths + " months");
    }
}