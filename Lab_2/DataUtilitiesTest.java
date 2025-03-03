package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class DataUtilitiesTest {

    private Values2D values2D;
    private KeyedValues keyedValues;

    @Before
    public void setUp() {
        // Create a more robust test dataset with multiple rows and columns
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        testValues.addValue(1.0, 0, 0);  // row 0, column 0
        testValues.addValue(2.0, 0, 1);  // row 0, column 1
        testValues.addValue(4.0, 1, 0);  // row 1, column 0
        testValues.addValue(5.0, 1, 1);  // row 1, column 1
        values2D = testValues;

        // Create KeyedValues for cumulative percentage tests
        DefaultKeyedValues kv = new DefaultKeyedValues();
        kv.addValue("A", 0.2);
        kv.addValue("B", 0.3);
        kv.addValue("C", 0.5);
        keyedValues = kv;
    }
    
    // Helper method to create an empty Values2D object
    private Values2D createEmptyValues2D() {
        DefaultKeyedValues2D emptyValues = new DefaultKeyedValues2D();
        return emptyValues;
    }

    @After
    public void tearDown() {
        values2D = null;
        keyedValues = null;
    }

    @Test
    public void testCalculateColumnTotalValid() {
        // Ensure our test data is properly set up before testing
        assertNotNull("values2D should not be null", values2D);
        assertTrue("values2D should have at least 2 rows", values2D.getRowCount() >= 2);
        assertTrue("values2D should have at least 1 column", values2D.getColumnCount() >= 1);
        
        // Print the test data for debugging
        System.out.println("Testing calculateColumnTotal with values:");
        for (int i = 0; i < values2D.getRowCount(); i++) {
            System.out.println("Row " + i + ", Col 0: " + values2D.getValue(i, 0));
        }
        
        // Column 0 should sum to 1.0 + 4.0 = 5.0
        double actual = DataUtilities.calculateColumnTotal(values2D, 0);
        System.out.println("Actual Column Sum: " + actual);
        assertEquals("Wrong sum returned for column 0. It should be 5.0", 5.0, actual, 0.0001);
    }

    @Test
    public void testCalculateColumnTotalForSecondColumn() {
        // Column 1 should sum to 2.0 + 5.0 = 7.0
        double actual = DataUtilities.calculateColumnTotal(values2D, 1);
        System.out.println("Actual Column 1 Sum: " + actual);
        assertEquals("Wrong sum returned for column 1. It should be 7.0", 7.0, actual, 0.0001);
    }
    
    @Test
    public void testCalculateColumnTotalWithNoRows() {
        // Test case 2 from plan: Valid Values2D with no rows
        Values2D emptyData = createEmptyValues2D();
        double actual = DataUtilities.calculateColumnTotal(emptyData, 0);
        System.out.println("Empty data column sum: " + actual);
        assertEquals("Sum for column with no rows should be 0.0", 0.0, actual, 0.0001);
    }
    
    @Test
    public void testCalculateColumnTotalWithNegativeIndex() {
        // Test case 3 from plan: Column index out of bounds (-1)
        try {
            DataUtilities.calculateColumnTotal(values2D, -1);
            fail("Expected IndexOutOfBoundsException or similar for negative column index");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            // Either exception is acceptable
            assertTrue(true);
        }
    }
    
    @Test
    public void testCalculateColumnTotalWithTooHighIndex() {
        // Test case 4 from plan: Column index too high
        int tooHighIndex = values2D.getColumnCount() + 5; // Definitely too high
        try {
            DataUtilities.calculateColumnTotal(values2D, tooHighIndex);
            fail("Expected IndexOutOfBoundsException or similar for too high column index");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            // Either exception is acceptable
            assertTrue(true);
        }
    }

    @Test
    public void testCalculateColumnTotalNull() {
        try {
            DataUtilities.calculateColumnTotal(null, 0);
            fail("Expected IllegalArgumentException to be thrown for null data");
        } catch (IllegalArgumentException e) {
            // Expected exception was thrown
            assertTrue(true);
        } catch (NullPointerException e) {
            // If implementation is throwing NPE instead of IllegalArgumentException
            fail("Method should throw IllegalArgumentException for null data, not NullPointerException");
        }
    }

    @Test
    public void testCalculateRowTotalValid() {
        // Ensure our test data is properly set up before testing
        assertNotNull("values2D should not be null", values2D);
        assertTrue("values2D should have at least 1 row", values2D.getRowCount() >= 1);
        assertTrue("values2D should have at least 2 columns", values2D.getColumnCount() >= 2);
        
        // Print the test data for debugging
        System.out.println("Testing calculateRowTotal with values:");
        for (int j = 0; j < values2D.getColumnCount(); j++) {
            System.out.println("Row 0, Col " + j + ": " + values2D.getValue(0, j));
        }
        
        // Row 0 should sum to 1.0 + 2.0 = 3.0
        double actual = DataUtilities.calculateRowTotal(values2D, 0);
        System.out.println("Actual Row Sum: " + actual);
        assertEquals("Wrong sum returned for row 0. It should be 3.0", 3.0, actual, 0.0001);
    }

    @Test
    public void testCalculateRowTotalForSecondRow() {
        // Row 1 should sum to 4.0 + 5.0 = 9.0
        double actual = DataUtilities.calculateRowTotal(values2D, 1);
        System.out.println("Actual Row 1 Sum: " + actual);
        assertEquals("Wrong sum returned for row 1. It should be 9.0", 9.0, actual, 0.0001);
    }
    
    @Test
    public void testCalculateRowTotalWithNoColumns() {
        // Test case 2 from plan: Valid Values2D with no columns
        Values2D emptyData = createEmptyValues2D();
        double actual = DataUtilities.calculateRowTotal(emptyData, 0);
        System.out.println("Empty data row sum: " + actual);
        assertEquals("Sum for row with no columns should be 0.0", 0.0, actual, 0.0001);
    }
    
    @Test
    public void testCalculateRowTotalWithNegativeIndex() {
        // Test case 3 from plan: Row index out of bounds (-1)
        try {
            DataUtilities.calculateRowTotal(values2D, -1);
            fail("Expected IndexOutOfBoundsException or similar for negative row index");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            // Either exception is acceptable
            assertTrue(true);
        }
    }
    
    @Test
    public void testCalculateRowTotalWithTooHighIndex() {
        // Test case 4 from plan: Row index too high
        int tooHighIndex = values2D.getRowCount() + 5; // Definitely too high
        try {
            DataUtilities.calculateRowTotal(values2D, tooHighIndex);
            fail("Expected IndexOutOfBoundsException or similar for too high row index");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            // Either exception is acceptable
            assertTrue(true);
        }
    }

    @Test
    public void testCalculateRowTotalNull() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("Expected IllegalArgumentException to be thrown for null data");
        } catch (IllegalArgumentException e) {
            // Expected exception was thrown
            assertTrue(true);
        } catch (NullPointerException e) {
            // If implementation is throwing NPE instead of IllegalArgumentException
            fail("Method should throw IllegalArgumentException for null data, not NullPointerException");
        }
    }

    @Test
    public void testCreateNumberArrayValid() {
        double[] data = {1.1, 2.2, 3.3};
        Number[] result = DataUtilities.createNumberArray(data);
        System.out.println("Actual Array: " + Arrays.toString(result));
        
        // Test array length
        assertEquals("Incorrect array length", 3, result.length);
        
        // Check each element individually with null checks
        assertNotNull("Element at index 0 should not be null", result[0]);
        assertNotNull("Element at index 1 should not be null", result[1]);
        assertNotNull("Element at index 2 should not be null", result[2]);
        
        // Only test values if elements are not null
        if (result[0] != null) {
            assertEquals("Incorrect value at index 0", 1.1, result[0].doubleValue(), 0.0001);
        }
        if (result[1] != null) {
            assertEquals("Incorrect value at index 1", 2.2, result[1].doubleValue(), 0.0001);
        }
        if (result[2] != null) {
            assertEquals("Incorrect value at index 2", 3.3, result[2].doubleValue(), 0.0001);
        }
    }

    @Test
    public void testCreateNumberArrayNull() {
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected IllegalArgumentException to be thrown for null data");
        } catch (IllegalArgumentException e) {
            // Expected exception was thrown
            assertTrue(true);
        } catch (NullPointerException e) {
            // If implementation is throwing NPE instead of IllegalArgumentException
            fail("Method should throw IllegalArgumentException for null data, not NullPointerException");
        }
    }

    @Test
    public void testCreateNumberArray2DValid() {
        // Test case 1 from plan: Valid 2D array with multiple rows and columns
        double[][] data = {{1.1, 2.2}, {3.3, 4.4}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        System.out.println("Actual 2D Array: " + Arrays.deepToString(result));
        
        // Test array dimensions
        assertEquals("Incorrect row length", 2, result.length);
        assertNotNull("First row should not be null", result[0]);
        assertNotNull("Second row should not be null", result[1]);
        
        if (result[0] != null) {
            assertEquals("Incorrect column length", 2, result[0].length);
            
            // Check elements with null checks
            assertNotNull("Element at [0][0] should not be null", result[0][0]);
            assertNotNull("Element at [0][1] should not be null", result[0][1]);
            
            if (result[0][0] != null) {
                assertEquals("Incorrect value at [0][0]", 1.1, result[0][0].doubleValue(), 0.0001);
            }
            if (result[0][1] != null) {
                assertEquals("Incorrect value at [0][1]", 2.2, result[0][1].doubleValue(), 0.0001);
            }
        }
        
        if (result[1] != null) {
            assertEquals("Incorrect column length for second row", 2, result[1].length);
            
            // Check elements with null checks
            assertNotNull("Element at [1][0] should not be null", result[1][0]);
            assertNotNull("Element at [1][1] should not be null", result[1][1]);
            
            if (result[1][0] != null) {
                assertEquals("Incorrect value at [1][0]", 3.3, result[1][0].doubleValue(), 0.0001);
            }
            if (result[1][1] != null) {
                assertEquals("Incorrect value at [1][1]", 4.4, result[1][1].doubleValue(), 0.0001);
            }
        }
    }
    
    @Test
    public void testCreateNumberArray2DEmpty() {
        // Test case 2 from plan: Empty 2D array
        double[][] emptyData = {};
        Number[][] result = DataUtilities.createNumberArray2D(emptyData);
        
        assertNotNull("Result should not be null for empty 2D array", result);
        assertEquals("Result should be empty 2D array", 0, result.length);
    }
    
    @Test
    public void testCreateNumberArray2DWithNegativeValues() {
        // Test case 3 from plan: 2D array with negative numbers
        double[][] negativeData = {{-1.1, -2.2}, {-3.3, -4.4}};
        Number[][] result = DataUtilities.createNumberArray2D(negativeData);
        
        assertEquals("Incorrect row length", 2, result.length);
        assertNotNull("First row should not be null", result[0]);
        
        if (result[0] != null) {
            assertEquals("Incorrect column length", 2, result[0].length);
            assertNotNull("Element at [0][0] should not be null", result[0][0]);
            
            if (result[0][0] != null) {
                assertEquals("Incorrect value at [0][0]", -1.1, result[0][0].doubleValue(), 0.0001);
            }
        }
    }

    @Test
    public void testCreateNumberArray2DNull() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected IllegalArgumentException to be thrown for null data");
        } catch (IllegalArgumentException e) {
            // Expected exception was thrown
            assertTrue(true);
        } catch (NullPointerException e) {
            // If implementation is throwing NPE instead of IllegalArgumentException
            fail("Method should throw IllegalArgumentException for null data, not NullPointerException");
        }
    }

    @Test
    public void testGetCumulativePercentagesValid() {
        // Ensure our test data is properly set up
        assertNotNull("keyedValues should not be null", keyedValues);
        assertEquals("keyedValues should have 3 items", 3, keyedValues.getItemCount());
        
        // Print input values for debugging
        System.out.println("Testing getCumulativePercentages with values:");
        for (int i = 0; i < keyedValues.getItemCount(); i++) {
            System.out.println(keyedValues.getKey(i) + " -> " + keyedValues.getValue(i));
        }
        
        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);
        assertNotNull("Result should not be null", result);
        
        // Print output values for debugging
        System.out.println("Cumulative Percentages: ");
        for (int i = 0; i < result.getItemCount(); i++) {
            System.out.println(result.getKey(i) + " -> " + result.getValue(i));
        }
        
        // Test cumulative percentages with null checks
        assertNotNull("Value for key A should not be null", result.getValue("A"));
        assertNotNull("Value for key B should not be null", result.getValue("B"));
        assertNotNull("Value for key C should not be null", result.getValue("C"));
        
        if (result.getValue("A") != null) {
            assertEquals("Incorrect cumulative percentage for A", 0.2, result.getValue("A").doubleValue(), 0.0001);
        }
        if (result.getValue("B") != null) {
            assertEquals("Incorrect cumulative percentage for B", 0.5, result.getValue("B").doubleValue(), 0.0001);
        }
        if (result.getValue("C") != null) {
            assertEquals("Incorrect cumulative percentage for C", 1.0, result.getValue("C").doubleValue(), 0.0001);
        }
    }

    @Test
    public void testGetCumulativePercentagesNull() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("Expected IllegalArgumentException to be thrown for null data");
        } catch (IllegalArgumentException e) {
            // Expected exception was thrown
            assertTrue(true);
        } catch (NullPointerException e) {
            // If implementation is throwing NPE instead of IllegalArgumentException
            fail("Method should throw IllegalArgumentException for null data, not NullPointerException");
        }
    }
    
    @Test
    public void testGetCumulativePercentagesWithEmptyData() {
        DefaultKeyedValues emptyValues = new DefaultKeyedValues();
        KeyedValues result = DataUtilities.getCumulativePercentages(emptyValues);
        assertEquals("Empty data should result in empty result", 0, result.getItemCount());
    }
    
    @Test
    public void testGetCumulativePercentagesWithZeroSum() {
        DefaultKeyedValues zeroValues = new DefaultKeyedValues();
        zeroValues.addValue("A", 0.0);
        zeroValues.addValue("B", 0.0);
        
        System.out.println("Testing getCumulativePercentages with zero values:");
        KeyedValues result = DataUtilities.getCumulativePercentages(zeroValues);
        
        // The implementation should handle division by zero appropriately
        for (int i = 0; i < result.getItemCount(); i++) {
            System.out.println(result.getKey(i) + " -> " + result.getValue(i));
            // Note: Implementations may differ on how to handle this case
            // Some might return NaN, others might return 0 or 1
            assertNotNull("Result should not be null for zero sum", result.getValue(i));
        }
    }
    
    @Test
    public void testGetCumulativePercentagesWithNegativeValues() {
        // Test case 3 from plan: KeyedValues with negative values (edge case)
        DefaultKeyedValues negativeValues = new DefaultKeyedValues();
        negativeValues.addValue("A", -2.0);
        negativeValues.addValue("B", -3.0);
        negativeValues.addValue("C", -5.0);
        
        System.out.println("Testing getCumulativePercentages with negative values:");
        KeyedValues result = DataUtilities.getCumulativePercentages(negativeValues);
        
        assertNotNull("Result should not be null", result);
        assertEquals("Result should have same number of items", 3, result.getItemCount());
        
        // The sum is -10, so cumulative percentages should be:
        // A: -2/-10 = 0.2
        // B: (-2+-3)/-10 = 0.5
        // C: (-2+-3+-5)/-10 = 1.0
        if (result.getValue("A") != null) {
            assertEquals("Incorrect cumulative percentage for A with negative values", 
                        0.2, result.getValue("A").doubleValue(), 0.0001);
        }
        if (result.getValue("B") != null) {
            assertEquals("Incorrect cumulative percentage for B with negative values", 
                        0.5, result.getValue("B").doubleValue(), 0.0001);
        }
        if (result.getValue("C") != null) {
            assertEquals("Incorrect cumulative percentage for C with negative values", 
                        1.0, result.getValue("C").doubleValue(), 0.0001);
        }
    }
}
