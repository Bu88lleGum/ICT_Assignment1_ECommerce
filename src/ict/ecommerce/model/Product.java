package ict.ecommerce.model;

// Parent class for books, electronics and clothing.
public class Product {
    private int id;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        if (price >= 0) {
            this.price = price;
        }
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        }
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

    public double getPrice() {
        return price;
    }

    // Negative prices are not allowed.
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    // Negative stock is not allowed.
    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        }
    }

    public void displayInfo() {
        System.out.println("Product #" + id + " | " + name + " | " + price
                + " KZT | Stock: " + stockQuantity);
    }
}
