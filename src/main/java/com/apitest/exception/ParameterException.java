package com.apitest.exception;

public class ParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParameterException() {
        super();
    }

    public ParameterException(String message) {
        super();
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
