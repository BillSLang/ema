package com.bill.ema.emaCommon.response;

public enum Statuscode {
	Success(0,"success"),
	Fail(-1,"fail"),
	InvalidCaptcha(1000,"验证码错误"),
	AccountNotExist(1001,"用户不存在"),
	AccountPasswordIncorrect(1002,"用户或密码错误"),
	AccountHasBeenLocked(1003,"用户被上锁"),
	AccountHasBeenActive(1004,"用户不可以用"),
	AccountValidatedFail(1005,"用户认证失败"),
	CurrUserNotPermission(1006,"当前用户没有权限"),
	UserNameExist(1007,"用户名已存在，请重新输入用户名"),
	EmailExist(1008,"邮箱地址已存在，请重新输入邮箱地址"), 
	PhoneExist(1009,"手机号已存在，请重新输入手机号"), 
	RolenameExist(1010,"角色名已存在，请重新输入角色名"), 
	CaptchaIsNull(1011,"验证码不能为空"), 
	UsernameIsNull(1012,"用户名不能为空"),
	PasswordIsNull(1013,"密码不能为空"), 
	PermissionnameExist(1014,"权限名已存在，请重新输入权限名");
	
	private Integer code;
	private String msg;
	
	Statuscode(Integer code,String msg){
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
