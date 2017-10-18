package com.neopragma.javatypes;

public class InvalidSsnException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvalidSsnException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
