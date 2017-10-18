package com.neopragma.javatypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.MonetaryCurrencies;
import javax.money.MonetaryOperator;
import javax.money.MonetaryRoundings;

import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

/**
 * playing with javax.money. this is throwaway code.
 * @author dave
 *
 */
public class MoneyTest {
	
	CurrencyUnit defaultCurrency = MonetaryCurrencies.getCurrency("USD");
	MonetaryOperator rounding = MonetaryRoundings.getRounding(defaultCurrency);
	MonetaryAmount amt1;
	MonetaryAmount amt2;
	
	@Before
	public void beforeEach() {
		amt1 = Money.of(200.504, defaultCurrency);
		amt2 = Money.of(19.758, defaultCurrency);
	}
	
	@Test
	public void addingTwoMonetaryAmounts() {
		assertEquals(220.26d, amt1.add(amt2).with(rounding).getNumber().doubleValue(), 0.005d);
	}

	@Test
	public void comparingTwoMonetaryAmounts() {
        assertTrue("first > second", 0 < amt1.compareTo(amt2));	
        assertTrue("second > first", 0 > amt2.compareTo(amt1));
        assertFalse("equality", 0 == amt1.compareTo(amt2));
	}

}
