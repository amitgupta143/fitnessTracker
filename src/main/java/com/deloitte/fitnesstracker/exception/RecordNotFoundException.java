package com.deloitte.fitnesstracker.exception;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2447937476397681318L;
	
	private String message;
	
	public RecordNotFoundException() {
		super();
	}
	
	public RecordNotFoundException(String message) {
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
