package com.neopragma.javatypes;

public enum AccountType {
	
	BUSINESS_CHECKING("102"),
	PERSONAL_CHECKING("212"),
	PERSONAL_SAVINGS("222");
	
	private String code;
	
	private AccountType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static AccountType fromString(String code) {
	    for (AccountType at : AccountType.values()) {
	      if (at.code.equalsIgnoreCase(code)) {
	        return at;
	      }
	    }
	    throw new IllegalArgumentException("No AccountType with code " + code + " found.");
	  }
}
