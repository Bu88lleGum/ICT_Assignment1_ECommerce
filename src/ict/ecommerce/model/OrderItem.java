package ict.ecommerce.model;

// One line in an order: which product, how many, and at what price.
public class OrderItem {
    private Product product;
    private int quantity;
    private double unitPrice;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getLineTotal() {
        return unitPrice * quantity;
    }

    public void printLine() {
        System.out.println("  " + product.getName() + " x " + quantity + " @ "
                + unitPrice + " KZT = " + getLineTotal() + " KZT");
    }
}
