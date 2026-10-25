package ict.ecommerce;

import ict.ecommerce.model.*;
import java.util.ArrayList;

// Stores users, products and orders in memory using ArrayList.
public class Store {
    private ArrayList<User> users;
    private ArrayList<Product> products;
    private ArrayList<Order> orders;
    private int nextUserId;
    private int nextProductId;
    private int nextOrderId;

    public Store() {
        users = new ArrayList<>();
        products = new ArrayList<>();
        orders = new ArrayList<>();
        nextUserId = 1;
        nextProductId = 1;
        nextOrderId = 1;

        // Demo records make the application easy to demonstrate in a lab.
        users.add(new Customer(nextUserId, "Demo Customer", "customer@demo.com", "1234"));
        nextUserId++;
        users.add(new Employee(nextUserId, "Demo Employee", "employee@demo.com", "1234", "Sales"));
        nextUserId++;
        products.add(new Book(nextProductId, "Java Basics", 7000.0, 6, "A. Teacher"));
        nextProductId++;
        products.add(new Electronics(nextProductId, "Headphones", 15000.0, 4, 12));
        nextProductId++;
        products.add(new Clothing(nextProductId, "T-Shirt", 5500.0, 8, "M"));
        nextProductId++;
    }

    public boolean emailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public Customer registerCustomer(String name, String email, String password) {
        if (emailExists(email)) {
            return null;
        }
        Customer customer = new Customer(nextUserId, name, email, password);
        nextUserId++;
        users.add(customer);
        return customer;
    }

    public Employee registerEmployee(String name, String email, String password, String department) {
        if (emailExists(email)) {
            return null;
        }
        Employee employee = new Employee(nextUserId, name, email, password, department);
        nextUserId++;
        users.add(employee);
        return employee;
    }

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.checkPassword(password)) {
                return user;
            }
        }
        return null;
    }

    public void listUsers() {
        System.out.println("\n=== ALL USERS ===");
        for (User user : users) {
            user.displayInfo();
        }
    }

    public Book addBook(String name, double price, int stock, String author) {
        Book book = new Book(nextProductId, name, price, stock, author);
        nextProductId++;
        products.add(book);
        return book;
    }

    public Electronics addElectronics(String name, double price, int stock, int warranty) {
        Electronics electronics = new Electronics(nextProductId, name, price, stock, warranty);
        nextProductId++;
        products.add(electronics);
        return electronics;
    }

    public Clothing addClothing(String name, double price, int stock, String size) {
        Clothing clothing = new Clothing(nextProductId, name, price, stock, size);
        nextProductId++;
        products.add(clothing);
        return clothing;
    }

    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void listProducts() {
        System.out.println("\n=== AVAILABLE PRODUCTS ===");
        if (products.isEmpty()) {
            System.out.println("No products available.");
        }
        for (Product product : products) {
            product.displayInfo();
        }
    }

    // First check stock for EVERY line. Only then modify stock and create the order.
    public Order placeOrder(Customer customer, ArrayList<OrderItem> cart) {
        if (cart.isEmpty()) {
            return null;
        }
        for (OrderItem item : cart) {
            if (item.getQuantity() <= 0
                    || item.getQuantity() > item.getProduct().getStockQuantity()) {
                return null;
            }
        }

        // No validation failed: the order can now be completed.
        Order order = new Order(nextOrderId, customer, cart);
        nextOrderId++;
        for (OrderItem item : cart) {
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
        }
        orders.add(order);
        customer.addLoyaltyPoints(1);
        return order;
    }

    public void listAllOrders() {
        System.out.println("\n=== ALL ORDERS ===");
        if (orders.isEmpty()) {
            System.out.println("No orders have been placed yet.");
        }
        for (Order order : orders) {
            order.printReceipt();
        }
    }

    public void listCustomerOrders(Customer customer) {
        System.out.println("\n=== MY ORDERS ===");
        boolean found = false;
        for (Order order : orders) {
            if (order.getCustomer().getId() == customer.getId()) {
                order.printReceipt();
                found = true;
            }
        }
        if (!found) {
            System.out.println("You do not have any orders yet.");
        }
    }
}
