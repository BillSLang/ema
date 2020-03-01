package com.bill.ema.emaServer.service;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaModel.entity.Role;

@Service("roleService")
public interface RoleService extends IService<Role>{
	Role getByName(String name);
	Set<Role> listByPermissionId(Integer permissionId);
	Set<Role> listByPermissionName(String permissionName);
	Set<Role> listByUserId(Integer userId);
	Set<Role> listByUsername(String username);
	PageUtil queryPage(Map<String, Object> param);
	R edit(Map<String, Object> param);
	R create(Map<String, Object> param);
	void delete(Map<String, Object> param);
}
