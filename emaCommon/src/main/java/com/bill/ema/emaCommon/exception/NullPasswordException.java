package com.bill.ema.emaCommon.exception;

public class NullPasswordException extends NullPointerException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullPasswordException() {
		super();
	}

	public NullPasswordException(String msg) {
		super(msg);
	}

}
