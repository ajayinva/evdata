package com.ajay.evdata.exception;

public class BadDataException extends RuntimeException{

	private static final long serialVersionUID = -2210874462880783621L;
	
	public BadDataException(String message) {
		super(message);
	}

}
