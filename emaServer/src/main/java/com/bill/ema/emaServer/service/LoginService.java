package com.bill.ema.emaServer.service;

import java.util.Map;

import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaModel.entity.User;

public interface LoginService {
	public R authentication(Map<String,Object> param);	
	public R register(Map<String,Object> param);
	
}
