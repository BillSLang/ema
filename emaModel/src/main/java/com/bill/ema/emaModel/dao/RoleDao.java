package com.bill.ema.emaModel.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.Role;

public interface RoleDao extends BaseMapper<Role>{

	List<Role> selectForPage(IPage<Role> page, Map<String, Object> param);
}
