package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.StoreMethod;


public interface StoreMethodDao extends BaseMapper<StoreMethod>{
	List<StoreMethod> selectForPage(IPage<StoreMethod> page, Map<String, Object> param);

}
