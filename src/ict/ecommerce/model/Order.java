package ict.ecommerce.model;

import java.util.ArrayList;

// An order HAS-A customer and HAS multiple order items.
public class Order {
    private int orderId;
    private Customer customer;
    private ArrayList<OrderItem> items;
    private double totalAmount;

    public Order(int orderId, Customer customer, ArrayList<OrderItem> cart) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();

        // Copy order lines so later cart changes do not change an existing order.
        double total = 0;
        for (OrderItem item : cart) {
            OrderItem copy = new OrderItem(item.getProduct(), item.getQuantity());
            items.add(copy);
            total += copy.getLineTotal();
        }
        this.totalAmount = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getLineTotal();
        }
        return total;
    }

    public void printReceipt() {
        System.out.println("----------------------------------------");
        System.out.println("RECEIPT | Order #" + orderId);
        System.out.println("Customer: " + customer.getName());
        for (OrderItem item : items) {
            item.printLine();
        }
        System.out.println("TOTAL: " + totalAmount + " KZT");
        System.out.println("----------------------------------------");
    }
}
