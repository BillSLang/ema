package com.bill.ema.emaCommon.response;

public enum STATUSCODE {
	SUCCESS(0,"success"),
	FAIL(-1,"fail"),
	INVALIDCAPTCHA(1000,"验证码错误"),
	ACCOUNTNOTEXIST(1001,"用户不存在"),
	ACCOUNTPASSWORDINCORRECT(1002,"用户或密码错误"),
	ACCOUNTHASBEENLOCKED(1003,"用户被上锁"),
	ACCOUNTHASBEENACTIVE(1004,"用户不可以用"),
	ACCOUNTVALIDATEFAIL(1005,"用户认证失败"),
	CURRUSERNOTPERMISSION(1006,"当前用户没有权限"),
	USERNAMEEXIST(1007,"用户名已存在，请重新输入用户名"),
	EMAILEXIST(1008,"邮箱地址已存在，请重新输入邮箱地址");
	
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
