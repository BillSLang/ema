package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.Food;

public interface FoodDao extends BaseMapper<Food>{
	List<Food> selectForPage(IPage<Food> page, Map<String, Object> param);

}
