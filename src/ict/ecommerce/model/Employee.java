package ict.ecommerce.model;

// An employee IS-A user.
public class Employee extends User {
    private String department;

    public Employee(int id, String name, String email, String password, String department) {
        super(id, name, email, password, "Employee");
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Department: " + department);
    }
}
