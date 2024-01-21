import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private List<User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        users = new ArrayList<>();
        // Adding a sample user for testing
        users.add(new User("12345", "1234"));
        scanner = new Scanner(System.in);
    }


    public void start() {
        while (true) {
            System.out.println("Enter User ID:");
            String userId = scanner.nextLine();

            System.out.println("Enter User PIN:");
            String pin = scanner.nextLine();

            currentUser = authenticateUser(userId, pin);

            if (currentUser != null) {
                showOptions();
            } else {
                System.out.println("Invalid credentials. Try again.");
            }
        }
    }

    private User authenticateUser(String userId, String pin) {
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getPin().equals(pin)) {
                return user;
            }
        }
        return null;
    }

    private void showOptions() {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    currentUser.showTransactionHistory();
                    break;
                case 2:
                    System.out.println("Enter amount to withdraw:");
                    double amount = Double.parseDouble(scanner.nextLine());
                    currentUser.withdraw(amount);
                    break;
                case 3:
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    currentUser.deposit(depositAmount);
                    break;
                case 4:
                    // Implement Transfer functionality here
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}

class User {
    private String userId;
    private String pin;
    private double balance;
    private List<Transaction> transactions;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Deposit(amount));
        System.out.println("Deposit successful. Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Withdraw(amount));
            System.out.println("Withdrawal successful. Balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void showTransactionHistory() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

abstract class Transaction {
    protected double amount;
    protected String type;

    public Transaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}

class Deposit extends Transaction {
    public Deposit(double amount) {
        super(amount, "Deposit");
    }
}

class Withdraw extends Transaction {
    public Withdraw(double amount) {
        super(amount, "Withdraw");
    }
}
