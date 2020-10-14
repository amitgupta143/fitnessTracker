package com.deloitte.fitnesstracker.exception;

public class TokenNotValidException extends RuntimeException {

	private static final long serialVersionUID = -4941469913264522122L;
	private String message;
	
	public TokenNotValidException() {
		super();
	}
	
	public TokenNotValidException(String message) {
		super(message);
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
