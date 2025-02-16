package tests;

import java.util.Date;

import model.Transaction;
import utils.TestUtils;

public class TransactionTest {

	public static void main(String[] args) {

		testTransactionConstructor();
	}

	public static void testTransactionConstructor() {

		String test_account_number = "1234";
		double test_transaction_amount = 200.00;
		Date test_transaction_date = TestUtils.parseDate("2025-01-30");

		Transaction testTransaction = new Transaction(test_account_number, test_transaction_amount,
				test_transaction_date);

		String test_case_1 = "TC1-getAccountNumber";
		String test_case_2 = "TC2-getTransactionAmount";
		String test_case_3 = "TC3-getTransactionDate";

		System.out.println("Starting the assertions of the test method: testTransactionConstructor");

		// getAccountNumber
		if (testTransaction.getAccount_number().equals(test_account_number)) {
			TestUtils.printTestPassed(test_case_1);
		} else {
			TestUtils.printTestFailed(test_case_1);
		}

		// getTransactionAmount
		if (testTransaction.getTransaction_amount() == (test_transaction_amount)) {
			TestUtils.printTestPassed(test_case_2);
		} else {
			TestUtils.printTestFailed(test_case_2);
		}

		// getTransactionDate
		if (testTransaction.getTransaction_date().equals(test_transaction_date)) {
			TestUtils.printTestPassed(test_case_3);
		} else {
			TestUtils.printTestFailed(test_case_3);
		}

		// using asserts
		assert testTransaction.getAccount_number().equals(test_account_number);
		assert testTransaction.getTransaction_amount() == (test_transaction_amount);
		assert testTransaction.getTransaction_date().equals(test_transaction_date);
		System.out.println("All Java assertions in the test suite passed (none failed).");
	}
}
