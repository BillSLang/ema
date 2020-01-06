package com.bill.ema.emaCommon.response;

public enum STATUSCODE {
	SUCCESS(0,"success"),
	FAIL(-1,"fail");	
	
	private Integer code;
	private String msg;
	
	STATUSCODE(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
