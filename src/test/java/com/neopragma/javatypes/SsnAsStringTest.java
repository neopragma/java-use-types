package com.neopragma.javatypes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test cases to exercise an implementation of US Social Security Number as a String
 * @author dave
 */
public class SsnAsStringTest {
	
	@Test
	public void itRecognizesAValidSsn() {
		assertTrue("324782265 is a valid SSN", Utils.validateSsn("324782265"));
	}
	
	@Test
	public void itCatchesNonNumericValue() {
		assertFalse("324X045X2 is not numeric", Utils.validateSsn("324X045X2"));
	}
	
	@Test
	public void itCatchesPrefix_000() {
		assertFalse("000782265 has invalid prefix 000", Utils.validateSsn("000782265"));
	}
	
	@Test
	public void itCatchesPrefix_666() {
		assertFalse("666782265 has invalid prefix 666", Utils.validateSsn("666782265"));
	}
	
	@Test
	public void itCatchesPrefix_inThe_900_range() {
		assertFalse("900782265 has invalid prefix 900", Utils.validateSsn("900782265"));
	}
	
	@Test
	public void itCatchesInfix_00() {
		assertFalse("324002265 has invalid infix 00", Utils.validateSsn("324002265"));
	}
	
	@Test
	public void itCatchesSuffix_0000() {
		assertFalse("324780000 has invalid suffix 0000", Utils.validateSsn("324780000"));
	}

}
