import java.util.*;
import java.time.LocalDateTime;

// ABSTRACT CLASS
abstract class Account {
    static String bankName = "SBI";

    private int customerId;
    private String name;
    private int age;
    private String branchName;
    private double balance;
    private ArrayList<Transaction> transactions;

    Account(int customerId, String name, int age, String branchName, double balance) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.branchName = branchName;
        this.balance = Math.max(balance, 0);
        this.transactions = new ArrayList<>();
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction(amount, "DEPOSIT"));
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount!");
        }
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction(amount, "WITHDRAW"));
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    // Protected helper for child classes
    protected void updateBalance(double newBalance) {
        this.balance = newBalance;
    }

    int getCustomerId() {
        return customerId;
    }

    String getName() {
        return name;
    }

    double getBalance() {
        return balance;
    }

    void display() {
        System.out.println(customerId + " " + name + " " + age + " " + balance + " " + branchName + " " + bankName);
    }

    void printTransactions() {
        System.out.println("\nTransaction History:");
        for (Transaction t : transactions) {
            t.printTransaction();
        }
    }

    abstract double calculateInterest();
}

// SAVINGS ACCOUNT
class SavingsAccount extends Account {

    SavingsAccount(int id, String name, int age, String branch, double balance) {
        super(id, name, age, branch, balance);
    }

    @Override
    double calculateInterest() {
        return getBalance() * 0.04;
    }
}

// CURRENT ACCOUNT (WITH OVERDRAFT)
class CurrentAccount extends Account {

    private double overdraftLimit;

    CurrentAccount(int id, String name, int age, String branch, double balance, double overdraftLimit) {
        super(id, name, age, branch, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    double calculateInterest() {
        return 0;
    }

    @Override
    void withdraw(double amount) {
        if (amount <= getBalance() + overdraftLimit) {
            updateBalance(getBalance() - amount);
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Overdraft limit exceeded!");
        }
    }
}

// TRANSACTION CLASS
class Transaction {
    static int counter = 1;

    int transactionId;
    double amount;
    String type;
    LocalDateTime dateTime;

    Transaction(double amount, String type) {
        this.transactionId = counter++;
        this.amount = amount;
        this.type = type;
        this.dateTime = LocalDateTime.now();
    }

    void printTransaction() {
        System.out.println("ID: " + transactionId + " | Type: " + type + " | Amount: " + amount + " | Time: " + dateTime);
    }
}

// BANK CLASS
class Bank {
    static int totalAccounts = 0;
    static int idCounter = 1;

    HashMap<Integer, Account> accounts = new HashMap<>();

    void createAccount(int type, String name, int age, String branch, double balance, Scanner sc) {

        int id = idCounter++;
        Account acc;

        if (type == 1) {
            acc = new SavingsAccount(id, name, age, branch, balance);
        } else {
            System.out.print("Enter overdraft limit: ");
            double limit = sc.nextDouble();
            acc = new CurrentAccount(id, name, age, branch, balance, limit);
        }

        accounts.put(id, acc);
        totalAccounts++;

        System.out.println("Account created for " + name + " with ID: " + id);
    }

    Account findAccount(int id) {
        return accounts.get(id);
    }

    void deposit(int id, double amount) {
        Account acc = findAccount(id);
        if (acc != null) acc.deposit(amount);
        else System.out.println("Account not found!");
    }

    void withdraw(int id, double amount) {
        Account acc = findAccount(id);
        if (acc != null) acc.withdraw(amount);
        else System.out.println("Account not found!");
    }

    void transfer(int fromId, int toId, double amount) {
        Account sender = findAccount(fromId);
        Account receiver = findAccount(toId);

        if (sender == null || receiver == null) {
            System.out.println("Account not found");
            return;
        }

        if (fromId == toId) {
            System.out.println("Cannot transfer to same account");
            return;
        }

        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }

        if (sender.getBalance() < amount) {
            System.out.println("Insufficient balance");
            return;
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        System.out.println("Transfer successful");
    }

    void checkBalance(int id) {
        Account acc = findAccount(id);
        if (acc != null) {
            System.out.println("Balance: " + acc.getBalance());
        } else {
            System.out.println("Account not found");
        }
    }

    void showTransactions(int id) {
        Account acc = findAccount(id);
        if (acc != null) acc.printTransactions();
        else System.out.println("Account not found");
    }

    void calculateInterest(int id) {
        Account acc = findAccount(id);
        if (acc != null) {
            System.out.println("Interest: " + acc.calculateInterest());
        } else {
            System.out.println("Account not found");
        }
    }
}

// MAIN CLASS
public class Banking_System {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Show Transactions");
            System.out.println("7. Calculate Interest");
            System.out.println("8. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("1. Savings Account");
                    System.out.println("2. Current Account");
                    int type = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter branch: ");
                    String branch = sc.nextLine();

                    System.out.print("Enter balance: ");
                    double balance = sc.nextDouble();

                    bank.createAccount(type, name, age, branch, balance, sc);
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    bank.deposit(sc.nextInt(), sc.nextDouble());
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    bank.withdraw(sc.nextInt(), sc.nextDouble());
                    break;

                case 4:
                    System.out.print("From ID: ");
                    int from = sc.nextInt();
                    System.out.print("To ID: ");
                    int to = sc.nextInt();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    bank.transfer(from, to, amt);
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    bank.checkBalance(sc.nextInt());
                    break;

                case 6:
                    System.out.print("Enter ID: ");
                    bank.showTransactions(sc.nextInt());
                    break;

                case 7:
                    System.out.print("Enter ID: ");
                    bank.calculateInterest(sc.nextInt());
                    break;

                case 8:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
