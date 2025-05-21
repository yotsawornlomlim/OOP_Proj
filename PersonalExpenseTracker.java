import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// Abstract base class
abstract class Transaction {
    protected String description;
    protected double amount;
    protected Date date;

    public Transaction(String description, double amount, Date date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public abstract void showInfo();

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
}

// Expense class
class Expense extends Transaction {
    public Expense(String description, double amount, Date date) {
        super(description, amount, date);
    }

    @Override
    public void showInfo() {
        System.out.println("Expense: " + description + " - Amount: -" + amount + " Date: " + date);
    }
}

// Income class
class Income extends Transaction {
    public Income(String description, double amount, Date date) {
        super(description, amount, date);
    }

    @Override
    public void showInfo() {
        System.out.println("Income: " + description + " + Amount: " + amount + " Date: " + date);
    }
}

// Manager class to hold and process transactions
class TransactionManager {
    private ArrayList<Transaction> transactions;

    public TransactionManager() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void showAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : transactions) {
                t.showInfo();
            }
        }
    }

    public double getTotalIncome() {
        double total = 0;
        for (Transaction t : transactions) {
            if (t instanceof Income) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getTotalExpense() {
        double total = 0;
        for (Transaction t : transactions) {
            if (t instanceof Expense) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }
}

// Main class with interactive menu
public class PersonalExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();
        boolean running = true;

        System.out.println("Welcome to Personal Expense Tracker!");

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Show All Transactions");
            System.out.println("4. Show Totals and Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter income description: ");
                    String incomeDesc = scanner.nextLine();
                    System.out.print("Enter income amount: ");
                    double incomeAmt = Double.parseDouble(scanner.nextLine());
                    manager.addTransaction(new Income(incomeDesc, incomeAmt, new Date()));
                    System.out.println("Income added successfully.");
                    break;

                case 2:
                    System.out.print("Enter expense description: ");
                    String expenseDesc = scanner.nextLine();
                    System.out.print("Enter expense amount: ");
                    double expenseAmt = Double.parseDouble(scanner.nextLine());
                    manager.addTransaction(new Expense(expenseDesc, expenseAmt, new Date()));
                    System.out.println("Expense added successfully.");
                    break;

                case 3:
                    System.out.println("\nAll transactions:");
                    manager.showAllTransactions();
                    break;

                case 4:
                    System.out.println("\nTotal Income: " + manager.getTotalIncome());
                    System.out.println("Total Expense: " + manager.getTotalExpense());
                    System.out.println("Balance: " + manager.getBalance());
                    break;

                case 5:
                    running = false;
                    System.out.println("Thank you for using Personal Expense Tracker!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
