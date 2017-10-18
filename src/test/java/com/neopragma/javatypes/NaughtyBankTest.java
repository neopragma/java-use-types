package com.neopragma.javatypes;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NaughtyBankTest {
	
	private NaughtyBank bank;
	
	@Mock
	private NaughtyBankDatabase db;
	
	@Before
	public void beforeEach() {
		bank = new NaughtyBank(db);
	}
	
	@Test
	public void itReturnsTheCurrentBalanceForAnAccount() {
		bank.queryCurrentBalance("foo");
		verify(db).getCurrentBalance(anyString());
	}
	
	@Test
	public void itDepositsAnAmountIntoAnAccount() {
		bank.deposit(500.0d, "foo");
		verify(db).deposit(anyDouble(), anyString());
	}
	
	@Test
	public void itWithdrawsAnAmountFromAnAccount() {
		bank.withdraw(500.0d, "foo");
		verify(db).withdraw(anyDouble(), anyString());
	}
	
	@Test
	public void itReturnsTheAccountStatus() {
		when(db.getAccountStatus("foo")).thenReturn(1);
		assertEquals(1, bank.getAccountStatus("foo"));
	}
	
	@Test
	public void itTransfersFunds() {
		bank.transferFunds(anyDouble(), anyString(), anyString());
		verify(db).transferFunds(anyDouble(), anyString(), anyString());
	}
	
	@Test
	public void itActivatesAnAccount() {
		when(db.changeAccountStatus("foo", 1)).thenReturn(1);;
		assertEquals(1, bank.activateAccount("foo"));
	}
	
	@Test
	public void itClosesAnAccount() {
		when(db.changeAccountStatus("foo", 2)).thenReturn(2);;
		assertEquals(2, bank.closeAccount("foo"));
	}
	
	

}

