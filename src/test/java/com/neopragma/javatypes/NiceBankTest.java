package com.neopragma.javatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * A few sample test cases for the "nice" bank application.
 * @author dave
 */
@RunWith(MockitoJUnitRunner.class)
public class NiceBankTest implements Constants {
	
	private NiceBank bank;
	private AccountNumber testAccountNumber1 = new AccountNumber("1021111111111111");
	
	@Mock
	private NiceBankDatabase db;
		
	@Before
	public void beforeEach() {
		bank = new NiceBank(db);
	}
	
	@Test
	public void itReturnsTheCurrentBalanceForAnAccount() {
		when(db.getCurrentBalance(testAccountNumber1)).thenReturn(
			Optional.of(Money.of(1000.00, defaultCurrency))
		);
		BackendResponse response = bank.queryCurrentBalance(testAccountNumber1);
		assertEquals(response.getTransactionStatus(), TransactionStatus.COMPLETED);
		assertEquals("Expected amount 1,000.00", 0, response.getAmount().get().compareTo(Money.of(1000.00, defaultCurrency)));
	}
	
	@Test
	public void itReportsProblemWhenTransactionCannotBeCompleted() {
		when(db.getCurrentBalance(testAccountNumber1)).thenThrow(
		    new RuntimeException()
		);
		BackendResponse response = bank.queryCurrentBalance(testAccountNumber1);
		assertEquals(response.getTransactionStatus(), TransactionStatus.INTERRUPTED);
		assertNull("Amount should be null", response.getAmount());
	}

}
