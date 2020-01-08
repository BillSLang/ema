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
import com.bill.ema.emaModel.dao.User2RoleDao;
import com.bill.ema.emaModel.dao.UserDao;
import com.bill.ema.emaModel.entity.Permission;
import com.bill.ema.emaModel.entity.Role;
import com.bill.ema.emaModel.entity.Role2Permission;
import com.bill.ema.emaModel.entity.User;
import com.bill.ema.emaModel.entity.User2Role;
import com.bill.ema.emaServer.service.UserService;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService{

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private User2RoleDao user2RoleDao;
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private Role2PermissionDao role2PermissionDao;
	
	@Override
	public User getByUsername(String username) {
		QueryWrapper<User> query = new QueryWrapper<User>();
		query.eq("username", username);		
		return baseMapper.selectOne(query);
	}
	
	@Override
	public User getByEmail(String email) {
		QueryWrapper<User> query = new QueryWrapper<User>();
		query.eq("email", email);		
		return baseMapper.selectOne(query);
	}

	@Override
	public Set<User> getByRoleId(Integer roleId) {
		QueryWrapper<User2Role> query1 = new QueryWrapper<User2Role>();
		query1.eq("roleId", roleId);
		List<User2Role> list1 = user2RoleDao.selectList(query1);
		Set<User> list = new HashSet();
		for(User2Role data:list1) {
			list.add(baseMapper.selectById(data.getUerId()));
		}
		return list;
	}

	@Override
	public Set<User> getByRoleName(String roleName) {
		QueryWrapper<Role> query0 = new QueryWrapper<Role>();
		query0.eq("name", roleName);
		QueryWrapper<User2Role> query1 = new QueryWrapper<User2Role>();
		query1.eq("roleId", roleDao.selectOne(query0).getId());
		List<User2Role> list1 = user2RoleDao.selectList(query1);
		Set<User> list = new HashSet();
		for(User2Role data:list1) {
			list.add(baseMapper.selectById(data.getUerId()));
		}
		return list;
	}

	@Override
	public Set<Permission> getAllPermission(Integer id) {		
		QueryWrapper<User2Role> query1 = new QueryWrapper();
		query1.eq("user", id);
		List<User2Role> list1 = user2RoleDao.selectList(query1);
		Set<Permission> result = new HashSet();
		for(User2Role data:list1) {
			QueryWrapper<Role2Permission> query2 = new QueryWrapper();
			query2.eq("roleId", data.getRoleId());
 			List<Role2Permission> list3 = role2PermissionDao.selectList(query2);
 			list3.stream().forEach(entity->{
 				result.add(permissionDao.selectById(entity.getPermissionId()));
 			});
		}
		return result;
	}

}
