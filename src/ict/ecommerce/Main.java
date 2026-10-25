package ict.ecommerce;

import ict.ecommerce.model.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// Console interface: input, menus and messages are handled here.
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Store store = new Store();

    public static void main(String[] args) {
        // Use a period as the decimal separator for prices (e.g. 1999.50).
        scanner.useLocale(Locale.US);
        System.out.println("Welcome to the ICT Console E-Commerce System!");
        System.out.println("DEMO customer: customer@demo.com / 1234");
        System.out.println("DEMO employee: employee@demo.com / 1234");

        boolean running = true;
        while (running) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Register as customer");
            System.out.println("2. Log in");
            System.out.println("3. Browse products");
            System.out.println("0. Exit");
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    store.listProducts();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid menu option.");
            }
        }
        scanner.close();
        System.out.println("Goodbye!");
    }

    private static void registerCustomer() {
        System.out.println("\n--- REGISTER CUSTOMER ---");
        String name = readText("Full name: ");
        String email = readText("Email: ");
        if (store.emailExists(email)) {
            System.out.println("This email is already registered.");
            return;
        }
        String password = readText("Password: ");
        Customer customer = store.registerCustomer(name, email, password);
        System.out.println("Customer added! ID: " + customer.getId());
    }

    private static void registerEmployee() {
        System.out.println("\n--- ADD EMPLOYEE ---");
        String name = readText("Full name: ");
        String email = readText("Email: ");
        if (store.emailExists(email)) {
            System.out.println("This email is already registered.");
            return;
        }
        String password = readText("Password: ");
        String department = readText("Department: ");
        Employee employee = store.registerEmployee(name, email, password, department);
        System.out.println("Employee added! ID: " + employee.getId());
    }

    private static void login() {
        System.out.println("\n--- LOGIN ---");
        String email = readText("Email: ");
        String password = readText("Password: ");
        User user = store.login(email, password);
        if (user == null) {
            System.out.println("Wrong email or password.");
        } else if (user instanceof Customer) {
            customerMenu((Customer) user);
        } else if (user instanceof Employee) {
            employeeMenu((Employee) user);
        }
    }

    private static void customerMenu(Customer customer) {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n===== CUSTOMER: " + customer.getName() + " =====");
            System.out.println("1. List products");
            System.out.println("2. Place order");
            System.out.println("3. View my orders");
            System.out.println("4. View my profile");
            System.out.println("0. Log out");
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1:
                    store.listProducts();
                    break;
                case 2:
                    placeOrder(customer);
                    break;
                case 3:
                    store.listCustomerOrders(customer);
                    break;
                case 4:
                    customer.displayInfo();
                    break;
                case 0:
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }

    private static void employeeMenu(Employee employee) {
        boolean logout = false;
        while (!logout) {
            System.out.println("\n===== EMPLOYEE: " + employee.getName() + " =====");
            System.out.println("1. List products");
            System.out.println("2. Add book");
            System.out.println("3. Add electronics");
            System.out.println("4. Add clothing");
            System.out.println("5. List all users");
            System.out.println("6. Add customer");
            System.out.println("7. Add employee");
            System.out.println("8. View all orders");
            System.out.println("0. Log out");
            int choice = readInt("Choose: ");

            switch (choice) {
                case 1:
                    store.listProducts();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    addElectronics();
                    break;
                case 4:
                    addClothing();
                    break;
                case 5:
                    store.listUsers();
                    break;
                case 6:
                    registerCustomer();
                    break;
                case 7:
                    registerEmployee();
                    break;
                case 8:
                    store.listAllOrders();
                    break;
                case 0:
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }

    private static void addBook() {
        System.out.println("\n--- ADD BOOK ---");
        String name = readText("Name: ");
        double price = readPositiveDouble("Price (KZT): ");
        int stock = readNonNegativeInt("Stock quantity: ");
        String author = readText("Author: ");
        Book book = store.addBook(name, price, stock, author);
        System.out.println("Book added with ID " + book.getId());
    }

    private static void addElectronics() {
        System.out.println("\n--- ADD ELECTRONICS ---");
        String name = readText("Name: ");
        double price = readPositiveDouble("Price (KZT): ");
        int stock = readNonNegativeInt("Stock quantity: ");
        int warranty = readNonNegativeInt("Warranty in months: ");
        Electronics electronics = store.addElectronics(name, price, stock, warranty);
        System.out.println("Electronics added with ID " + electronics.getId());
    }

    private static void addClothing() {
        System.out.println("\n--- ADD CLOTHING ---");
        String name = readText("Name: ");
        double price = readPositiveDouble("Price (KZT): ");
        int stock = readNonNegativeInt("Stock quantity: ");
        String size = readText("Size (S/M/L/XL): ");
        Clothing clothing = store.addClothing(name, price, stock, size);
        System.out.println("Clothing added with ID " + clothing.getId());
    }

    private static void placeOrder(Customer customer) {
        ArrayList<OrderItem> cart = new ArrayList<>();
        System.out.println("\n--- NEW ORDER (0 = finish selecting) ---");
        boolean selecting = true;

        while (selecting) {
            store.listProducts();
            int id = readInt("Product ID (0 to finish): ");
            if (id == 0) {
                selecting = false;
            } else {
                Product product = store.findProductById(id);
                if (product == null) {
                    System.out.println("Product not found.");
                } else if (product.getStockQuantity() == 0) {
                    System.out.println("This product is out of stock.");
                } else {
                    int quantity = readPositiveInt("Quantity: ");
                    addToCart(cart, product, quantity);
                }
            }
        }

        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Order cancelled.");
            return;
        }

        double previewTotal = 0;
        System.out.println("\n--- ORDER PREVIEW ---");
        for (OrderItem item : cart) {
            item.printLine();
            previewTotal += item.getLineTotal();
        }
        System.out.println("TOTAL: " + previewTotal + " KZT");
        int confirmation = readInt("Confirm order? 1 = yes, 0 = no: ");
        if (confirmation != 1) {
            System.out.println("Order cancelled. Stock was not changed.");
            return;
        }

        Order order = store.placeOrder(customer, cart);
        if (order == null) {
            System.out.println("Order failed: check stock quantities.");
        } else {
            System.out.println("Order placed successfully!");
            order.printReceipt();
        }
    }

    private static void addToCart(ArrayList<OrderItem> cart, Product product, int quantity) {
        // If the product is already in the cart, increase its quantity.
        for (OrderItem item : cart) {
            if (item.getProduct().getId() == product.getId()) {
                if (quantity > product.getStockQuantity() - item.getQuantity()) {
                    System.out.println("Not enough stock. Available: " + product.getStockQuantity());
                } else {
                    int totalQuantity = item.getQuantity() + quantity;
                    item.setQuantity(totalQuantity);
                    System.out.println("Updated quantity in cart: " + totalQuantity);
                }
                return;
            }
        }

        if (quantity > product.getStockQuantity()) {
            System.out.println("Not enough stock. Available: " + product.getStockQuantity());
        } else {
            cart.add(new OrderItem(product, quantity));
            System.out.println("Added to cart.");
        }
    }

    // All numeric input uses Scanner; invalid tokens are safely skipped.
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }
            System.out.println("Enter a valid whole number.");
            scanner.nextLine();
        }
    }

    private static int readPositiveInt(String prompt) {
        int value = readInt(prompt);
        while (value <= 0) {
            System.out.println("Number must be greater than zero.");
            value = readInt(prompt);
        }
        return value;
    }

    private static int readNonNegativeInt(String prompt) {
        int value = readInt(prompt);
        while (value < 0) {
            System.out.println("Number cannot be negative.");
            value = readInt(prompt);
        }
        return value;
    }

    private static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value > 0 && !Double.isInfinite(value)) {
                    return value;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("Enter a positive price, using a dot (e.g. 1500.50).");
        }
    }

    private static String readText(String prompt) {
        String value = "";
        while (value.trim().isEmpty()) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("Text cannot be empty.");
            }
        }
        return value;
    }
}
