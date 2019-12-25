package com.bill.ema.emaServer.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.PermissionDao;
import com.bill.ema.emaModel.dao.Role2PermissionDao;
import com.bill.ema.emaModel.dao.RoleDao;
import com.bill.ema.emaModel.dao.User2RoleDao;
import com.bill.ema.emaModel.dao.UserDao;
import com.bill.ema.emaModel.entity.Permission;
import com.bill.ema.emaModel.entity.Role;
import com.bill.ema.emaModel.entity.Role2Permission;
import com.bill.ema.emaModel.entity.User;
import com.bill.ema.emaModel.entity.User2Role;
import com.bill.ema.emaServer.service.RoleService;

public class RoleServiceImpl extends ServiceImpl<RoleDao,Role> implements RoleService{
	
	@Autowired
	private User2RoleDao user2RoleDao;
	
	@Autowired
	private Role2PermissionDao role2PermissionDao;
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Role getByName(String name) {
		QueryWrapper<Role> query = new QueryWrapper();
		query.eq("name", name);		
		return baseMapper.selectOne(query);
	}

	@Override
	public Set<Role> getByPermissionId(Integer permissionId) {
		QueryWrapper<Role2Permission> query1 = new QueryWrapper<Role2Permission>();
		query1.eq("permissionId",permissionId);
		Set<Role> list = new HashSet<Role>();
		List<Role2Permission> list1 = role2PermissionDao.selectList(query1);
		for(Role2Permission data:list1) {					
			list.add(baseMapper.selectById(data.getRoleId()));
		}
		return list;
	}

	@Override
	public Set<Role> getByPermissionName(String permissionName) {
		QueryWrapper<Permission> query0 = new QueryWrapper<Permission>();
		query0.eq("name", permissionName);
		Permission perimission = permissionDao.selectOne(query0);
		
		QueryWrapper<Role2Permission> query1 = new QueryWrapper<Role2Permission>();
		query1.eq("permissionId",perimission.getId());
		
		Set<Role> list = new HashSet<Role>();
		List<Role2Permission> list1 = role2PermissionDao.selectList(query1);
		for(Role2Permission data:list1) {					
			list.add(baseMapper.selectById(data.getRoleId()));
		}
		return list;
	}

	@Override
	public Set<Role> getByUserId(Integer userId) {
		QueryWrapper<User2Role> query1 = new QueryWrapper<User2Role>();
		query1.eq("userId",userId);
		Set<Role> list = new HashSet<Role>();
		List<User2Role> list1 = user2RoleDao.selectList(query1);
		for(User2Role data:list1) {					
			list.add(baseMapper.selectById(data.getRoleId()));
		}
		return list;
	}

	@Override
	public Set<Role> getByUsername(String username) {
		QueryWrapper<User> query0 = new QueryWrapper<User>();
		query0.eq("name", username);
		User user = userDao.selectOne(query0);
		
		QueryWrapper<User2Role> query1 = new QueryWrapper<User2Role>();
		query1.eq("userId",user.getId());
		
		Set<Role> list = new HashSet<Role>();
		List<User2Role> list1 = user2RoleDao.selectList(query1);
		for(User2Role data:list1) {					
			list.add(baseMapper.selectById(data.getRoleId()));
		}
		return list;
	}
	
}
