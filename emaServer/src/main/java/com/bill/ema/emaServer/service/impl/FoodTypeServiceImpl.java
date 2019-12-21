package com.bill.ema.emaServer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.FoodTypeDao;
import com.bill.ema.emaModel.entity.FoodType;
import com.bill.ema.emaServer.service.FoodTypeService;

@Service("foodTypeService")
public class FoodTypeServiceImpl extends ServiceImpl<FoodTypeDao,FoodType> implements FoodTypeService{
	
}
