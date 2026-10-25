# ICT Assignment 1 - Console-Based E-Commerce System

A self-contained Java console application designed for the **Information and Communication Technologies** course (Modules 2-4). It does not require Maven, Gradle, databases, frameworks, or third-party libraries.

## Open and run in IntelliJ IDEA

1. Unzip the archive and open the **ICT_Assignment1_ECommerce** folder using **File > Open** in IntelliJ IDEA.
2. If asked, select **Trust Project**. Set **Project SDK to JDK 17 or later** in File > Project Structure > Project if the IDE requests an SDK. If necessary, mark `src` as **Sources Root** (normally already configured).
3. Choose run configuration **ECommerce - Main** and press the green Run button. Alternatively, open `src/ict/ecommerce/Main.java` and click the green triangle next to `main`.
4. The app runs in the **Run** console. Enter option numbers and other values, pressing Enter each time.

### Demo login credentials (for local classroom demonstration ONLY)

- Employee: **employee@demo.com** / **1234**
- Customer: **customer@demo.com** / **1234**

An employee can add products, add users, and view all orders. A customer can browse products, place orders, and view only their own orders. The program includes 3 demo products.

## Important limitations

- The data exists **only in memory** (`ArrayList`). It resets every time you stop and restart the program.
- Passwords are stored as plain text **only because this is a small educational console project**; never use this approach in a real application.
- Use a **dot** when entering decimal prices: `1500.50`.
- No payment integration, database, website, or advanced Java technologies are needed by Assignment 1.

## Project files

- `src/ict/ecommerce/Main.java`: console input and menus.
- `src/ict/ecommerce/Store.java`: uses 3 `ArrayList` collections; registers users, stores products, creates orders.
- `src/ict/ecommerce/model`: User, Customer, Employee, Product, Book, Electronics, Clothing, Order and OrderItem.
- `diagrams/ECommerce_UML.drawio`: **editable UML in diagrams.net (draw.io)**.
- `diagrams/ECommerce_UML.png`: overview picture; `diagrams/ECommerce_UML_Full.png`: full class diagram.
- `docs/Assignment1_Report.pdf`: 22-page PDF report: requirements, testing, full source-code appendix (A) and full-size UML (B). Its student name/ID/date fields on the cover are **fillable**.
- `docs/ORAL_DEFENSE_GUIDE_RU_EN.md`: explanation and possible oral-defense questions.
- `docs/TEST_CASES.md`: verification scenarios.

## Command-line alternative (no IDE)

From the **project root** with Java 17+ installed:

```bash
mkdir -p out
javac -encoding UTF-8 -d out src/ict/ecommerce/model/*.java src/ict/ecommerce/*.java
java -cp out ict.ecommerce.Main
```

If your instructor wants a Git URL in the report, first publish **your own** project repository. Do not use the course author's repository URL as if it were your code repository. The PDF supplied here includes the full source-code appendix instead.

**Before submission:** replace the student name, student ID, and date on the cover page of the report. Source code and oral defense must be your own understood work.

## Git / GitHub

```bash
cd ICT_Assignment1_ECommerce
git init
git add .
git commit -m "ICT Assignment 1: console e-commerce system"
git branch -M main
git remote add origin https://github.com/<your-username>/<repo-name>.git
git push -u origin main
```
