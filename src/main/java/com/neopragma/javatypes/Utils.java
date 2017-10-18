package com.neopragma.javatypes;

/**
 * The existence of this class is a code smell.
 * @author dave
 */
public class Utils {
	
	public static boolean validateSsn(String ssn) {
		boolean result = true;
		if (!ssn.matches("^\\d{9}$")) {
			result = false;
		}
		String prefix = ssn.substring(0, 3);
		if (prefix.equals("000") || prefix.equals("666")) {
			result = false;
		}
		if (prefix.charAt(0) == '9') {
			result = false;
		}
		if (ssn.substring(3, 5).equals("00")) {
			result = false;
		}
		if (ssn.substring(5).equals("0000")) { 
			result = false;
		}		
		return result;
	}

	public static boolean validateVin(String vin) {
		if (vin != null && vin.length() == 17) {
			return true;
		}
		return false;
	}

}
