package ict.ecommerce.model;

// Parent class for both customers and employees.
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    // Password is checked, not printed or returned by a getter.
    public boolean checkPassword(String enteredPassword) {
        return password.equals(enteredPassword);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void displayInfo() {
        System.out.println("User #" + id + " | " + name + " | " + email + " | " + role);
    }
}
