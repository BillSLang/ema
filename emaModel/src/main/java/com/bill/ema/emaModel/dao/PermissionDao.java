package com.bill.ema.emaModel.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.Permission;

public interface PermissionDao extends BaseMapper<Permission>{

	List<Permission> selectForPage(IPage<Permission> page, Map<String, Object> param);
}
