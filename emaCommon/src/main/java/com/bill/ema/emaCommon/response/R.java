package com.bill.ema.emaCommon.response;

import java.util.HashMap;

import com.google.common.collect.Maps;

public class R extends HashMap<String,Object>{

	private static final long serialVersionUID = 1L;

	public R() {
		put("code",0);
		put("msg","success");
		put("data",Maps.newHashMap());
	}
	
	public static R ERROR() {
		return ERROR();
	}
	
	public static R ERROR(String msg) {
		return ERROR(STATUSCODE.FAIL.getCode(),msg);
	}
	
	public static R ERROR(int code,String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
	
	public static R OK() {
		return new R();
	}
	
	public static R OK(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R OK(String msg,Object data) {
		R r = new R();
		r.put("msg", msg);
		r.put("data", data);
		return r;
	}
	
	public static R OK(Object data) {
		R r = new R();
		r.put("data", data);
		return r;
	}
	
	public static R map(String key,Object data) {
		R r = new R();
		r.put("key", data);
		return r;
	}	
}
