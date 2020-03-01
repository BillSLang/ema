package com.bill.ema.emaCommon.exception;

public class NullCaptchaException extends NullPointerException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullCaptchaException() {
		super();
	}

	public NullCaptchaException(String msg) {
		super(msg);
	}

}
