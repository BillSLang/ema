package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.FoodBatch;

public interface FoodBatchDao extends BaseMapper<FoodBatch>{
	List<FoodBatch> selectForPage(IPage<FoodBatch> page, Map<String, Object> param);

}
