package com.neopragma.javatypes;

public class SsnStringClient {
	
	private String ssn;
    private static final String DASH = "-";
	
	public SsnStringClient(String ssn) {
		this.ssn = ssn;
	}

	public String formatSsn() {
		return ssn.substring(0, 3) + DASH + ssn.substring(3, 5) + DASH + ssn.substring(5);
	}
	
	public boolean isSsnValid() {
	    return Utils.validateSsn(ssn);
    }

	public String obfuscateSsn() {
        return "*****" + ssn.substring(5);
	}

	public String getLastFourDigits() {
		return ssn.substring(5);
	}	

}
