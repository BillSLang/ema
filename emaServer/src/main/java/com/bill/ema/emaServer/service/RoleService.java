package com.bill.ema.emaServer.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.ema.emaModel.entity.Role;

@Service("roleService")
public interface RoleService extends IService<Role>{
	Role getByName(String name);
	Set<Role> getByPermissionId(Integer permissionId);
	Set<Role> getByPermissionName(String permissionName);
	Set<Role> getByUserId(Integer userId);
	Set<Role> getByUsername(String username);
}
