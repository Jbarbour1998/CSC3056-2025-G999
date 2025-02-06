package manualTests;

import java.util.Date;

import model.Transaction;
import utils.TestUtils;

public class TransactionTest {

	public static void main(String[] args) {

		Date transaction_date = TestUtils.parseDate("2025-01-30");

		Transaction testTransaction = new Transaction("1234", 200.00, transaction_date);

		System.out.println(testTransaction);
	}
}
