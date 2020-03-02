package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.Uri;

public interface UriDao extends BaseMapper<Uri>{
	List<Uri> selectForPage(IPage<Uri> page, Map<String, Object> param);

}
