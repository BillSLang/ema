package com.bill.ema.emaModel.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.ProductCode;

public interface ProductCodeDao extends BaseMapper<ProductCode>{
	List<ProductCode> selectForPage(IPage<ProductCode> page, Map<String, Object> param);

}
