package com.bill.ema.emaModel.dao;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.Subject;

public interface SubjectDao extends BaseMapper<Subject>{
	List<Subject> selectForPage(IPage<Subject> page, Map<String, Object> param);

}
