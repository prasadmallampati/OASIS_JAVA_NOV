import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private int userId;
    private int userPin;
    private Account account;

    public User(int userId, int userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.account = new Account(initialBalance);
    }

    public int getUserId() {
        return userId;
    }

    public int getUserPin() {
        return userPin;
    }

    public Account getAccount() {
        return account;
    }
}

class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    public void transfer(double amount, Account recipient) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
        } else {
            System.out.println("Insufficient funds. Transfer failed.");
        }
    }
}

class Transaction {
    private List<String> history;

    public Transaction() {
        this.history = new ArrayList<>();
    }

    public void addTransaction(String transaction) {
        history.add(transaction);
    }

    public void showTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (String transaction : history) {
            System.out.println(transaction);
        }
    }
}

class ATM {
    private User currentUser;
    private Transaction transactionHistory;

    public ATM(User currentUser) {
        this.currentUser = currentUser;
        this.transactionHistory = new Transaction();
    }

    public void showMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    transactionHistory.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    currentUser.getAccount().withdraw(withdrawAmount);
                    transactionHistory.addTransaction("Withdrawal: -$" + withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    currentUser.getAccount().deposit(depositAmount);
                    transactionHistory.addTransaction("Deposit: +$" + depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient's user ID: ");
                    int recipientUserId = scanner.nextInt();
                    User recipient = findUserById(recipientUserId);
                    
                    if (recipient != null) {
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        currentUser.getAccount().transfer(transferAmount, recipient.getAccount());
                        transactionHistory.addTransaction("Transfer to User ID " + recipient.getUserId() + ": -$" + transferAmount);
                    } else {
                        System.out.println("Recipient not found. Transfer failed.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private User findUserById(int userId) {
       
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, 1234, 1000.0));
        userList.add(new User(2, 5678, 500.0));

        for (User user : userList) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
}

  class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, 1234, 1000.0));
        userList.add(new User(2, 5678, 500.0));

        // Prompt for user id and pin
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();

        System.out.print("Enter User PIN: ");
        int userPin = scanner.nextInt();

        // Validate user credentials
        User user = findUser(userList, userId, userPin);

        if (user != null) {
            System.out.println("Login successful!");

            // Display menu and handle user input
            ATM atm = new ATM(user);
            atm.showMenu();
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }
    }

    private static User findUser(List<User> userList, int userId, int userPin) {
        for (User user : userList) {
            if (user.getUserId() == userId && user.getUserPin() == userPin) {
                return user;
            }
        }
        return null;
    }
}
