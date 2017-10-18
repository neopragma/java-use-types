package com.neopragma.javatypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vin {
	
	private String vin;
	
	private static final Map<String, Integer> transliterationTable = makeTransliterationTable();
	private static final int[] checkDigitWeights = new int[] { 8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2 };

	public Vin(String vinAsString) {
		if (vinAsString == null) {
			throw new InvalidVinException();
		}
		if (!vinAsString.matches("^[A-Z|0-9]{8}\\d{1}[A-H|J-N|P|R-T|V-Y|0-9]{1}[A-Z|0-9]{7}$")) {
			throw new InvalidVinException();
		}
		if (!vinAsString.substring(8, 9).equals(checkDigit(vinAsString))) {
			throw new InvalidVinException();
		}
		vin = vinAsString;
	}
	
	public Manufacturer getWorldManufacturerIdentifier() {
		return Manufacturer.valueOf("WMI_" + vin.substring(0, 3));
	}	
	
	/**
	 * @return VIN value as a String.
	 */
	@Override
	public String toString() {
		return vin;
	}

	String checkDigit() {
		return checkDigit(vin);
	}
	
	String checkDigit(String vin) {
        List<Integer> weightedNumbers = new ArrayList<Integer>();
        for(int i = 0 ; i < vin.length(); i++) {
            String current = vin.substring(i, i+1);        	
        	weightedNumbers.add(transliterationTable.get(current) * checkDigitWeights[i]);
        }	
        int sumOfWeightedNumbers = weightedNumbers.stream().mapToInt(i -> i.intValue()).sum();
        int modulus = sumOfWeightedNumbers % 11;
	    return modulus == 10 ? "X" : String.valueOf(modulus);
	}
	
	private static Map<String, Integer> makeTransliterationTable() {
		Map<String, Integer> tempMap = new HashMap<String, Integer>();
		tempMap.put("A", 1);
		tempMap.put("B", 2);
		tempMap.put("C", 3);
		tempMap.put("D", 4);
		tempMap.put("E", 5);
		tempMap.put("F", 6);
		tempMap.put("G", 7);
		tempMap.put("H", 8);
		tempMap.put("J", 1);
		tempMap.put("K", 2);
		tempMap.put("L", 3);
		tempMap.put("M", 4);
		tempMap.put("N", 5);
		tempMap.put("P", 7);
		tempMap.put("R", 9);
		tempMap.put("S", 2);
		tempMap.put("T", 3);
		tempMap.put("U", 4);
		tempMap.put("V", 5);
		tempMap.put("W", 6);
		tempMap.put("X", 7);
		tempMap.put("Y", 8);
		tempMap.put("Z", 9);
		tempMap.put("0", 0);
		tempMap.put("1", 1);
		tempMap.put("2", 2);
		tempMap.put("3", 3);
		tempMap.put("4", 4);
		tempMap.put("5", 5);
		tempMap.put("6", 6);
		tempMap.put("7", 7);
		tempMap.put("8", 8);
		tempMap.put("9", 9);
		return tempMap;
	}

}
