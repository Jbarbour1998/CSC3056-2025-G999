package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

	public static final String TEXT_COLOR_GREEN = "\u001B[32m"; // from
																// https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
	public static final String TEXT_COLOR_RED = "\u001B[31m";
	public static final String TEXT_COLOR_RESET = "\u001B[0m";

	public static void printTestPassed(String testCase) {
		System.out.println(TEXT_COLOR_GREEN + "PASSED -- " + testCase + TEXT_COLOR_RESET);
	}

	public static void printTestFailed(String testCase) {
		System.out.println(TEXT_COLOR_RED + "FAILED -- " + testCase + TEXT_COLOR_RESET);
	}
	
	// Utility method to parse date from string
    public static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println(TEXT_COLOR_RED + "Error parsing date: " + e.getMessage() + TEXT_COLOR_RESET);
            return null; // Return null if parsing fails
        }
    }
}
