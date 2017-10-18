package com.neopragma.javatypes;

/**
 * This class represents the domain concept, Account Number.
 * It has some made-up validity rules for purposes of example.
 * @author dave
 */
public class AccountNumber {
	
	private AccountType accountType;
	private String accountNumberValue;
	
	public AccountNumber(String accountNumberValue) {
		if (!accountNumberValue.matches("^[102|212|222]\\d{15}$")) {
			throw new InvalidAccountNumberException();
		}
		this.accountType = AccountType.fromString(accountNumberValue.substring(0,3));
		this.accountNumberValue = accountNumberValue;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public String getAccountNumberValue() {
		return accountNumberValue;
	}

}
