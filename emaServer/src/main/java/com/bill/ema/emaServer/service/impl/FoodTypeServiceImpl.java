package com.bill.ema.emaServer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaModel.dao.FoodTypeDao;
import com.bill.ema.emaModel.entity.Food;
import com.bill.ema.emaModel.entity.FoodType;
import com.bill.ema.emaServer.service.FoodTypeService;

@Service("foodTypeService")
public class FoodTypeServiceImpl extends ServiceImpl<FoodTypeDao,FoodType> implements FoodTypeService{

	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<FoodType> page = new QueryUtil<FoodType>().getQueryPage(param);
		List<FoodType> list = baseMapper.selectForPage(page, param);
		return new PageUtil(page,list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		FoodType foodType = new FoodType(param);
		if (save(foodType))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		FoodType foodType = new FoodType(param);
		if (updateById(foodType))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean delete(Map<String, Object> param) {
		for (Object id : param.values()) {
			if(!this.removeById(Integer.valueOf(id.toString())))
				return false;
		}
		return true;
	}
}
