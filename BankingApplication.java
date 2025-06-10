import java.io.*;
import java.util.*;

// ===== Base Account Class =====
abstract class Account {
    protected String accountNumber;
    protected String holderName;
    protected double balance;

    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public abstract void withdraw(double amount);
    public abstract void deposit(double amount);
    public abstract double calculateInterest();

    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: ₹" + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String toFileFormat() {
        return accountNumber + "," + holderName + "," + balance + "," + this.getClass().getSimpleName();
    }
}

// ===== Savings Account with Interest and Limit =====
class SavingsAccount extends Account {
    private final double interestRate = 0.04; // 4% annual
    private final double withdrawalLimit = 25000;

    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    public void withdraw(double amount) {
        if (amount <= withdrawalLimit && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount + ". Remaining: ₹" + balance);
        } else {
            System.out.println("Withdrawal failed: Limit exceeded or insufficient funds.");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited ₹" + amount + ". New Balance: ₹" + balance);
    }

    public double calculateInterest() {
        return balance * interestRate;
    }
}

// ===== Current Account with Overdraft =====
class CurrentAccount extends Account {
    private final double overdraftLimit = 10000;

    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance + overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount + ". Remaining: ₹" + balance);
        } else {
            System.out.println("Withdrawal failed: Overdraft limit exceeded.");
        }
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited ₹" + amount + ". New Balance: ₹" + balance);
    }

    public double calculateInterest() {
        return 0; // No interest for current accounts
    }
}

// ===== Account Manager (Handles file I/O and logic) =====
class BankSystem {
    private static final String FILE_NAME = "accounts.txt";
    private List<Account> accounts = new ArrayList<>();

    public BankSystem() {
        loadAccounts();
    }

    private void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String accNum = parts[0];
                String name = parts[1];
                double balance = Double.parseDouble(parts[2]);
                String type = parts[3];

                if (type.equals("SavingsAccount")) {
                    accounts.add(new SavingsAccount(accNum, name, balance));
                } else if (type.equals("CurrentAccount")) {
                    accounts.add(new CurrentAccount(accNum, name, balance));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing account data found.");
        }
    }

    private void saveAccounts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts) {
                bw.write(acc.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving account data.");
        }
    }

    public void createAccount(Scanner sc) {
        System.out.print("Enter Account Number: ");
        String accNum = sc.next();
        System.out.print("Enter Holder Name: ");
        String name = sc.next();
        System.out.print("Enter Initial Deposit: ");
        double amount = sc.nextDouble();

        System.out.println("Select Account Type:\n1. Savings\n2. Current");
        int choice = sc.nextInt();

        Account acc = null;
        if (choice == 1) {
            acc = new SavingsAccount(accNum, name, amount);
        } else if (choice == 2) {
            acc = new CurrentAccount(accNum, name, amount);
        } else {
            System.out.println("Invalid account type.");
            return;
        }

        accounts.add(acc);
        saveAccounts();
        System.out.println("Account Created Successfully!");
    }

    public Account findAccount(String accNum) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNum)) {
                return acc;
            }
        }
        return null;
    }

    public void performTransaction(Scanner sc) {
        System.out.print("Enter Account Number: ");
        String accNum = sc.next();
        Account acc = findAccount(accNum);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("1. Deposit\n2. Withdraw\n3. View Details\n4. Calculate Interest");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter amount to deposit: ");
                acc.deposit(sc.nextDouble());
                break;
            case 2:
                System.out.print("Enter amount to withdraw: ");
                acc.withdraw(sc.nextDouble());
                break;
            case 3:
                acc.displayDetails();
                break;
            case 4:
                System.out.println("Interest: ₹" + acc.calculateInterest());
                break;
            default:
                System.out.println("Invalid choice.");
        }
        saveAccounts();
    }
}

// ===== Main Class =====
public class BankingApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankSystem bank = new BankSystem();

        while (true) {
            System.out.println("\n====== Multi-user Banking System ======");
            System.out.println("1. Create Account");
            System.out.println("2. Perform Transaction");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bank.createAccount(sc);
                    break;
                case 2:
                    bank.performTransaction(sc);
                    break;
                case 3:
                    System.out.println("Thank you for using our banking system.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
