package controller;

import model.Account;
import model.Transaction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Date;

public class AccountController {
    private Vector<Account> accounts;
    private Vector<Transaction> transactions;

    public AccountController() {
        this.accounts = new Vector<>();
        this.transactions = new Vector<>();
    }

    /**
     * Loads predefined account data and returns a Vector of accounts.
     *
     * @return A Vector containing account objects.
     */
    public Vector<Account> loadAccountData() {
        Vector<Account> loadedAccounts = new Vector<>();
        try {
            loadedAccounts.add(new Account("5495-1234", "mike", "Standard",
                    new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019")));
            loadedAccounts.add(new Account("5495-1239", "mike", "Standard",
                    new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2020")));
            loadedAccounts.add(new Account("5495-1291", "mike", "Saving",
                    new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2019")));
            loadedAccounts.add(new Account("5495-6789", "David.McDonald@gmail.com", "Saving",
                    new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.accounts = loadedAccounts; // Assign loaded accounts to the instance variable
        return this.accounts;
    }

    /**
     * Prints all accounts and their balances.
     */
    public void printAllAccounts() {
        System.out.println("There are: " + accounts.size() + " accounts in the system.");
        System.out.println(String.format("%-10s| %-30s| %-10s| %-15s| %-15s",
                "Account #", "username_of_account_holder", "type", "opening_date", "Balance"));
        System.out.println("--------------------------------------------------------------------------------");

        for (Account account : accounts) {
            System.out.println(account + "| $" + getBalance(account.getAccount_number()));
        }
        System.out.println();
    }

    /**
     * Calculates and returns the balance for a given account number.
     *
     * @param accountNumber The account number to check balance for.
     * @return The calculated balance.
     */
    public double getBalance(String accountNumber) {
        double balance = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getAccount_number().equals(accountNumber)) {
                balance += transaction.getTransaction_amount();
            }
        }
        return balance;
    }
    
    /*
     * Improvement: withdrawing method as an improvement
     * */
    
    public boolean withdraw(String accountNumber, double amount) {
        double currentBalance = getBalance(accountNumber);
        double minimumBalance = 10.00; // Minimum balance requirement

        if (currentBalance - amount < minimumBalance) {
            System.out.println("Withdrawal denied. Minimum balance requirement not met.");
            return false;
        }

        transactions.add(new Transaction(accountNumber, -amount, new Date()));
        return true;
    }
    
    /*
     * Improvement: Account type-nased interest
     * */
    public void applyInterest(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccount_number().equals(accountNumber)) {
                double balance = getBalance(accountNumber);
                if (balance <= 0) {
                    System.out.println("No interest applied. Balance is zero.");
                    return;
                }

                double rate = account.getAccount_type().equalsIgnoreCase("Saving") ? 0.05 : 0.02; // 5% for savings, 2% for standard
                double newBalance = balance * (1 + rate);
                transactions.add(new Transaction(accountNumber, newBalance - balance, new Date()));
                System.out.println("Interest applied. New balance: $" + newBalance);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    
    /*
    Improvement: Transaction History Retrieval
    */
    public void printTransactionHistory(String accountNumber) {
        System.out.println("Transaction History for Account: " + accountNumber);
        System.out.println("-------------------------------------------------");

        for (Transaction transaction : transactions) {
            if (transaction.getAccount_number().equals(accountNumber)) {
                System.out.println(transaction.getTransaction_date() + " | " + transaction.getTransaction_amount());
            }
        }
    }
    
    


    /**
     * Sets the transactions list for this controller.
     *
     * @param transactions The list of transactions to be managed.
     */
    public void setTransactions(Vector<Transaction> transactions) {
        this.transactions = transactions;
    }
}
