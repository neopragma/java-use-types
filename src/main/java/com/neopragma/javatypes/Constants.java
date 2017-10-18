package com.neopragma.javatypes;

import javax.money.CurrencyUnit;
import javax.money.MonetaryCurrencies;

public interface Constants {
	
	CurrencyUnit defaultCurrency = MonetaryCurrencies.getCurrency("USD");
}
