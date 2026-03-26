# 🏦 Banking System (Java OOP Project)

## 📌 Overview

This is a console-based Banking System implemented in Java using Object-Oriented Programming (OOP) principles.
The system simulates real-world banking operations such as account creation, deposits, withdrawals, transfers, and transaction tracking.

---

## 🚀 Features

* ✅ Create new bank accounts (auto-generated account IDs)
* 💰 Deposit money
* 💸 Withdraw money with balance validation
* 🔁 Transfer money between accounts
* 📊 Check account balance
* 🧾 View transaction history
* 🏦 Static bank-level tracking (total accounts)

---

## 🧠 Concepts Used

This project demonstrates strong understanding of:

* Object-Oriented Programming (OOP)

  * Classes & Objects
  * Encapsulation
  * Constructors
  * Method design
* Static variables & methods
* Pass-by-value & reference handling
* Collections Framework

  * `HashMap` for efficient account lookup (O(1))
  * `ArrayList` for transaction history
* Real-world system design thinking
* Control flow & validation handling

---

## 🏗️ System Design

### 🔹 Account Class

Represents an individual user account.

* Stores user details and balance
* Handles deposit & withdrawal
* Maintains transaction history

### 🔹 Transaction Class

Represents each financial operation.

* Stores transaction ID, type, amount, timestamp

### 🔹 Bank Class

Central controller of the system.

* Manages all accounts using `HashMap`
* Handles deposit, withdrawal, and transfer operations
* Generates unique account IDs

---

## 🔄 Sample Flow

1. Create Account
2. Deposit / Withdraw
3. Transfer Money
4. View Balance
5. Check Transaction History

---

## 🛠️ Technologies Used

* Java (Core Java)
* Java Collections Framework
* Java Time API (`LocalDateTime`)

---

## ▶️ How to Run

1. Clone the repository:

   ```bash
   git clone <your-repo-link>
   ```

2. Compile the code:

   ```bash
   javac Banking_System.java
   ```

3. Run the program:

   ```bash
   java Banking_System
   ```

---

## 📈 Future Improvements

* 🔐 Add authentication (PIN/password)
* 📂 File handling for persistent storage
* 🌐 Convert to GUI or Web Application
* 🔄 Use Enum for transaction types
* 🏗️ Layered architecture (Service layer)

---

## 👨‍💻 Author

* Vineet Chauhan

---

## ⭐ Why This Project?

This project reflects:

* Strong OOP fundamentals
* Ability to design real-world systems
* Clean code structure and modular thinking

---

⭐ If you like this project, consider giving it a star!
