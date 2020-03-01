package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.Brand;

public interface BrandDao extends BaseMapper<Brand>{
	List<Brand> selectForPage(IPage<Brand> page, Map<String, Object> param);

}
