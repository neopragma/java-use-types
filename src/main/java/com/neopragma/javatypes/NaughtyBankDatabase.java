package com.neopragma.javatypes;

public interface NaughtyBankDatabase {
	double getCurrentBalance(String accountNumber);
	double deposit(double amount, String accountNumber);
	double withdraw(double amount, String accountNumber);
	int getAccountStatus(String accountNumber);
	boolean transferFunds(double amount, String sourceAccountNumber, String targetAccountNumber);
	int changeAccountStatus(String accountNumber, int accountStatus);
}
