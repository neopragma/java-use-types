package com.neopragma.javatypes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test cases to exercise a VIN implementation as String.
 * @author dave
 */
public class VinAsStringTest {
	
	@Test
	public void itValidatesVinOfCorrectLength() {
		assertTrue("12345678901234567 should be a valid VIN", Utils.validateVin("12345678901234567"));
	}
	
	@Test
	public void itRecognizesNullVinAsInvalid() {
		assertFalse("Null VIN is invalid", Utils.validateVin(null));
	}
	
	@Test
	public void itRecognizesVinLessThan17CharactersLongAsInvalid() {
		assertFalse("VIN less than 17 characters long is invalid", Utils.validateVin("1234567890123456"));
	}
	
	@Test
	public void itRecognizesVinMoreThan17CharactersLongAsInvalid() {
		assertFalse("VIN more than 17 characters long is invalid", Utils.validateVin("123456789012345678"));
	}
	

}
