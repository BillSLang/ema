package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.Expire;


public interface ExpireDao extends BaseMapper<Expire>{
	List<Expire> selectForPage(IPage<Expire> page, Map<String, Object> param);

}
