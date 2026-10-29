package bookshop;

import bookshop.orders.Order;
import bookshop.products.Book;
import bookshop.products.Clothing;
import bookshop.products.Electronics;
import bookshop.products.Product;
import bookshop.users.Customer;
import bookshop.users.Employee;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);

    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        employees.add(new Employee("Daniyar Sadykov", "manager@bookshop.kz", "admin123", "Manager"));
        customers.add(new Customer("Aisha Bekova", "aisha@gmail.com", "123456",
                "Astana, Kabanbay Batyr 53, apt. 12"));

        products.add(new Book("1984", 4500, 5, "George Orwell", "Dystopia"));
        products.add(new Book("Dune", 6000, 3, "Frank Herbert", "Sci-fi"));
        products.add(new Book("The Master and Margarita", 5000, 4, "Mikhail Bulgakov", "Classic"));
        products.add(new Book("Harry Potter", 7000, 6, "J.K. Rowling", "Fantasy"));
        products.add(new Book("Abai Zholy", 5500, 2, "Mukhtar Auezov", "Classic"));
        products.add(new Electronics("PocketBook e-reader", 65000, 2, "PocketBook", 12));
        products.add(new Clothing("Book tote bag", 3500, 10, "One size", "Beige"));

        while (true) {
            System.out.println();
            System.out.println("===== Welcome to BookShop =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ---------- Login and registration ----------

    private static void login() {
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();

        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.checkPassword(password)) {
                System.out.println("Welcome, " + customer.getName() + "!");
                customerMenu(customer);
                return;
            }
        }

        for (Employee employee : employees) {
            if (employee.getEmail().equals(email) && employee.checkPassword(password)) {
                System.out.println("Welcome, " + employee.getName() + "!");
                employeeMenu(employee);
                return;
            }
        }

        System.out.println("Wrong email or password.");
    }

    private static void register() {
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Password (at least 6 characters): ");
        String password = input.nextLine();
        System.out.print("Delivery address: ");
        String address = input.nextLine();

        if (name.isEmpty() || !email.contains("@") || password.length() < 6) {
            System.out.println("Invalid name, email or password. Registration cancelled.");
            return;
        }

        customers.add(new Customer(name, email, password, address));
        System.out.println("Registration successful! You can now log in.");
    }

    // ---------- Customer ----------

    private static void customerMenu(Customer customer) {
        while (true) {
            System.out.println();
            System.out.println("===== Customer Menu (" + customer.getName() + ") =====");
            System.out.println("1. View products");
            System.out.println("2. Place order");
            System.out.println("3. View my orders");
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    showProducts();
                    break;
                case 2:
                    placeOrder(customer);
                    break;
                case 3:
                    showMyOrders(customer);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void placeOrder(Customer customer) {
        Order order = new Order(customer);
        showProducts();

        while (true) {
            System.out.print("Enter product ID (0 to finish): ");
            int id = input.nextInt();
            input.nextLine();
            if (id == 0) {
                break;
            }

            Product product = findProductById(id);
            if (product == null) {
                System.out.println("Product not found.");
            } else if (order.addProduct(product)) {
                System.out.println(product.getName() + " added.");
            } else {
                System.out.println(product.getName() + " is out of stock.");
            }
        }

        if (order.isEmpty()) {
            System.out.println("Order is empty. Cancelled.");
            return;
        }
        orders.add(order);
        order.printReceipt();
    }

    // ---------- Employee ----------

    private static void employeeMenu(Employee employee) {
        boolean isManager = employee.getPosition().equals("Manager");

        while (true) {
            System.out.println();
            System.out.println("===== Employee Menu (" + employee.getName() + ", " + employee.getPosition() + ") =====");
            System.out.println("1. Add product");
            System.out.println("2. View products");
            System.out.println("3. View users");
            System.out.println("4. View all orders");
            if (isManager) {
                System.out.println("5. Add employee");
            }
            System.out.println("0. Logout");
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    showProducts();
                    break;
                case 3:
                    showUsers();
                    break;
                case 4:
                    showOrders();
                    break;
                case 5:
                    if (isManager) {
                        addEmployee();
                    } else {
                        System.out.println("Only the manager can add employees.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addProduct() {
        System.out.println("Type: 1. Book  2. Electronics  3. Clothing");
        System.out.print("Choose type: ");
        int type = input.nextInt();
        input.nextLine();

        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Price: ");
        double price = input.nextDouble();
        System.out.print("Quantity: ");
        int quantity = input.nextInt();
        input.nextLine();

        if (name.isEmpty() || price <= 0 || quantity < 0) {
            System.out.println("Name cannot be empty, price must be > 0, quantity >= 0. Product not added.");
            return;
        }

        Product product;
        if (type == 1) {
            System.out.print("Author: ");
            String author = input.nextLine();
            System.out.print("Genre: ");
            String genre = input.nextLine();
            product = new Book(name, price, quantity, author, genre);
        } else if (type == 2) {
            System.out.print("Brand: ");
            String brand = input.nextLine();
            System.out.print("Warranty (months): ");
            int warranty = input.nextInt();
            input.nextLine(); 
            product = new Electronics(name, price, quantity, brand, warranty);
        } else if (type == 3) {
            System.out.print("Size: ");
            String size = input.nextLine();
            System.out.print("Color: ");
            String color = input.nextLine();
            product = new Clothing(name, price, quantity, size, color);
        } else {
            System.out.println("Invalid type. Product not added.");
            return;
        }

        products.add(product);
        System.out.println("Product added with ID " + product.getId() + ".");
    }

    private static void addEmployee() {
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Password (at least 6 characters): ");
        String password = input.nextLine();
        System.out.print("Position: ");
        String position = input.nextLine();

        if (name.isEmpty() || !email.contains("@") || password.length() < 6) {
            System.out.println("Invalid name, email or password. Employee not added.");
            return;
        }

        employees.add(new Employee(name, email, password, position));
        System.out.println(name + " added as " + position + ".");
    }

    // ---------- Helper methods ----------

    private static void showProducts() {
        System.out.println("===== Products =====");
        for (Product product : products) {
            product.displayInfo();
        }
    }

    private static void showUsers() {
        System.out.println("===== Customers =====");
        for (Customer customer : customers) {
            customer.displayInfo();
        }
        System.out.println("===== Employees =====");
        for (Employee employee : employees) {
            employee.displayInfo();
        }
    }

    private static void showOrders() {
        if (orders.isEmpty()) {
            System.out.println("There are no orders yet.");
            return;
        }
        for (Order order : orders) {
            order.printReceipt();
        }
    }

    private static void showMyOrders(Customer customer) {
        boolean found = false;
        for (Order order : orders) {
            if (order.getCustomer() == customer) {
                order.printReceipt();
                found = true;
            }
        }
        if (!found) {
            System.out.println("You have no orders yet.");
        }
    }

    private static Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}