package org.jfree.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {

	private Range rangeObjectUnderTestForConstrain;
	private Range rangeObjectUnderTestForExpand;
	private Range rangeObjectUnderTestForExpandToInclude;

	// Helper methods for ranges used multiple times
	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTestForConstrain = new Range(-3, 3);
		rangeObjectUnderTestForExpand = new Range(1, 5);
		rangeObjectUnderTestForExpandToInclude = new Range(2, 6);
	}

	@After
	public void tearDown() throws Exception {
		rangeObjectUnderTestForConstrain = null;
		rangeObjectUnderTestForExpand = null;
		rangeObjectUnderTestForExpandToInclude = null;
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
		// Value is above the upper bound, should return the upper bound (3)
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
		assertEquals("Constraining Double.MIN_VALUE should return lower bound (-3).", -3.0,
				rangeObjectUnderTestForConstrain.constrain(-Double.MIN_VALUE), 1e-9);
	}

	@Test
	public void testConstrainWithMaximumValue() {
		assertEquals("Constraining Double.MAX_VALUE should return upper bound (3).", 3.0,
				rangeObjectUnderTestForConstrain.constrain(Double.MAX_VALUE), 1e-9);
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
		try {
			Range range = new Range(10, 20);
			Range.expand(range, -1.2, 0.1);
			fail("Expected IllegalArgumentException was not thrown.");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testExpandInvalidUpperMargin() {
		try {
			Range range = new Range(10, 20);
			Range.expand(range, 0.2, -1.6);
			fail("Expected IllegalArgumentException was not thrown.");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testExpandNullRange() {
		try {
			Range.expand(null, 0.25, 0.5);
			fail("Expected IllegalArgumentException was not thrown.");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
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

	//////////////
	// 6. combine
	//////////////

	@Test
	public void testCombineNullRanges() {
		assertNull(Range.combine(null, null)); // not directly in code but useful to reveal unintentional bugs
	}

	@Test
	public void testCombineNullFirstRange() {
		Range range2 = new Range(1, 5);
		assertEquals(range2, Range.combine(null, range2));
	}

	@Test
	public void testCombineNullSecondRange() {
		Range range1 = new Range(1, 5);
		assertEquals(range1, Range.combine(range1, null)); // bug
	}

	@Test
	public void testCombineOverlappingRanges() {
		Range range1 = new Range(1, 5);
		Range range2 = new Range(4, 10);
		Range result = Range.combine(range1, range2);
		assertEquals(new Range(1, 10), result);
	}

	@Test
	public void testCombineNonOverlappingRanges() {
		Range r1 = new Range(2, 5);
		Range r2 = new Range(6, 10);
		Range result = Range.combine(r1, r2);
		assertEquals(new Range(2, 10), result);
	}

	@Test
	public void testCombine_FirstContainsSecond() {
		Range range1 = new Range(1, 10);
		Range range2 = new Range(3, 7);
		Range result = Range.combine(range1, range2);
		assertEquals(new Range(1, 10), result);
	}

	@Test
	public void testCombine_SecondContainsFirst() {
		Range range1 = new Range(3, 7);
		Range range2 = new Range(1, 10);
		Range result = Range.combine(range1, range2);
		assertEquals(new Range(1, 10), result);
	}

	@Test
	public void testCombine_IdenticalRanges() {
		Range range1 = new Range(4, 12);
		Range range2 = new Range(4, 12);
		Range result = Range.combine(range1, range2);
		assertEquals(new Range(4, 12), result);
	}

	@Test
	public void testCombine_ZeroLengthRange() {
		Range range1 = new Range(7, 7);
		Range range2 = new Range(3, 7);
		Range result = Range.combine(range1, range2);
		assertEquals(new Range(3, 7), result);
	}

	@Test
	public void testCombine_NegativeValues() {
		Range range1 = new Range(-3, -2);
		Range range2 = new Range(1, 3);
		Range result = Range.combine(range1, range2);
		assertEquals(new Range(-3, 3), result);
	}

////////////////////////
// 7. expandToInclude()
////////////////////////

	@Test
	public void testExpandToIncludeNullRangeWithPositiveValue() {
		Range result = Range.expandToInclude(null, 5);
		assertEquals(new Range(5, 5), result);
	}

	@Test
	public void testExpandToIncludeNullRangeWithZeroValue() {
		Range result = Range.expandToInclude(null, 0);
		assertEquals(new Range(0, 0), result);
	}

	@Test
	public void testExpandToIncludeNullRangeWithNegativeValue() {
		Range result = Range.expandToInclude(null, -3);
		assertEquals(new Range(-3, -3), result);
	}

	@Test
	public void testExpandToIncludeValidRangeWithValueInside() {
		Range result = Range.expandToInclude(rangeObjectUnderTestForExpandToInclude, 4);
		assertEquals(new Range(2, 6), result);
	}

	@Test
	public void testExpandToIncludeValidRangeWithLowerExpansion() {
		Range result = Range.expandToInclude(rangeObjectUnderTestForExpandToInclude, 1);
		assertEquals(new Range(1, 6), result);
	}

	@Test
	public void testExpandToIncludeValidRangeWithUpperExpansion() {
		Range result = Range.expandToInclude(rangeObjectUnderTestForExpandToInclude, 8);
		assertEquals(new Range(2, 8), result);
	}

	@Test
	public void testExpandToIncludeValidRangeWithValueEqualToLowerBound() {
		Range result = Range.expandToInclude(rangeObjectUnderTestForExpandToInclude, 2);
		assertEquals(new Range(2, 6), result);
	}

	@Test
	public void testExpandToIncludeValidRangeWithValueEqualToUpperBound() {
		Range result = Range.expandToInclude(rangeObjectUnderTestForExpandToInclude, 6);
		assertEquals(new Range(2, 6), result);
	}

	@Test
	public void testExpandToIncludeValueHigherThanUpperBound() {
		Range range = new Range(3, 3);
		Range result = Range.expandToInclude(range, 5);
		assertEquals(new Range(3, 5), result);
	}

	@Test
	public void testExpandToIncludeValueLowerThanLowerBound() {
		Range range = new Range(3, 3);
		Range result = Range.expandToInclude(range, 1);
		assertEquals(new Range(1, 3), result);
	}

////////////////////////
//8. shift()
////////////////////////
	@Test
	public void testShiftValidRangeWithPositiveDelta() {
		Range range = new Range(3, 6);
		Range result = Range.shift(range, 3);
		assertEquals(new Range(6, 9), result);
	}

	@Test
	public void testShiftValidRangeWithNegativeDelta() {
		Range range = new Range(3, 6);
		Range result = Range.shift(range, -2);
		assertEquals(new Range(1, 4), result);
	}

	@Test
	public void testShiftValidRangeWithZeroDelta() {
		Range range = new Range(3, 6);
		Range result = Range.shift(range, 0);
		assertEquals(new Range(3, 6), result);
	}

	@Test
	public void testShiftValidSameRangeWithPositiveDelta() {
		Range range = new Range(7, 7);
		Range result = Range.shift(range, 2);
		assertEquals(new Range(9, 9), result);
	}

	@Test
	public void testShiftValidSameRangeWithNegativeDelta() {
		Range range = new Range(7, 7);
		Range result = Range.shift(range, -3);
		assertEquals(new Range(4, 4), result);
	}

	@Test
	public void testShiftZeroRangeWithPositiveDelta() {
		Range range = new Range(0, 0);
		Range result = Range.shift(range, 5);
		assertEquals(new Range(5, 5), result);
	}

	@Test
	public void testShiftZeroRangeWithNegativeDelta() {
		Range range = new Range(0, 0);
		Range result = Range.shift(range, -3);
		assertEquals(new Range(-3, -3), result);
	}

	@Test
	public void testShiftValidRangeCrossingZeroWithoutAllowingZeroCrossing() {
		Range range = new Range(-5, 5);
		Range result = Range.shift(range, 10);
		assertEquals(new Range(0, 15), result); // Should not cross zero
	}

	@Test
	public void testShiftValidRangeWithLargePositiveDelta() {
		Range range = new Range(10, 20);
		Range result = Range.shift(range, 100);
		assertEquals(new Range(110, 120), result);
	}

	@Test
	public void testShiftValidRangeWithLargeNegativeDelta() {
		Range range = new Range(10, 20);
		Range result = Range.shift(range, -50);
		assertEquals(new Range(0, 0), result);
	}

	//////////////////////////
	// 9. getCentralValue()
	//////////////////////////

	@Test
	public void testCentralValueValidPositiveRange() {
		Range range = new Range(2, 10);
		assertEquals(6, range.getCentralValue(), 0.0001);
	}

	@Test
	public void testCentralValueValidNegativeRange() {
		Range range = new Range(-8, -4);
		assertEquals(-6, range.getCentralValue(), 0.0001);
	}

	@Test
	public void testGetCentralValueWithRangeContainingZero() {
		Range range = new Range(-5, 5);
		double result = range.getCentralValue();
		assertEquals(0.0, result, 0.0001);
	}

	@Test
	public void testGetCentralValueWithSameBoundsRange() {
		Range range = new Range(5, 5);
		double result = range.getCentralValue();
		assertEquals(5.0, result, 0.0001);
	}

	@Test
	public void testGetCentralValueWithLargeRange() {
		Range range = new Range(1000, 5000);
		double result = range.getCentralValue();
		assertEquals(3000.0, result, 0.0001);
	}

	@Test
	public void testGetCentralValueWithInvalidRange() {
		try {
			Range range = new Range(5, 2);
			range.getCentralValue();
			fail("Expected IllegalArgumentException was not thrown.");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

}
