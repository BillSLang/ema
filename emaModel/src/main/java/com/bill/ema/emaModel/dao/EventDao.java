package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.Event;

public interface EventDao extends BaseMapper<Event>{
	List<Event> selectForPage(IPage<Event> page, Map<String, Object> param);

}
