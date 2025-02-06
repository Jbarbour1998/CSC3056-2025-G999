package tests;

import model.User;
import utils.TestUtils;

public class UserTest {

	public static void main(String[] args) {

		testUserConstructor();
	}

	public static void testUserConstructor() {

		String test_username = "mike";
		String test_password = "my_passwd";
		String test_first_name = "Mike";
		String test_last_name = "Smith";
		String test_mobile_number = "07771234567";

		User testUser = new User(test_username, test_password, test_first_name, test_last_name, test_mobile_number);

		String test_case_1 = "TC1-getUsername";
		String test_case_2 = "TC2-getPassword";
		String test_case_3 = "TC3-getFirstName";
		String test_case_4 = "TC4-getLastName";
		String test_case_5 = "TC5-getMobileNumber";

		System.out.println("Starting the assertions of the test method: testUserConstructor");

		// getUsername
		if (testUser.getUsername() == (test_username)) {
			TestUtils.printTestPassed(test_case_1);
		} else {
			TestUtils.printTestFailed(test_case_1);
		}

		// getPassword
		if (testUser.getPassword().equals(test_password)) {
			TestUtils.printTestPassed(test_case_2);
		} else {
			TestUtils.printTestFailed(test_case_2);
		}

		// getFirst_name
		if (testUser.getFirst_name().equals(test_first_name)) {
			TestUtils.printTestPassed(test_case_3);
		} else {
			TestUtils.printTestFailed(test_case_3);
		}

		// getLast_name
		if (testUser.getLast_name().equals(test_last_name)) {
			TestUtils.printTestPassed(test_case_4);
		} else {
			TestUtils.printTestFailed(test_case_4);
		}

		// getMobileNumber
		if (testUser.getMobile_number().equals(test_mobile_number)) {
			TestUtils.printTestPassed(test_case_5);
		} else {
			TestUtils.printTestFailed(test_case_5);
		}

		// using asserts
		assert testUser.getUsername() == test_username;
		// other assertions like one above to verify the four other fields of the class
		assert testUser.getPassword() == test_password;
		assert testUser.getFirst_name() == test_first_name;
		assert testUser.getLast_name() == test_last_name;
		assert testUser.getMobile_number() == test_mobile_number;
		System.out.println("All Java aseertions in the test suite passed (none failed).");
	}
}
