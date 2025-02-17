package controller;

import model.Transaction;
import java.util.Calendar;
import java.util.Vector;

public class TransactionController {
    private Vector<Transaction> transactions;

    public TransactionController(Vector<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(String accountNumber, double amount) {
        Transaction transaction = new Transaction(accountNumber, amount, Calendar.getInstance().getTime());
        transactions.add(transaction);
    }
}
