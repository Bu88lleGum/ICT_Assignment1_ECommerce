# Assignment 1: Code explanation and oral defense guide (RU / EN)

> **Purpose:** You may use AI tools to prepare code, but you must **understand it, be able to run it, and explain every important line** yourself. Start by opening `src/ict/ecommerce/Main.java` and tracing calls into `Store.java`, then into the model classes.

## 1. What was built / Что сделано

The project is a small **console e-commerce application**, not a website. The user interacts via numbered menus. Customer accounts can list products, place orders and view their own receipts. Employee accounts can create books/electronics/clothing, register users, list them, and see all orders. Data is stored in **three ArrayLists**, so it resets on restart.

**Зачем ровно такие классы:**

| File | Role in the assignment | One-line English explanation |
|---|---|---|
| `User.java` | Базовый пользователь: имя, email, пароль, роль | `User is the parent class with common user attributes.` |
| `Customer.java` | Покупатель, наследник User | `Customer extends User and adds loyalty points.` |
| `Employee.java` | Сотрудник, наследник User | `Employee extends User and adds department information.` |
| `Product.java` | Базовый товар и остаток на складе | `Product is the parent class with price and stock.` |
| `Book.java` | Товар-книга, `author` | `Book extends Product and adds an author.` |
| `Electronics.java` | Электроника, гарантия | `Electronics extends Product and adds warranty months.` |
| `Clothing.java` | Одежда, размер | `Clothing extends Product and adds size.` |
| `OrderItem.java` | Строка заказа: товар, количество, цена | `OrderItem stores the product, quantity, and unit price.` |
| `Order.java` | Заказ: покупатель и список строк, чек | `Order contains a customer and order lines and calculates the total.` |
| `Store.java` | Хранение пользователей, товаров, заказов | `Store manages three ArrayLists and the business operations.` |
| `Main.java` | Запуск, Scanner, меню, ввод | `Main provides the console interface and calls Store methods.` |

**Почему есть OrderItem, хотя его нет в примере задания:** одна покупка содержит товар **и количество**. Без этого сложно хранить разные количества и считать итоговую сумму. Это обычный класс с private-полями, конструктором и методами — всё из Modules 2–4. Он не использует новые технологии.

## 2. The Java keywords you MUST explain / Ключевые слова

| Code | Russian explanation | English explanation |
|---|---|---|
| `package ict.ecommerce.model;` | Помещает класс в пакет | `It declares the package of the class.` |
| `import java.util.ArrayList;` | Подключает имя стандартного класса | `It imports the ArrayList type.` |
| `public class User` | Публичный класс | `This declares a public class.` |
| `private String name;` | Поле закрыто от других классов | `This field is private for encapsulation.` |
| `public User(...)` | Конструктор | `This is the constructor, which initializes a new object.` |
| `this.name = name;` | Записывает параметр в поле текущего объекта | `This assigns the parameter to the object's field.` |
| `Customer extends User` | Customer наследует User | `Customer is a subclass of User.` |
| `super(...)` | Вызывает конструктор родителя | `It calls the parent constructor.` |
| `@Override` | Проверяет переопределение метода | `It indicates the subclass overrides a method.` |
| `super.displayInfo()` | Вызов реализации родительского метода | `It calls the superclass version.` |
| `void` | Нет возвращаемого значения | `This method does not return a value.` |
| `return value;` | Вернуть значение и завершить метод | `It returns the result to the caller.` |
| `static` | Принадлежит классу, не объекту | `It belongs to the class rather than an instance.` |
| `final` | Нельзя переназначить переменную | `The variable cannot be reassigned after initialization.` |
| `new` | Создание экземпляра класса | `It creates a new object.` |
| `null` | Нет объекта по ссылке | `It indicates that no object reference was found.` |
| `==` | Сравнение чисел по значению | `It compares primitive values.` |
| `.equals(...)` | Сравнение строк по содержанию | `It compares text content.` |
| `if / else` | Ветвление | `It executes a branch based on a condition.` |
| `switch / case` | Выбор меню | `It selects an action for the menu choice.` |
| `while` | Повторять, пока условие true | `It repeats the menu until exit is chosen.` |
| `for (OrderItem item : cart)` | Для каждого элемента списка | `This is an enhanced for loop over the collection.` |
| `break` | Выйти из цикла или ветви switch | `It ends the current loop or switch branch.` |
| `continue` | Пропустить итерацию (используется как концепция, не в этом коде) | `It skips to the next loop iteration.` |
| `instanceof` | Проверить реальный тип объекта | `It checks whether an object has a given type.` |
| `(Customer) user` | Явное приведение ссылки к Customer | `It casts a User reference to Customer after a type check.` |
| `ArrayList<Product>` | Динамический список товаров | `A resizable collection of Product references.` |
| `.add()`, `.size()`, `.isEmpty()` | Работа со списком | `Collection methods to add, count and check emptiness.` |

## 3. Trace the program from the first line / Путь выполнения

1. Java starts at `public static void main(String[] args)` in **Main.java**.
2. `Store store = new Store()` creates the object that contains three ArrayLists. Its constructor creates demo users and products.
3. `while (running)` repeatedly prints the main menu. `readInt()` reads numbers using `Scanner`.
4. `switch (choice)` determines which menu action runs.
5. `login()` calls `store.login(email, password)`. `Store` loops through the `users` collection and compares email and password.
6. `instanceof` selects the appropriate customer or employee menu based on the actual object type.
7. Employee options call `store.addBook(...)`, `store.addElectronics(...)` or `store.addClothing(...)` and add subclass objects into `ArrayList<Product>`.
8. Customer `placeOrder()` builds an `ArrayList<OrderItem> cart` with selected products and quantities.
9. `Store.placeOrder(...)` checks **every item** before changing any stock. If there is not enough stock, it returns `null`.
10. When valid, an `Order` is created, stock is reduced, it is added to `orders`, and `order.printReceipt()` prints the result.

## 4. Explain important lines — Main.java / Объясняем строки

```java
private static final Scanner scanner = new Scanner(System.in);
```

- **private**: only `Main` uses this field.
- **static**: all static menu methods use one scanner, without creating `Main` objects.
- **final**: the variable always refers to the same scanner object.
- **Scanner**: standard Java tool for reading console input.
- **new Scanner(System.in)**: creates a scanner connected to the keyboard/standard input.

```java
private static final Store store = new Store();
```

- **Store**: the application's data/logic object.
- **new Store()**: calls its constructor and adds demo records.
- **static final**: one shared store object for this running program.

```java
while (running) { ... }
```

- Keeps the main menu open until option zero sets `running = false`.

```java
if (user == null) { ... }
else if (user instanceof Customer) { customerMenu((Customer) user); }
```

- `null` means login returned no matching user.
- `instanceof Customer` checks the object is a Customer.
- `(Customer) user` is a type cast, safe after that check.
- `customerMenu(...)` receives the customer instance.

```java
if (scanner.hasNextInt()) {
    int value = scanner.nextInt();
    scanner.nextLine();
    return value;
}
```

- `hasNextInt()` checks that an integer was entered instead of crashing on bad text.
- `nextInt()` reads the number.
- `nextLine()` consumes the remaining input line, so the next String read does not accidentally get an empty line.
- `return` gives the number to the calling menu.

```java
scanner.useLocale(Locale.US);
```

- Tells Scanner to use a dot as the decimal separator, so entering `1500.50` works consistently.

## 5. Explain important lines — Store.java

```java
private ArrayList<User> users;
private ArrayList<Product> products;
private ArrayList<Order> orders;
```

All fields are **private**. These collections store user, product and order references. `ArrayList` is explicitly required by Assignment 1.

```java
for (User user : users) {
    if (user.getEmail().equalsIgnoreCase(email)
            && user.checkPassword(password)) {
        return user;
    }
}
return null;
```

- `for`: examine every stored user until finding a match.
- `getEmail`: encapsulation; access the private email via getter.
- `equalsIgnoreCase`: emails are compared without case differences.
- `&&`: both the email and password must match.
- `return user`: send the matching User object to `Main`.
- `return null`: no match was found.

```java
for (OrderItem item : cart) {
    if (item.getQuantity() <= 0
            || item.getQuantity() > item.getProduct().getStockQuantity()) {
        return null;
    }
}
```

This first pass prevents invalid orders: negative/zero quantity or more than the available stock. No stock changes happen until after **the entire cart** has passed validation.

```java
Order order = new Order(nextOrderId, customer, cart);
nextOrderId++;
for (OrderItem item : cart) {
    Product product = item.getProduct();
    product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
}
orders.add(order);
```

- Creates an order using constructor parameters.
- `nextOrderId++` increases the ID for the next order.
- Enhanced `for`: visits every line of the accepted cart.
- `getStockQuantity() - getQuantity()`: new stock level.
- `setStockQuantity(...)`: updates a **private** field via a controlled setter.
- `orders.add(order)`: saves the new order in memory.

**Important limitation:** this is a single-user educational console application. Real concurrent e-commerce needs database transactions for safe shared inventory, and production password hashing. Neither is required in Modules 2–4.

## 6. Explain important lines — model classes

```java
public class Customer extends User {
    public Customer(int id, String name, String email, String password) {
        super(id, name, email, password, "Customer");
    }
}
```

`Customer IS-A User`. The `super(...)` call initializes the inherited part of the object (id, name, email, password, role).

```java
@Override
public void displayInfo() {
    super.displayInfo();
    System.out.println("  Loyalty points: " + loyaltyPoints);
}
```

`Customer` **overrides** `User.displayInfo()`: first it prints common info from the parent, then customer-specific info. This is runtime polymorphism.

```java
OrderItem item = new OrderItem(product, quantity);
```

The constructor saves a reference to `Product`, the quantity, and a **snapshot** of its current unit price. That makes the order price stable even if the product price is edited later.

```java
double total = 0;
for (OrderItem item : items) {
    total += item.getLineTotal();
}
return total;
```

The total amount is the sum of all order lines. This demonstrates methods, `double`, `ArrayList`, enhanced for loops and return values from Module 2.

## 7. How to explain UML / Как объяснить UML

**UML** stands for **Unified Modeling Language**. A UML class diagram is a picture of the classes, private attributes, public methods and their relationships. You can edit the supplied `.drawio` file using https://app.diagrams.net/ (draw.io / diagrams.net); that software is fine and was used to prepare course diagrams.

- Box **User** -> boxes **Customer** and **Employee**: inheritance, hollow triangle points to parent User.
- Box **Product** -> **Book**, **Electronics**, **Clothing**: inheritance.
- `Order.customer: Customer` means one order knows its buyer (HAS-A association).
- `Order.items: ArrayList<OrderItem>` means one order contains one or more order lines.
- `OrderItem.product: Product` associates an order line with a product.
- `Store` manages three collections (aggregation); `Main` keeps a `Store` in a static field (association) and also uses `OrderItem`, `Customer`, `Product` etc. as parameters and local variables (dependency).
- `-` before an attribute means private; `+` before a method means public.

**Why not an inheritance arrow from Order to Product?** Because an order **is not a** product. An order **has** product-containing order lines, so it is association, not inheritance.

## 8. Oral defense questions: answers in English

**Q1. What is the project?**  
A: This is a Java console-based e-commerce system. Employees manage users and products, and customers place and view orders.

**Q2. Why use Java?**  
A: Java is the required programming language, and it supports object-oriented programming.

**Q3. What is a class? What is an object?**  
A: A class is a blueprint. An object is an instance of that class.

**Q4. Why does Customer extend User?**  
A: A customer is a type of user. Inheritance lets us reuse the common name, email and password fields.

**Q5. Why are fields private?**  
A: To apply encapsulation and prevent uncontrolled access. Getters and setters provide controlled access.

**Q6. Why use super()?**  
A: It calls the parent class constructor to initialize inherited fields.

**Q7. What is @Override?**  
A: It marks a method that overrides a parent method, and the compiler checks that it is a valid override.

**Q8. Where is polymorphism?**  
A: The `ArrayList<Product>` contains Book, Electronics and Clothing objects; calling `product.displayInfo()` uses each object's implementation.

**Q9. What is association?**  
A: A relationship between objects. An Order has a Customer and OrderItem objects; an OrderItem refers to a Product.

**Q10. What is the difference between inheritance and association?**  
A: Inheritance means IS-A; association means an object is connected to, or HAS-A, another object.

**Q11. Why use OrderItem?**  
A: It stores product, quantity and unit price together. An order can contain different products in different quantities.

**Q12. Why use ArrayList instead of an array?**  
A: Its size is dynamic, and we do not know in advance how many products, users or orders there will be.

**Q13. How does logging in work?**  
A: We iterate over the stored users and compare email and password. We then select the menu based on the user's subclass.

**Q14. How do you calculate the total?**  
A: Each OrderItem calculates price times quantity. The Order sums those line totals.

**Q15. How do you prevent negative stock?**  
A: We validate quantities against the available stock before completing an order and only then reduce stock.

**Q16. What happens if the cart is empty?**  
A: The order is cancelled and no stock is changed.

**Q17. Why use Scanner?**  
A: The assignment requires console input, and Scanner reads the user's choices and values.

**Q18. Why use while and switch?**  
A: The while loop repeatedly displays menus. Switch selects the action based on the number entered.

**Q19. What do static and final mean here?**  
A: Static fields belong to Main as a class; final prevents reassignment of the scanner and store references.

**Q20. Does the project use a database?**  
A: No. The assignment requires ArrayList, so this version stores all data only in memory.

**Q21. Why are the passwords not encrypted?**  
A: This is a basic demonstration based on Modules 2–4. Plain-text demo passwords are not suitable for production. A real system would use secure password hashing.

**Q22. What did you test?**  
A: I tested both roles, product and user creation, successful checkout, cart cancellation, insufficient stock, duplicate email and invalid menu input.

**Q23. What is the main difference between a getter and a setter?**  
A: A getter returns a field's value; a setter changes it, optionally checking whether the new value is valid.

**Q24. What is constructor chaining?**  
A: It means that a subclass constructor invokes a superclass constructor using super(...).

**Q25. What is the key limitation?**  
A: This is a single-process, in-memory educational example. Data is lost after exiting the program.

## 9. A four-minute oral demonstration script

1. **English**: “This is the main menu. I will log in as an employee.” Use employee demo credentials.
2. “This employee can manage products and users. I can add a book; it is a subclass of Product.” Add or list a product.
3. “Now I log out and log in as a customer.” Use customer credentials.
4. “A customer can browse products and place an order. I select two books and one pair of headphones.” Confirm.
5. “The Order stores its Customer and OrderItem list. Each OrderItem has a quantity and unit price.” Point to Order.java and OrderItem.java.
6. “Stock is checked first, then reduced after successful checkout. The receipt shows a correct total.” Show receipt and inventory.
7. “These classes and their inheritance/associations are shown in my UML diagram.” Open `.drawio`.
8. “All model fields are private for encapsulation, and ArrayList stores users, products, and orders.” Open Product.java and Store.java.

## 10. Before submission / Перед отправкой

- Replace student name, student ID and submission date on the report cover.
- Open and run the app in IntelliJ IDEA; demonstrate successful checkout.
- Open the editable UML and compare class names, attributes and relationships with the code.
- Go through `Main.java` and `Store.java` using this guide and explain why each method exists.
- Keep the report PDF open, source code open in IntelliJ, and UML ready to show at the oral exam.
- Submit the personalized report **before** the laboratory oral examination using the official instructions.
