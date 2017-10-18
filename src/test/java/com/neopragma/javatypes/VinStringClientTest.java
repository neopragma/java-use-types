package com.neopragma.javatypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test cases for application code that uses VINs represented as strings.
 * The developers cut corners because it's inconvenient to work with VINs as strings.
 * Most of the VIN values in these test cases are invalid, and would be flagged as
 * invalid even by the relatively weak validation logic in Utils.validateVin(). 
 * But there's no guarantee client code will ever call that validation routine. 
 * The VIN is really just a string, and could contain anything. These test cases
 * are unreliable and probably yield false positives. 
 * @author dave
 */
public class VinStringClientTest {

	@Test
	public void itIdentifiesJapaneseCar() {
		assertEquals("Japan", new VinStringClient().countryOfOrigin("J2345678901234567"));
	}

	@Test
	public void itIdentifiesKoreanCar() {
		assertEquals("Korea", new VinStringClient().countryOfOrigin("K23"));
	}

	@Test
	public void itIdentifiesGermanCar() {
		assertEquals("Germany", new VinStringClient().countryOfOrigin("W23456"));
	}

	@Test
	public void itIdentifiesAmericanCarWithFirstDigit_1() {
		assertEquals("United States", new VinStringClient().countryOfOrigin("12345678901234567"));
	}

	@Test
	public void itIdentifiesAmericanCarWithFirstDigit_4() {
		assertEquals("United States", new VinStringClient().countryOfOrigin("42345678901234567"));
	}

	@Test
	public void itIdentifiesAmericanCarWithFirstDigit_5() {
		assertEquals("United States", new VinStringClient().countryOfOrigin("5234567890"));
	}
	
	
}
