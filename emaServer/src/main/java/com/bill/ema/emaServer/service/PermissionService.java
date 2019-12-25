package com.bill.ema.emaServer.service;

import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.ema.emaModel.entity.Permission;

public interface PermissionService extends IService<Permission>{
	Permission getByName(String name);
	Set<Permission> getByRoleId(Integer roleId);
	Set<Permission> getByRoleName(String roleName);
}
