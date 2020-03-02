package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.Site;

public interface SiteDao extends BaseMapper<Site>{
	List<Site> selectForPage(IPage<Site> page, Map<String, Object> param);

}
