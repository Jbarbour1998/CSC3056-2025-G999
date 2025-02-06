package manualTests;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Account;

public class AccountTest {

	public static void main(String[] args) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Parse the date string into a Date object
		Date account_opening_date = dateFormat.parse("2024-01-30");

		Account testAccount = new Account("1234", "Mike", "Standard", account_opening_date);

		System.out.println(testAccount);
	}
}
