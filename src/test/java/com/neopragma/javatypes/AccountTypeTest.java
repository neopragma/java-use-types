package com.neopragma.javatypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AccountTypeTest {
	
	@Test
	public void itInstantiatesAccountTypeBasedOnCodeText() {
		assertEquals(AccountType.BUSINESS_CHECKING, AccountType.fromString("102"));
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void itComplainsWhenTheCodeTextIsNotDefinedForTheEnum() {
		AccountType.fromString("XXX");
	}

}
