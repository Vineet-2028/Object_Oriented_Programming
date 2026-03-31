import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

abstract class Lib_Card {
    static String library_name = "Vineet Pustakalya";

    private int id;
    private String name;
    private int age;
    private String gender;
    private String role;
    private String address;

    ArrayList<Transaction> transactions;

    Lib_Card(int id, String name, int age, String gender, String role, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.address = address;
        this.transactions = new ArrayList<>();
    }

    int getId() { return id; }
    String getName() { return name; }

    void printTransactions() {
        for (Transaction t : transactions) {
            t.printTransaction();
        }
    }

    abstract double calculateFine();
}

class Transaction {
    static int counter = 1;

    int transactionId;
    String type;
    int bookId;
    String bookName;

    LocalDateTime issueDate;
    LocalDateTime returnDate;
    boolean isReturned;

    Transaction(int bookId, String bookName) {
        this.transactionId = counter++;
        this.type = "ISSUE";
        this.bookId = bookId;
        this.bookName = bookName;
        this.issueDate = LocalDateTime.now();
        this.isReturned = false;
    }

    void markReturned() {
        this.returnDate = LocalDateTime.now();
        this.isReturned = true;
        this.type = "RETURN";
    }

    void printTransaction() {
        System.out.println(transactionId + " | " + type + " | " + bookName +
                " | Issue: " + issueDate + " | Return: " + returnDate);
    }
}

class Books {
    int bookId;
    String bookName;
    String author;
    int units;

    Books(int id, String bookName, String author, int units) {
        this.bookId = id;
        this.bookName = bookName;
        this.author = author;
        this.units = units;
    }

    boolean isAvailable() {
        return units > 0;
    }
}

class Student extends Lib_Card {

    Student(int id, String name, int age, String gender, String role, String address) {
        super(id, name, age, gender, role, address);
    }

    @Override
    double calculateFine() {
        double fine = 0;

        for (Transaction t : transactions) {
            if (t.isReturned) {
                long days = ChronoUnit.DAYS.between(t.issueDate, t.returnDate);
                if (days > 7) {
                    fine += (days - 7) * 5;
                }
            }
        }
        return fine;
    }
}

class Worker extends Lib_Card {

    Worker(int id, String name, int age, String gender, String role, String address) {
        super(id, name, age, gender, role, address);
    }

    @Override
    double calculateFine() {
        double fine = 0;

        for (Transaction t : transactions) {
            if (t.isReturned) {
                long days = ChronoUnit.DAYS.between(t.issueDate, t.returnDate);
                if (days > 10) {
                    fine += (days - 10) * 2;
                }
            }
        }
        return fine;
    }
}

class Library {

    static int idCounter = 1;
    static int bookIdCounter = 1;

    HashMap<Integer, Lib_Card> users = new HashMap<>();
    HashMap<Integer, Books> books = new HashMap<>();

    void createLibCard(int type, String name, int age, String gender, String role, String address) {
        int id = idCounter++;
        Lib_Card user;

        if (type == 1)
            user = new Worker(id, name, age, gender, role, address);
        else
            user = new Student(id, name, age, gender, role, address);

        users.put(id, user);
        System.out.println("User created with ID: " + id);
    }

    void addBook(String name, String author, int units) {
        int id = bookIdCounter++;
        books.put(id, new Books(id, name, author, units));
        System.out.println("Book added with ID: " + id);
    }

    Lib_Card findUser(int id) {
        return users.get(id);
    }

    Books findBook(int id) {
        return books.get(id);
    }

    void issueBook(int userId, int bookId) {
        Lib_Card user = findUser(userId);
        Books book = findBook(bookId);

        if (user == null || book == null) {
            System.out.println("Invalid user/book");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book not available");
            return;
        }

        book.units--;

        Transaction t = new Transaction(bookId, book.bookName);
        user.transactions.add(t);

        System.out.println("Book issued");
    }

    void returnBook(int userId, int bookId) {
        Lib_Card user = findUser(userId);
        Books book = findBook(bookId);

        if (user == null || book == null) {
            System.out.println("Invalid user/book");
            return;
        }

        for (Transaction t : user.transactions) {
            if (t.bookId == bookId && !t.isReturned) {
                t.markReturned();
                book.units++;
                System.out.println("Book returned");
                return;
            }
        }

        System.out.println("No such issued book found");
    }

    void showHistory(int userId) {
        Lib_Card user = findUser(userId);
        if (user != null) user.printTransactions();
        else System.out.println("User not found");
    }

    void calculateFine(int userId) {
        Lib_Card user = findUser(userId);
        if (user != null)
            System.out.println("Fine: " + user.calculateFine());
        else
            System.out.println("User not found");
    }
}

public class Library_Management_System {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        while (true) {
            try {
                System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
                System.out.println("1. Create User");
                System.out.println("2. Add Book");
                System.out.println("3. Issue Book");
                System.out.println("4. Return Book");
                System.out.println("5. Show Transaction History");
                System.out.println("6. Calculate Fine");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.println("\nSelect User Type:");
                        System.out.println("1. Worker");
                        System.out.println("2. Student");
                        System.out.print("Enter type: ");

                        int type = sc.nextInt();
                        sc.nextLine(); 

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Gender: ");
                        String gender = sc.nextLine();

                        System.out.print("Enter Role: ");
                        String role = sc.nextLine();

                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();

                        lib.createLibCard(type, name, age, gender, role, address);
                        break;

                    case 2:
                        sc.nextLine();

                        System.out.print("Enter Book Name: ");
                        String bookName = sc.nextLine();

                        System.out.print("Enter Author: ");
                        String author = sc.nextLine();

                        System.out.print("Enter Number of Units: ");
                        int units = sc.nextInt();

                        lib.addBook(bookName, author, units);
                        break;

                    case 3:
                        sc.nextLine();
                        System.out.print("Enter User ID: ");
                        int userIdIssue = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Book ID: ");
                        int bookIdIssue = sc.nextInt();
                        sc.nextLine();

                        lib.issueBook(userIdIssue, bookIdIssue);
                        break;

                    case 4:
                        System.out.print("Enter User ID: ");
                        int userIdReturn = sc.nextInt();

                        System.out.print("Enter Book ID: ");
                        int bookIdReturn = sc.nextInt();

                        lib.returnBook(userIdReturn, bookIdReturn);
                        break;

                    case 5:
                        System.out.print("Enter User ID: ");
                        int userIdHistory = sc.nextInt();

                        lib.showHistory(userIdHistory);
                        break;

                    case 6:
                        System.out.print("Enter User ID: ");
                        int userIdFine = sc.nextInt();

                        lib.calculateFine(userIdFine);
                        break;

                    case 7:
                        System.out.println("\nThank you for using the system ");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println(" Invalid input! Please enter correct data type.");
                sc.nextLine(); 
            } catch (Exception e) {
                System.out.println(" Unexpected error: " + e.getMessage());
            }
        }
    }
}
