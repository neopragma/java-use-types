package com.neopragma.javatypes;

public class VinStringClient {

	public String countryOfOrigin(String vin) {
		Character countryId = vin.charAt(0);
		switch (countryId) {
		    case 'J' : return "Japan";
		    case 'K' : return "Korea";
		    case 'W' : return "Germany";
		    case '1' :
		    case '4' :
		    case '5' : return "United States";
		    default : return "";
		}
	}

}
