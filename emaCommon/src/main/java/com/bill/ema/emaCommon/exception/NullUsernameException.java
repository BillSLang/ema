package com.bill.ema.emaCommon.exception;

public class NullUsernameException extends NullPointerException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullUsernameException() {
		super();
	}

	public NullUsernameException(String msg) {
		super(msg);
	}

}
