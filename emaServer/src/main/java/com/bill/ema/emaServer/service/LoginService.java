package com.bill.ema.emaServer.service;

import java.util.Map;

import com.bill.ema.emaCommon.response.R;

public interface LoginService {
	public R authentication(Map<String,Object> param);
}
