package com.bill.ema.emaServer.service;

import com.bill.ema.emaCommon.response.R;

public interface ValidateService {
	public R verifyUseranme(String username);
	public R verifyEmail(String email);
	public R verifyPhone(String phone);
}
