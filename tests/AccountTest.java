package tests;

import java.util.Date;

import model.Account;
import utils.TestUtils;

public class AccountTest {

	public static void main(String[] args) {
		testAccountConstructor();

	}

	public static void testAccountConstructor() {

		String test_account_number = "1234";
		String test_username_of_account_holder = "Mike";
		String test_account_type = "Standard";
		Date test_account_opening_date = TestUtils.parseDate("2024-01-30");

		Account testAccount = new Account(test_account_number, test_username_of_account_holder, test_account_type,
				test_account_opening_date);

		String test_case_1 = "TC1-getAccountNumber";
		String test_case_2 = "TC2-getUsernameOfAccountHolder";
		String test_case_3 = "TC3-getAccountType";
		String test_case_4 = "TC4-getAccountOpeningDate";

		System.out.println("Starting the assertions of the test method: testAccountConstructor");

		// getAccountNumber
		if (testAccount.getAccount_number().equals(test_account_number)) {
			TestUtils.printTestPassed(test_case_1);
		} else {
			TestUtils.printTestFailed(test_case_1);
		}

		// getUsernameOfAccountHolder
		if (testAccount.getUsername_of_account_holder().equals(test_username_of_account_holder)) {
			TestUtils.printTestPassed(test_case_2);
		} else {
			TestUtils.printTestFailed(test_case_2);
		}

		// getAccountType
		if (testAccount.getAccount_type().equals(test_account_type)) {
			TestUtils.printTestPassed(test_case_3);
		} else {
			TestUtils.printTestFailed(test_case_3);
		}

		// getAccountOpeningDate
		if (testAccount.getAccount_opening_date().equals(test_account_opening_date)) {
			TestUtils.printTestPassed(test_case_4);
		} else {
			TestUtils.printTestFailed(test_case_4);
		}

		// using asserts
		assert testAccount.getAccount_number().equals(test_account_number);
		assert testAccount.getUsername_of_account_holder().equals(test_username_of_account_holder);
		assert testAccount.getAccount_type().equals(test_account_type);
		assert testAccount.getAccount_opening_date().equals(test_account_opening_date);
		System.out.println("All Java aseertions in the test suite passed (none failed).");
	}
}
