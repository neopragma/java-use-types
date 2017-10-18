package com.neopragma.javatypes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class encapsulates the domain concept, Social Security Number.
 * @author dave
 */
public final class Ssn {
	
	public static final String TEST_SSN = "987654321";
	
	private String areaNumber;
	private String groupNumber;
	private String serialNumber;
	
	private static final String EMPTY_STRING = "";
	private static final String DASH = "-";
	private static final String MSG_NOT_NUMERIC = "SSN must be nine numeric digits";
	private static final String MSG_INVALID_PREFIX = "SSN area number must not be 000, 666, or >740";
	private static final String MSG_INVALID_INFIX = "SSN group number must not be 00";
	private static final String MSG_INVALID_SUFFIX = "SSN serial number must not be 0000";
	private static final String MSG_KNOWN_INVALID = "SSN {0} is on the list of known invalid numbers";

	/** These have been used in the past by mistake and can't be issued. */
	private static final List<String> badSsnList = new ArrayList<String>(
	    Arrays.asList(
	    "002281852", "042103580", "062360749", "078051120", "095073645",
        "128036045", "135016629", "141186941", "165167999", "165187999",
        "165207999", "165227999", "165247999", "189092294", "212097694",
        "212099999", "306302348", "308125070", "468288779", "549241889",
        "078051120", "219099999"
	));

	private static final String reservedAreaNumber = "987";

	public Ssn(String ssnAsString) {
		validateSsn(ssnAsString);
		this.areaNumber = ssnAsString.substring(0, 3);
		this.groupNumber = ssnAsString.substring(3, 5);
		this.serialNumber = ssnAsString.substring(5);		
	}

	@Override
	public String toString() {
		return areaNumber + groupNumber + serialNumber;
	}
	
	private void validateSsn(String ssn) {
		if (ssn == null || ssn.equals(EMPTY_STRING)) {
			error(MSG_NOT_NUMERIC);
		}
		if (!ssn.matches("^\\d{9}$")) {
			error(MSG_NOT_NUMERIC);
		}
		if (badSsnList.contains(ssn)) {
			error(MessageFormat.format(MSG_KNOWN_INVALID, ssn));
		}
		String areaNumber = ssn.substring(0, 3);
		if (areaNumber.equals("000") || areaNumber.equals("666")) {
			error(MSG_INVALID_PREFIX);
		}
		if (areaNumber.compareTo("740") > 0 && !areaNumber.equals(reservedAreaNumber)) {
			error(MSG_INVALID_PREFIX);
		}
		if (ssn.substring(3, 5).equals("00")) {
			error(MSG_INVALID_INFIX);
		}
		if (ssn.substring(5).equals("0000")) { 
			error(MSG_INVALID_SUFFIX);
		}
	}
	
	private void error(String message) {
		throw new InvalidSsnException(message);
	}

	public String format() {
		return areaNumber + DASH + groupNumber + DASH + serialNumber;
	}

	public String obfuscate() {
		return "*****" + serialNumber;
	}

	public String getLastFourDigits() {
		return serialNumber;
	}
	
}
