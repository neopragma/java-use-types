package com.neopragma.javatypes;

public class NaughtyBank {
	
	public NaughtyBankDatabase db;
	
	public NaughtyBank(NaughtyBankDatabase db) {
		this.db = db;
	}

	public double queryCurrentBalance(String accountNumber) {
		return db.getCurrentBalance(accountNumber);
	}

	public double deposit(double amount, String accountNumber) {
		return db.deposit(amount, accountNumber);
	}

	public double withdraw(double amount, String accountNumber) {
        return db.withdraw(amount,  accountNumber);
	}

	public int getAccountStatus(String accountNumber) {
        return db.getAccountStatus(accountNumber);
	}

	public boolean transferFunds(double amount, String sourceAccountNumber, String targetAccountNumber) {
        return db.transferFunds(amount, sourceAccountNumber, targetAccountNumber);
	}

	public int activateAccount(String accountNumber) {
		return db.changeAccountStatus(accountNumber, 1);		
	}
	
	public int closeAccount(String accountNumber) {
		return db.changeAccountStatus(accountNumber, 2);
	}

}
