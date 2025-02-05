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
		
		User testUser = new User(test_username, test_password, test_first_name, 
				test_last_name, test_mobile_number);
		
		String test_case_1 = "TC1-getUsername";
		String test_case_2 = "TC2-getPassword";
		String test_case_3 = "TC3-getFirstName";
		String test_case_4 = "TC4-getLastName";
		String test_case_5 = "TC5-getMobileNumber";

		System.out.println("Starting the assertions of the test method: testUserConstructor");
		
		if (testUser.getUsername() == (test_username))
			TestUtils.printTestPassed(test_case_1);
		else
			TestUtils.printTestFailed(test_case_1);
		
		if (testUser.getPassword().equals(test_password))
			TestUtils.printTestPassed(test_case_2);
		else
			TestUtils.printTestFailed(test_case_2);
		
		if (testUser.getFirst_name().equals(test_first_name))
			TestUtils.printTestPassed(test_case_3);
		else
			TestUtils.printTestFailed(test_case_3);
		
		if (testUser.getLast_name().equals(test_last_name))
			TestUtils.printTestPassed(test_case_4);
		else
			TestUtils.printTestFailed(test_case_4);
		
		if (testUser.getMobile_number().equals(test_mobile_number))
			TestUtils.printTestPassed(test_case_5);
		else
			TestUtils.printTestFailed(test_case_5);
	}
}
