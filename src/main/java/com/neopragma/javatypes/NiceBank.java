package com.neopragma.javatypes;

import java.util.Optional;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;

public class NiceBank {
	
	private NiceBankDatabase db;
	
	public NiceBank(NiceBankDatabase db) {
		this.db = db;
	}
	
	BackendResponse queryCurrentBalance(AccountNumber accountNumber) {
		try {
			Optional<MonetaryAmount> currentBalance = db.getCurrentBalance(accountNumber);
			return new BackendResponse(TransactionStatus.COMPLETED, null, null, currentBalance);
		} catch (RuntimeException e) {
			// logging, etc.
			return new BackendResponse(TransactionStatus.INTERRUPTED, null, null, null);
		}
	}
	

}
