package bookshop.users;

public class User {
    private static int nextId = 1;

    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User(String name, String email, String password, String role) {
        this.id = nextId;
        nextId++;
        setName(name);
        setEmail(email);
        setPassword(password);
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Error: email must contain @");
        }
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.password = password;
        } else {
            System.out.println("Error: password must be at least 6 characters");
        }
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getRole() {
        return role;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + " | " + name + " | " + email + " | Role: " + role);
    }
}