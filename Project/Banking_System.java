import java.util.*;
import java.time.LocalDateTime;

class Account{
    static String bankName = "SBI";
    
    private int customerId = 0;
    private String name;
    private int age = 0;
    private String branchName;
    private double balance = 0;
    ArrayList<Transaction> transactions;
    
    Account(int customerId, String name, int age, String branchName, double balance) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.branchName = branchName;
        this.balance = Math.max(balance, 0);
        this.transactions = new ArrayList<>();
    }
    
    void deposit(double amount){
        if(amount > 0){
            balance += amount;
            Transaction t = new Transaction(amount, "DEPOSIT");
            transactions.add(t);
            System.out.println("Deposited: " + amount);
        }
        else{
            System.out.println("Enter the valid amount");
            return;
        }
    }
    
    void withdraw(double amount) {
        if(amount <= balance) {
            balance -= amount;
            Transaction t = new Transaction(amount, "WITHDRAW");
            transactions.add(t);

        System.out.println("Withdrawn: " + amount);
        }
        else{
            System.out.println("Insufficient Balance");
            return;
        }
    }

    int getCustomerId(){
        return customerId;
    }
    
    String getName() {
        return name;
    }

    int getAge(){
        return age;
    }

    double getBalance() {
        return balance;
    }

    String getBranchName(){
        return branchName;
    }
    
    void display(){
        System.out.println(customerId + " " + name + " " + age + " " + balance + " " +  branchName + " " + bankName);
    }
    
    void printTransactions() {
        System.out.println("\nTransaction History:");
        for (Transaction t : transactions) {
            t.printTransaction();
        }
    }
}

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

class Bank {
    static int totalAccounts = 0;
    static int idCounter = 1;

    HashMap<Integer, Account> accounts = new HashMap<>();

    void createAccount(String name, int age, String branch, double balance) {
        int id = idCounter;
        idCounter++;
        Account acc = new Account(id, name, age, branch, balance);
        accounts.put(id, acc);
        totalAccounts++;
        System.out.println("Account created for " + name + " with ID: " + id);
    }

    void deposit(int id, double amount){
        Account acc = findAccount(id);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    void withdraw(int id, double amount) {
        Account acc = findAccount(id);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    Account findAccount(int id) {
        return accounts.get(id);
    }

    void transfer(int fromId, int toId, double amount){
        Account sender = findAccount(fromId);
        Account reciever = findAccount(toId);
        if(sender == null || reciever == null){
            System.out.println("Account not found");
            return;
        }
        if(fromId == toId){
            System.out.println("Cannot transfer to same account");
            return;
        }
        if(amount <= 0){
            System.out.println("Invalid Amount");
            return;
        }
        if(sender.getBalance() < amount){
            System.out.println("Insufficient Balance");
            return;
        }
        sender.withdraw(amount);
        reciever.deposit(amount);
        System.out.println("Transfer successful");
    }
}

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
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    sc.nextLine(); // consume newline
                    String name = sc.nextLine();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();

                    System.out.print("Enter branch: ");
                    sc.nextLine();
                    String branch = sc.nextLine();

                    System.out.print("Enter initial balance: ");
                    double balance = sc.nextDouble();

                    bank.createAccount(name, age, branch, balance);
                    break;

                case 2:
                    System.out.print("Enter account ID: ");
                    int idDeposit = sc.nextInt();

                    System.out.print("Enter amount: ");
                    double depAmount = sc.nextDouble();

                    bank.deposit(idDeposit, depAmount);
                    break;

                case 3:
                    System.out.print("Enter account ID: ");
                    int idWithdraw = sc.nextInt();

                    System.out.print("Enter amount: ");
                    double withAmount = sc.nextDouble();

                    bank.withdraw(idWithdraw, withAmount);
                    break;

                case 4:
                    System.out.print("Enter sender ID: ");
                    int fromId = sc.nextInt();

                    System.out.print("Enter receiver ID: ");
                    int toId = sc.nextInt();

                    System.out.print("Enter amount: ");
                    double transAmount = sc.nextDouble();

                    bank.transfer(fromId, toId, transAmount);
                    break;

                case 5:
                    System.out.print("Enter account ID: ");
                    int idBal = sc.nextInt();

                    Account acc = bank.findAccount(idBal);
                    if (acc != null) {
                        System.out.println("Balance: " + acc.getBalance());
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                case 6:
                    System.out.print("Enter account ID: ");
                    int idTrans = sc.nextInt();

                    Account acc2 = bank.findAccount(idTrans);
                    if (acc2 != null) {
                        acc2.printTransactions();
                    } else {
                        System.out.println("Account not found");
                    }
                    break;

                case 7:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
