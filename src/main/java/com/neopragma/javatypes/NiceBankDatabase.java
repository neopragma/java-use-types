package com.neopragma.javatypes;

import java.util.Optional;

import javax.money.MonetaryAmount;

public interface NiceBankDatabase {
	
	Optional<MonetaryAmount> getCurrentBalance(AccountNumber accountNumber);
	MonetaryAmount deposit(MonetaryAmount amount, AccountNumber accountNumber);
	MonetaryAmount withdraw(MonetaryAmount amount, AccountNumber accountNumber);
	AccountStatus getAccountStatus(AccountNumber accountNumber);
	TransactionStatus transferFunds(MonetaryAmount amount, AccountNumber sourceAccountNumber, AccountNumber targetAccountNumber);
	AccountStatus changeAccountStatus(AccountNumber accountNumber, AccountStatus accountStatus);

}
