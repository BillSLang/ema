package com.bill.ema.emaServer.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaModel.entity.ProductCode;

public interface ProductCodeService extends IService<ProductCode>{
	PageUtil queryPage(Map<String, Object> param);
	
	Boolean create(Map<String,Object> param);
	Boolean edit(Map<String,Object> param);

	Boolean delete(Map<String,Object> param);
}
