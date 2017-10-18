package com.neopragma.javatypes;

public enum Manufacturer {
	
	WMI_1GC("1GC", "Chevrolet Truck USA", "General Motors USA"),
	WMI_1HG("1HG", "Honda USA", "Honda");
	
	private final String wmi;
	private final String manufacturerName;
	private final String corporationName;
	
	private Manufacturer(String wmi, String manufacturerName, String corporationName) {
		this.wmi = wmi;
		this.manufacturerName = manufacturerName;
		this.corporationName = corporationName;
	}

	public String getWmi() {
		return wmi;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public String getCorporationName() {
		return corporationName;
	}

}
