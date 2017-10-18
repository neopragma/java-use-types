package com.neopragma.javatypes;

import java.util.Optional;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;

public class BackendResponse implements Constants {
	private TransactionStatus transactionStatus;
	private Optional<String> correlationTag = Optional.of(EMPTY_STRING);
	private Optional<String> message = Optional.of(EMPTY_STRING);
	private Optional<MonetaryAmount> amount = Optional.of(Money.of(0.00, defaultCurrency));
	private static final String EMPTY_STRING = "";
	
	public BackendResponse(
		    TransactionStatus transactionStatus,
		    Optional<String> correlationTag,
		    Optional<String> message,
		    Optional<MonetaryAmount> money) {
	    this.transactionStatus = transactionStatus;
	    this.correlationTag = correlationTag;
	    this.message = message;
	    this.amount = money;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public Optional<String> getCorrelationTag() {
		return correlationTag;
	}

	public Optional<String> getMessage() {
		return message;
	}

	public Optional<MonetaryAmount> getAmount() {
		return amount;
	}
}
