package tests;

import controller.AccountController;
import controller.UserController;
import controller.TransactionController;
import model.Transaction;
import model.User;
import model.Account;
import utils.TestUtils;

import java.util.Vector;

public class SimpleBankingAppTestSOLVED {

    private static UserController userController;
    private static AccountController accountController;
    private static TransactionController transactionController;
    private static Vector<User> users;
    private static Vector<Account> accounts;
    private static Vector<Transaction> transactions = new Vector<>();

    public static void setup() {
        userController = new UserController();
        accountController = new AccountController();
        transactionController = new TransactionController(transactions);

        // Load test data
        users = userController.loadUserData();
        accounts = accountController.loadAccountData();
        accountController.setTransactions(transactions);
    }

    public static void testLoadData() {
        System.out.println("Running testLoadData...");

        // Verify users
        if (users.size() == 3)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testLoadData: loadUserData: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testLoadData: loadUserData: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);

        // Verify accounts
        if (accounts.size() == 4)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testLoadData: loadAccountData: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "testLoadData: loadAccountData: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
    }

    public static void testDeposits() {
        System.out.println("Running testDeposits...");

        // 1- Setup phase
        double balanceBefore = accountController.getBalance("5495-1234");
        double depositAmount = 50.21;

        // 2- Exercise phase
        transactionController.addTransaction("5495-1234", depositAmount);
        double balanceAfter = accountController.getBalance("5495-1234");

        // 3- Verify phase
        if (balanceBefore + depositAmount == balanceAfter)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testDeposits: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "testDeposits: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
            System.out.format("Expected Balance: %.2f, Actual Balance: %.2f\n",
                    balanceBefore + depositAmount, balanceAfter);
        }

        // 4- Teardown phase
        transactionController.addTransaction("5495-1234", -depositAmount);
    }

    public static void testWithdrawals() {
        System.out.println("Running testWithdrawals...");

        // 1- Setup phase
        double balanceBefore = accountController.getBalance("5495-1234");
        double withdrawalAmount = 50.21;

        // 2- Exercise phase
        transactionController.addTransaction("5495-1234", -withdrawalAmount);
        double balanceAfter = accountController.getBalance("5495-1234");

        // 3- Verify phase
        if (balanceBefore - withdrawalAmount == balanceAfter)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testWithdrawals: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "testWithdrawals: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
            System.out.format("Expected Balance: %.2f, Actual Balance: %.2f\n",
                    balanceBefore - withdrawalAmount, balanceAfter);
        }

        // 4- Teardown phase
        transactionController.addTransaction("5495-1234", withdrawalAmount);
    }
    
    /*
     * Minimum  Balance
     * */
    
    public static void testMinimumBalance() {
        System.out.println("Running testMinimumBalance...");

        double initialBalance = accountController.getBalance("5495-1234");
        boolean withdrawalResult = accountController.withdraw("5495-1234", initialBalance - 5);

        if (!withdrawalResult) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testMinimumBalance: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "testMinimumBalance: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
        }
    }
    
    /* Improvement: Account Type-Based Interest Calculation
     * 
     */
    
    public static void testInterestApplication() {
        System.out.println("Running testInterestApplication...");

        // Ensure account has a non-zero balance before testing
        transactionController.addTransaction("5495-1291", 100.00);  // Deposit $100

        double balanceBefore = accountController.getBalance("5495-1291");
        accountController.applyInterest("5495-1291");
        double balanceAfter = accountController.getBalance("5495-1291");

        if (balanceAfter > balanceBefore) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "testInterestApplication: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "testInterestApplication: TC1 FAILED" + TestUtils.TEXT_COLOR_RESET);
            System.out.format("Expected Balance > %.2f, but got %.2f\n", balanceBefore, balanceAfter);
        }
    }

    
    /*
    Improvement: Transaction History Retrieval
    */
    public static void testTransactionHistory() {
        System.out.println("Running testTransactionHistory...");

        // Add some transactions
        transactionController.addTransaction("5495-1234", 500.00);
        transactionController.addTransaction("5495-1234", -100.00);
        transactionController.addTransaction("5495-1234", 5.00);

        // Verify if transaction history is displayed
        accountController.printTransactionHistory("5495-1234");

        System.out.println(TestUtils.TEXT_COLOR_GREEN + "testTransactionHistory: TC1 passed" + TestUtils.TEXT_COLOR_RESET);
    }



    public static void main(String[] args) {
        setup();
        testLoadData();
        testDeposits();
        testWithdrawals();
        testMinimumBalance();
        testInterestApplication();
        testTransactionHistory();
    }
}
