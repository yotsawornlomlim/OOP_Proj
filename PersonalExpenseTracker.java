import java.util.ArrayList;
import java.util.Date;

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

class Expense extends Transaction {
    public Expense(String description, double amount, Date date) {
        super(description, amount, date);
    }

    @Override
    public void showInfo() {
        System.out.println("Expense: " + description + " - Amount: -" + amount + " Date: " + date);
    }
}

class Income extends Transaction {
    public Income(String description, double amount, Date date) {
        super(description, amount, date);
    }

    @Override
    public void showInfo() {
        System.out.println("Income: " + description + " + Amount: " + amount + " Date: " + date);
    }
}

class TransactionManager {
    private ArrayList<Transaction> transactions;

    public TransactionManager() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void showAllTransactions() {
        for (Transaction t : transactions) {
            t.showInfo();
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

public class PersonalExpenseTracker {
    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();

        manager.addTransaction(new Income("Salary", 25000, new Date()));
        manager.addTransaction(new Expense("Lunch", 50, new Date()));
        manager.addTransaction(new Expense("Bus ticket", 20, new Date()));
        manager.addTransaction(new Income("Freelance", 5000, new Date()));
        manager.addTransaction(new Expense("Groceries", 1500, new Date()));
        System.out.println("All transactions:");
        manager.showAllTransactions();

        System.out.println("\nTotal Income: " + manager.getTotalIncome());
        System.out.println("Total Expense: " + manager.getTotalExpense());
        System.out.println("Balance: " + manager.getBalance());
    }
}
