# 🏦 Banking System (Java OOP Project)

A **console-based Banking System** built using **core Java and Object-Oriented Programming (OOP)** principles.
This project simulates real-world banking operations such as account creation, deposits, withdrawals, transfers, and transaction tracking.

---

## 🚀 Features

* ✅ Create **Savings Account** and **Current Account**
* 💰 Deposit & Withdraw money
* 🔁 Transfer money between accounts
* 📊 Check account balance
* 🧾 View transaction history with timestamps
* 📈 Calculate interest (Savings Account only)
* 🏦 Overdraft facility for Current Account
* 🔐 Data encapsulation and validation

---

## 🧠 OOP Concepts Implemented

### 🔹 Abstraction

* `Account` is an **abstract class**
* Defines common behavior for all account types

### 🔹 Inheritance

* `SavingsAccount` and `CurrentAccount` extend `Account`

### 🔹 Polymorphism

* Method overriding:

  * `calculateInterest()`
  * `withdraw()`
* Parent reference (`Account`) used for child objects

### 🔹 Encapsulation

* Private data members (`balance`, `customerId`, etc.)
* Controlled access using getters and methods

### 🔹 Method Overriding

* Different behavior for:

  * Interest calculation
  * Withdrawal logic

---

## 🏗️ Project Structure

```
Banking_System
│
├── Account (abstract)
│   ├── SavingsAccount
│   └── CurrentAccount
│
├── Transaction
├── Bank
└── Main (Menu-driven system)
```

---

## ⚙️ Technologies Used

* Java (Core Java)
* OOP Principles
* Collections Framework (`HashMap`, `ArrayList`)
* Date & Time API (`LocalDateTime`)

---

## ▶️ How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/banking-system-java.git
   ```

2. Navigate to the project folder:

   ```bash
   cd banking-system-java
   ```

3. Compile the program:

   ```bash
   javac Banking_System.java
   ```

4. Run the program:

   ```bash
   java Banking_System
   ```

---

## 📋 Menu Options

```
1. Create Account
2. Deposit
3. Withdraw
4. Transfer
5. Check Balance
6. Show Transactions
7. Calculate Interest
8. Exit
```

---

## 💡 Key Highlights

* Uses **HashMap for efficient account lookup (O(1))**
* Implements **real-world banking logic**
* Clean and scalable design using OOP
* Demonstrates strong understanding of **Java fundamentals**

---

## 📌 Future Improvements

* 🔹 Add Enum for transaction types
* 🔹 File handling / database integration
* 🔹 User authentication (PIN / password)
* 🔹 GUI or Web-based interface (Spring Boot)

---

## 👨‍💻 Author

**Vineet Chauhan**

---

## ⭐ If you like this project

Give it a ⭐ on GitHub and feel free to contribute!
