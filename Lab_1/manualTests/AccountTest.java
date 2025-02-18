package manualTests;

import java.util.Date;

import model.Account;
import utils.TestUtils;

public class AccountTest {

	public static void main(String[] args) throws Exception {

		// Parse the date string into a Date object
		Date account_opening_date = TestUtils.parseDate("2024-01-30");

		Account testAccount = new Account("1234", "Mike", "Standard", account_opening_date);

		System.out.println(testAccount);
	}
}
