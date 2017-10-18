package com.neopragma.javatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Tests behavior of a class that uses Ssn defined as a String.
 * @author dave
 */
public class SsnStringClientTest {
	
	/** 
	 * FALSE POSITIVE
	 * The SSN is not valid. Methods of SsnStringClient
	 * does not call always call the SSN validation utility 
	 * method when they should. The test suite runs green 
	 * but the application is broken.
	 */
	@Test
	public void itFormatsAnSsn() {
		SsnStringClient ssnClient = new SsnStringClient("324002265");
		assertEquals("324-00-2265", ssnClient.formatSsn());
	}
	
	/**
	 * FALSE POSITIVE
	 */
	@Test
	public void itObfuscatesAnSsn() {
		SsnStringClient ssnClient = new SsnStringClient("6667X4040");
		assertEquals("*****4040", ssnClient.obfuscateSsn());
	}
	
	/**
	 * FALSE POSITIVE
	 */
	@Test
	public void itReturnsTheLastFourDigitsOfAnSsn() {
		SsnStringClient ssnClient = new SsnStringClient("garbage");
		assertEquals("ge", ssnClient.getLastFourDigits());
	}
	
	/**
	 * The existence of this test is a code smell.
	 * Code that uses an SSN should not have to validate the SSN.
	 */
	@Test
	public void itDetectsInvalidSsn() {
		SsnStringClient ssnClient = new SsnStringClient("324002265");
		assertFalse(ssnClient.isSsnValid());
	}

}
