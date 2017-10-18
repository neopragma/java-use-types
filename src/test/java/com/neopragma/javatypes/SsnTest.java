package com.neopragma.javatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases to exercise a class that represents a US Social Security Number.
 * SSN validation need not be checked anywhere else in the test suite or code base.
 * @author dave
 */
public class SsnTest {
	
	private Ssn ssn;
	private static final String NO_EXCEPTION = "no exception was thrown";
	
	@Before
	public void beforeEach() {
		ssn = new Ssn("324782265");
	}
	
	@Test
	public void itRecognizesAValidSsn() {
		assertEquals("324782265", ssn.toString());
	}
	
	@Test
	public void itFormatsAnSsn() {
		assertEquals("324-78-2265", ssn.format());
	}
	
	@Test
	public void itObfuscatesAnSsn() {
		assertEquals("*****2265", ssn.obfuscate());
	}
	
	@Test
	public void itReturnsTheLastFourDigits() {
		assertEquals("2265", ssn.getLastFourDigits());
	}
	
	@Test
	public void itReturnsTheStandardTestSsn() {
		assertEquals("987654321", Ssn.TEST_SSN);
	}

	@Test
	public void itCatchesInvalidSsnValues() {
		String[][] entries = new String[9][];
		entries[0] =	new String[] { "3247XQ265", "SSN must be nine numeric digits", NO_EXCEPTION };
		entries[1] = new String[] { "000654320", "SSN area number must not be 000, 666, or >740", NO_EXCEPTION };
		entries[2] = new String[] { "666654320", "SSN area number must not be 000, 666, or >740", NO_EXCEPTION };
		entries[3] = new String[] { "741654320", "SSN area number must not be 000, 666, or >740", NO_EXCEPTION };
		entries[4] = new String[] { "987004320", "SSN group number must not be 00", NO_EXCEPTION };
		entries[5] = new String[] { "987650000", "SSN serial number must not be 0000", NO_EXCEPTION };
		entries[6] = new String[] { "141186941", "SSN 141186941 is on the list of known invalid numbers", NO_EXCEPTION };
		entries[7] = new String[] { "219099999", "SSN 219099999 is on the list of known invalid numbers", NO_EXCEPTION };
		entries[8] = new String[] { null, "SSN must be nine numeric digits", NO_EXCEPTION };
		
		for (String [] values : entries) {
			checkSsnValidation(values);
		}
	}
	
	private void checkSsnValidation(String... values) {
		try {
			new Ssn(values[0]);
            failWithMessage(values[0], values[1], values[2]);			
		} catch( InvalidSsnException e ) {
			assertEquals(values[1], e.getMessage());
		} catch ( Exception e1 ) {
            failWithMessage(values[0], values[1], "got " + e1.getClass().getName());						
		}
	}
	
    private void failWithMessage(String badSsn, String message, String detail) {
    	fail("Expected InvalidSsnException with message '" 
            + message
            + "' but " + detail);
    }

}
