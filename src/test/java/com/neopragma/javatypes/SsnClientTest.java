package com.neopragma.javatypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests behavior of a class that uses an Ssn object.
 * No test cases are necessary to verify Ssn validity.
 * Only unique client code functionality is checked.
 * @author dave
 */
public class SsnClientTest {
	
	@Test
	public void itReturnsSsnMessage() {
		assertEquals("The SSN is 987-65-4320", new SsnClient(new Ssn("987654320")).ssnMessage());
	}
	
}
