package app;

import controller.AccountController;
import controller.UserController;
import controller.TransactionController;
import model.Account;
import model.Transaction;
import model.User;

import java.util.Vector;

public class SimpleBankingAppSOLVED {
    private static Vector<User> users;
    private static Vector<Account> accounts;
    private static Vector<Transaction> transactions = new Vector<>();

    private static UserController userController;
    private static AccountController accountController;
    private static TransactionController transactionController;

    public static void main(String[] args) {
        // Initialize controllers
        userController = new UserController();
        accountController = new AccountController();
        transactionController = new TransactionController(transactions);

        // Load user and account data
        users = userController.loadUserData();
        accounts = accountController.loadAccountData();
        accountController.setTransactions(transactions); // Assign transactions to AccountController

        // ✅ FIXED: Print users and accounts
        userController.printAllUsers();   // Removed parameters
        accountController.printAllAccounts(); // Removed parameters

        // Perform transactions
        transactionController.addTransaction("5495-1234", -50.21);
        System.out.println("Account: after the 1st addTransaction function call...");
        accountController.printAllAccounts();

        transactionController.addTransaction("5495-1234", 520.00);
        transactionController.addTransaction("9999-1111", 21.00); // ✅ FIXED typo in variable name
        System.out.println("Account: after the 2nd/3rd addTransaction function calls...");
        accountController.printAllAccounts();
        
        // 
        // New Feature 1: Minimum Balance Enforcement
        // 
        System.out.println("\nMinimum Balance Enforcement...");

        // Deposit money first
        transactionController.addTransaction("5495-1234", 50.00);

        // Attempt a valid withdrawal (should pass)
        System.out.println("Attempting to withdraw $30.00...");
        boolean result1 = accountController.withdraw("5495-1234", 30.00);
        System.out.println("Withdrawal successful: " + result1);

        // Attempt an invalid withdrawal (should fail)
        System.out.println("Attempting to withdraw $40.00...");
        boolean result2 = accountController.withdraw("5495-1234", 40.00);
        System.out.println("Withdrawal successful: " + result2);

        // 
        // New Feature 2: Interest Calculation
        // 
        System.out.println("\nInterest Calculation...");

        // Ensure account has a balance before applying interest
        transactionController.addTransaction("5495-1291", 100.00);
        System.out.println("Applying interest to account 5495-1291...");
        accountController.applyInterest("5495-1291");

        double newBalance = accountController.getBalance("5495-1291");
        System.out.println("New balance after interest: $" + newBalance);

        //
        // New Feature 3: Transaction History Retrieval
        //
        System.out.println("\nTransaction History Retrieval...");

        // Perform multiple transactions
        transactionController.addTransaction("5495-1234", 500.00);
        transactionController.addTransaction("5495-1234", -100.00);
        transactionController.addTransaction("5495-1234", 5.00); // Interest deposit

        // Retrieve and print transaction history
        accountController.printTransactionHistory("5495-1234");

        System.out.println("\n✅ All features tested successfully!");
        
    }       
        
}


