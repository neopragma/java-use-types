package com.neopragma.javatypes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for a class that represents the domain concept, Vehicle Identification Number.
 * @author dave
 */
public class VinTest {
	
	private String validTestVin = "1HGCM82633A004352";
	private String straightOnes = "11111111111111111";
	
	private Vin vin;
	
	@Before 
	public void beforeEach() {
		vin = new Vin(validTestVin);
	}

	@Test
	public void itAcceptsValidVinNumber() {
		assertEquals(validTestVin, vin.toString());
	}

	@Test(expected=InvalidVinException.class)
	public void itFailsGracefullyGivenNullInput() {
		new Vin(null);
	}
	
	@Test
	public void itCalculatesTheCheckDigit() {
		assertEquals("3", vin.checkDigit());
	}
	
	@Test(expected=InvalidVinException.class)
	public void itRejectsWhenCalculatedCheckDigitDiffersFromTheGivenValue() {
		String badTestVin = validTestVin.substring(0, 8) + "9" + validTestVin.substring(9);
		new Vin(badTestVin);
	}

	@Test
	public void canonicalAllOnesCheckDigitTest() {
		assertEquals("1", new Vin(straightOnes).checkDigit());
	}
	
	@Test
	public void itReturnsWorldManufacturerIdentifier() {
		assertEquals(Manufacturer.valueOf("WMI_1HG"), vin.getWorldManufacturerIdentifier());
	}
	
	
}
