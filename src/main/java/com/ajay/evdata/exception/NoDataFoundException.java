package com.ajay.evdata.exception;

public class NoDataFoundException extends RuntimeException{

	private static final long serialVersionUID = -2210874462880783621L;
	
	public NoDataFoundException(String message) {
		super(message);
	}

}
