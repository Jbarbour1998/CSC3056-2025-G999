package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {

	private Range rangeObjectUnderTestForConstrain;
	private Range rangeObjectUnderTestForExpand;

	//Helper methods for ranges used multiple times
	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTestForConstrain = new Range(-3, 3);
		rangeObjectUnderTestForExpand = new Range(1, 5);
	}

	@After
	public void tearDown() throws Exception {
		rangeObjectUnderTestForConstrain = null;
		rangeObjectUnderTestForExpand = null;
	}

	// Methods under test

	///////////////
	// 1. constrain()
	//////////////
	@Test
	public void testConstrainValueWithinRange() {
		// Value is within the range, should return the input value
		assertEquals("The value within the range should be returned unchanged.", 2.0,
				rangeObjectUnderTestForConstrain.constrain(2.0), 0.000000001d);
	}

	@Test
	public void testConstrainValueBelowLowerBound() {
		// Value is below the lower bound, should return the lower bound (-3)
		assertEquals("The value below the range should be constrained to the lower bound.", -3.0,
				rangeObjectUnderTestForConstrain.constrain(-8.0), 0.000000001d);
	}

	@Test
	public void testConstrainValueAboveUpperBound() {
		// Value is above the upper bound, should return the upper bound (5)
		assertEquals("The value above the range should be constrained to the upper bound.", 3.0,
				rangeObjectUnderTestForConstrain.constrain(6.0), 0.000000001d);
	}

	@Test
	public void testConstrainValueEqualToLowerBound() {
		// Value is exactly equal to the lower bound, should return the lower bound
		assertEquals("The value equal to the lower bound should be returned unchanged.", -3.0,
				rangeObjectUnderTestForConstrain.constrain(-3.0), 0.000000001d);
	}

	@Test
	public void testConstrainValueEqualToUpperBound() {
		// Value is exactly equal to the upper bound, should return the upper bound
		assertEquals("The value equal to the upper bound should be returned unchanged.", 3.0,
				rangeObjectUnderTestForConstrain.constrain(3.0), 0.000000001d);
	}

	@Test
	public void testConstrainValueBelowLowerBoundForNegativeRange() {
		// Test case with a range that goes negative, ensuring proper constraining
		Range negativeRange = new Range(-10.0, -2.0);
		assertEquals("The value below the range should be constrained to the lower bound.", -10.0,
				negativeRange.constrain(-20.0), 0.000000001d);
	}

	@Test
	public void testConstrainValueAboveUpperBoundForNegativeRange() {
		// Test case with a range that goes negative, ensuring proper constraining
		Range negativeRange = new Range(-10.0, -2.0);
		assertEquals("The value above the range should be constrained to the upper bound.", -2.0,
				negativeRange.constrain(5.0), 0.000000001d);
	}

	@Test
	public void testConstrainWithMinimumValue() {
	    assertEquals("Constraining Double.MIN_VALUE should return lower bound (-3).",
	            -3.0, rangeObjectUnderTestForConstrain.constrain(Double.MIN_VALUE), 1e-9);
	}

	@Test
	public void testConstrainWithMaximumValue() {
	    assertEquals("Constraining Double.MAX_VALUE should return upper bound (3).",
	            3.0, rangeObjectUnderTestForConstrain.constrain(Double.MAX_VALUE), 1e-9);
	}
	
	@Test
	public void testConstrainWithNaN() {
		Range range = new Range(-5, 5);
		assertTrue("Constraining NaN should return NaN.", Double.isNaN(range.constrain(Double.NaN)));
	}

	/*
	 * Bug for Range.constrain - when constrain value entered is less than lower
	 * bound it returns the central value of the range as the constrained value
	 * instead of the lower range
	 */

///////////////////
	// 2. getUpperBound()
//////////////////

	@Test
	public void testUpperBoundWhenPositive() {

		Range range = new Range(5, 15);
		assertEquals(15, range.getUpperBound(), 0.000000001d);
	}

	@Test
	public void testUpperBoundWhenNegative() {

		Range range = new Range(-25, -10);
		assertEquals(-10, range.getUpperBound(), 0.000000001d);
	}

	@Test
	public void testUpperBoundWhenLargePositive() {

		Range range = new Range(100, 255);
		assertEquals(255, range.getUpperBound(), 0.000000001d);
	}

	@Test
	public void testUpperBoundWhenLargeNegative() {

		Range range = new Range(-175, -145);
		assertEquals(-145, range.getUpperBound(), 0.000000001d);
	}

	@Test
	public void testUpperBoundWhenZero() {

		Range range = new Range(-5, 0);
		assertEquals(0, range.getUpperBound(), 0.000000001d);
	}

	@Test
	public void testUpperBoundWithZeroLengthRange() {

		Range range = new Range(6, 6);
		assertEquals(6, range.getUpperBound(), 0.000000001d);
	}

////////////////////
	// 3. getLowerBound()
///////////////////

	@Test
	public void testLowerBoundWhenPositive() {

		Range range = new Range(1, 10);
		assertEquals(1, range.getLowerBound(), 0.000000001d);
	}

	@Test
	public void testLowerBoundWhenNegative() {

		Range range = new Range(-15, 15);
		assertEquals(-15, range.getLowerBound(), 0.000000001d);
	}

	@Test
	public void testLowerBoundWhenLargePositive() {

		Range range = new Range(100, 155);
		assertEquals(100, range.getLowerBound(), 0.000000001d);
	}

	@Test
	public void testLowerBoundWhenLargeNegative() {

		Range range = new Range(-95, -45);
		assertEquals(-95, range.getLowerBound(), 0.000000001d);
	}

	@Test
	public void testLowerBoundWhenZero() {

		Range range = new Range(0, 10);
		assertEquals(0, range.getLowerBound(), 0.000000001d);
	}

	@Test
	public void testLowerBoundWithZeroLengthRange() {

		Range range = new Range(4, 4);
		assertEquals(4, range.getLowerBound(), 0.000000001d);
	}

////////////////////////
	// 4. expand()
///////////////////////
	@Test
	public void testExpandValidRangeWithPositiveMargins() {

		Range result = Range.expand(rangeObjectUnderTestForExpand, 0.25, 0.5);

		assertEquals(new Range(0, 7), result);
	}

	@Test
	public void testExpandValidRangeWithZeroLowerMarginValidUpperMargin() {

		Range result = Range.expand(rangeObjectUnderTestForExpand, 0, 0.5);

		assertEquals(new Range(1, 7), result);
	}

	@Test
	public void testExpandValidRangeWithValidLowerMarginZeroUpperMargin() {

		Range result = Range.expand(rangeObjectUnderTestForExpand, 0.25, 0);

		assertEquals(new Range(0, 7), result);
	}

	@Test
	public void testExpandZeroRange() {

		Range range = new Range(5, 5);
		Range result = Range.expand(range, 0.25, 0.25);

		assertEquals(new Range(5, 5), result);
	}
	// tests above bug, although it passed, putting any upper bound on the assertion
	// gets it to pass

	@Test
	public void testExpandRangeWithLargeMargins() {
		Range range = new Range(200, 300);
		Range result = Range.expand(range, 0.9, 0.9);

		assertEquals(new Range(110, 390), result);
	}

	@Test
	public void testExpandInvalidLowerMargin() {
		Range range = new Range(10, 20);
		assertThrows(IllegalArgumentException.class, () -> {
			Range.expand(range, -1.2, 0.1);
		});
	}

	@Test
	public void testExpandInvalidUpperMargin() {
		Range range = new Range(10, 20);
		assertThrows(IllegalArgumentException.class, () -> {
			Range.expand(range, 0.2, -1.1);
		});
	}

	@Test
	public void testExpandNullRange() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.expand(null, 0.25, 0.5);
		});
	}

	//////////////////
	// 5. intersects()
	/////////////////

	@Test
	public void testIntersectsRangeEntirelyInsideExistingRange() {
		Range existingRange = new Range(2, 10);
		assertTrue(existingRange.intersects(3, 7));
	}

	@Test
	public void testIntersectsRangePartiallyOverlappingExistingRange() {
		Range existingRange = new Range(2, 10);
		assertTrue(existingRange.intersects(3, 13));
	}

	@Test
	public void testIntersectsRangeOutsideExistingRangeNegatively() {
		Range existingRange = new Range(2, 10);
		assertFalse(existingRange.intersects(-7, -2));
	}

	@Test
	public void testIntersectsRangeOutsideExistingRangePositively() {
		Range existingRange = new Range(2, 10);
		assertFalse(existingRange.intersects(12, 15));
	}

	@Test
	public void testIntersectsRangeTouchesLowerBoundaryOfExistingRange() {
		Range existingRange = new Range(2, 10);
		assertTrue(existingRange.intersects(2, 5));
	}

	@Test
	public void testIntersectsRangeTouchesUpperBoundaryOfExistingRange() {
		Range existingRange = new Range(2, 10);
		assertTrue(existingRange.intersects(5, 10));
	}

	@Test
	public void testIntersectsRangeExactMatchOfExistingRange() {
		Range existingRange = new Range(2, 10);
		assertTrue(existingRange.intersects(2, 10));
	}

	@Test
	public void testIntersectsWithZeroLengthRange() {
		Range existingRange = new Range(5, 5);
		assertTrue(existingRange.intersects(5, 5));
	}

	@Test
	public void testIntersectsInvalidRange() {
		Range existingRange = new Range(2, 10);
		assertFalse(existingRange.intersects(10, 5));
	}
}