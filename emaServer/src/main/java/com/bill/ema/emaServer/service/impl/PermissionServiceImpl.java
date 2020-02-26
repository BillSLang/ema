package com.bill.ema.emaServer.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.PermissionDao;
import com.bill.ema.emaModel.dao.Role2PermissionDao;
import com.bill.ema.emaModel.dao.RoleDao;
import com.bill.ema.emaModel.entity.Permission;
import com.bill.ema.emaModel.entity.Role;
import com.bill.ema.emaModel.entity.Role2Permission;
import com.bill.ema.emaServer.service.PermissionService;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDao,Permission> implements PermissionService{

	@Autowired
	private Role2PermissionDao role2PermissionDao;
	
	@Autowired
	private RoleDao roleDao;
	

	@Override
	public Permission getByName(String name) {
		QueryWrapper<Permission> query = new QueryWrapper<Permission>();
		query.eq("name",name);
		return baseMapper.selectOne(query);
	}

	@Override
	public Set<Permission> getByRoleId(Integer roleId) {
		QueryWrapper<Role2Permission> query1 = new QueryWrapper<Role2Permission>();		
		query1.eq("roleId", roleId);
		List<Role2Permission> role2PermissionList =  role2PermissionDao.selectList(query1);
		Set<Permission> permissionSet = new HashSet<Permission>();
		for(Role2Permission data:role2PermissionList) {
			QueryWrapper<Permission> query2 = new QueryWrapper<Permission>();	
			query2.eq("id", data.getPermissionId());						
			permissionSet.add(baseMapper.selectOne(query2));
		}
		return permissionSet;
	}

	@Override
	public Set<Permission> getByRoleName(String roleName) {
		QueryWrapper<Role> query0 = new QueryWrapper<Role>();
		query0.eq("name", roleName);		
		QueryWrapper<Role2Permission> query1 = new QueryWrapper<Role2Permission>();		
		query1.eq("role_id", roleDao.selectOne(query0).getName());
		List<Role2Permission> role2PermissionList =  role2PermissionDao.selectList(query1);
		Set<Permission> permissionSet = new HashSet<Permission>();
		for(Role2Permission data:role2PermissionList) {
			QueryWrapper<Permission> query2 = new QueryWrapper<Permission>();	
			query2.eq("id", data.getPermissionId());						
			permissionSet.add(baseMapper.selectOne(query2));
		}
		return permissionSet;
	}

}
