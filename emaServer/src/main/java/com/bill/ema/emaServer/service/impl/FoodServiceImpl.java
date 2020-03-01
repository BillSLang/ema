package com.bill.ema.emaServer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaModel.dao.FoodDao;
import com.bill.ema.emaModel.entity.Food;
import com.bill.ema.emaServer.service.FoodService;

@Service("FoodService")
public class FoodServiceImpl extends ServiceImpl<FoodDao,Food> implements FoodService{

	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<Food> page = new QueryUtil<Food>().getQueryPage(param);
		List<Food> list = baseMapper.selectForPage(page, param);
		
		page.setRecords(list);
		return new PageUtil(page);
	}	
}
