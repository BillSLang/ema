package com.bill.ema.emaServer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.FoodDao;
import com.bill.ema.emaModel.entity.Food;
import com.bill.ema.emaServer.service.FoodService;

@Service("FoodService")
public class FoodServiceImpl extends ServiceImpl<FoodDao,Food> implements FoodService{

}
