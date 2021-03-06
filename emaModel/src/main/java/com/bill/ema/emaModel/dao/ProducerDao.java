package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.Producer;

public interface ProducerDao extends BaseMapper<Producer>{
	List<Producer> selectForPage(IPage<Producer> page, Map<String, Object> param);

}
