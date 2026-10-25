# Test cases and verification evidence

The source code was compiled and the console application was run with scripted inputs. These scenarios were **actually executed**, not simply proposed.

| ID | Scenario | Expected behavior | Result |
|---|---|---|---|
| T01 | Browse as guest | Lists 3 seed products | PASS |
| T02 | Incorrect login | Displays wrong credentials message | PASS |
| T03 | Employee manages catalog and users | Can add Book, Electronics, Clothing, Customer and Employee | PASS |
| T04 | Customer orders 2 books + 1 headphones | Total = 29,000 KZT; stock decreases to 4 and 3 | PASS |
| T05 | Try buying 10 books (6 in stock) | Rejects quantity, empty order cancelled | PASS |
| T06 | Cancel order before confirmation | No stock changes | PASS |
| T07 | Add same product multiple times | Combines quantities, rejects excess, total correct | PASS |
| T08 | Enter invalid menu text instead of number | Prints validation message and continues | PASS |
| T09 | Register existing email | Rejects duplicate email | PASS |

All nine tests passed with Java compiler/runtime during packaging. Full console transcript for T04 is in `examples/successful_order_console_transcript.txt`.

### Test T04 — key output

```text
RECEIPT | Order #1
Customer: Demo Customer
  Java Basics x 2 @ 7000.0 KZT = 14000.0 KZT
  Headphones x 1 @ 15000.0 KZT = 15000.0 KZT
TOTAL: 29000.0 KZT
```

### Important scope and limitations

- Single-process console application, not a networked service; no concurrency testing.
- Data is in-memory; no database/persistence is required by Assignment 1.
- Demo passwords are intentionally simple and stored in-memory as plain text; this is **not suitable for production**.
- Currency is displayed in KZT; `double` is used for course simplicity rather than the production-standard BigDecimal.
- All testing is performed with Java standard library and external console scripts; no project dependencies or JUnit are required.
