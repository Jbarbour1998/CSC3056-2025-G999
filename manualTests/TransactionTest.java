package manualTests;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Transaction;

public class TransactionTest {

	public static void main(String[] args) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Parse the date string into a Date object
		Date transaction_date = dateFormat.parse("2025-01-30");

		Transaction testTransaction = new Transaction("1234", 200.00, transaction_date);

		System.out.println(testTransaction);
	}
}
