package ict.ecommerce.model;

// A customer IS-A user.
public class Customer extends User {
    private int loyaltyPoints;

    public Customer(int id, String name, String email, String password) {
        super(id, name, email, password, "Customer");
        this.loyaltyPoints = 0;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            loyaltyPoints += points;
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Loyalty points: " + loyaltyPoints);
    }
}
