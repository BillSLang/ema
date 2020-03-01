package com.bill.ema.emaServer.service;

import java.util.Map;

import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaModel.vo.AuthorizationEditVo;

public interface AuthorizationService {

	PageUtil queryPage(Map<String, Object> param);
	AuthorizationEditVo getByRoleId(Integer id);
	R authorize(Map<String, Object> param);
}
