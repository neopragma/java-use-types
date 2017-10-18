package com.neopragma.javatypes;

public class SsnClient {
	
	private Ssn ssn;
	
	public SsnClient(Ssn ssn) {
		this.ssn = ssn;
	}

	public String ssnMessage() {
		return "The SSN is " + ssn.format();
	}

}
