package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.FoodType;

public interface FoodTypeDao extends BaseMapper<FoodType>{
	List<FoodType> selectForPage(IPage<FoodType> page, Map<String, Object> param);

}
