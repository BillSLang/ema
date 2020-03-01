package com.bill.ema.emaServer.service;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaModel.entity.Permission;

public interface PermissionService extends IService<Permission>{
	Permission getByName(String name);
	Set<Permission> listByRoleName(String roleName);
	Set<Permission> listByRoleId(Integer id);
	Set<Permission> listByUserId(Integer id);
	PageUtil queryPage(Map<String, Object> param);
	R edit(Map<String, Object> param);
	R create(Map<String, Object> param);
	void delete(Map<String, Object> param);

}
