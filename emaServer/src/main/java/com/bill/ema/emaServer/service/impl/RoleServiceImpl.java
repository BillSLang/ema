package com.bill.ema.emaServer.service.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.CollectionUtil;
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaCommon.util.TableCol;
import com.bill.ema.emaCommon.util.TransformUtil;
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
import com.bill.ema.emaServer.service.PermissionService;
import com.bill.ema.emaServer.service.Role2PermissionService;
import com.bill.ema.emaServer.service.RoleService;
import com.bill.ema.emaServer.service.User2RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao,Role> implements RoleService{
	
	@Autowired
	private User2RoleDao user2RoleDao;
	
	@Autowired
	private Role2PermissionDao role2PermissionDao;
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private User2RoleService user2RoleService;
	
	@Autowired
	private Role2PermissionService role2PermissionService;
		
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Role getByName(String name) {
		QueryWrapper<Role> query = new QueryWrapper<Role>();
		query.eq(TableCol.NAME, name);		
		return baseMapper.selectOne(query);
	}

	@Override
	public Set<Role> listByPermissionId(Integer permissionId) {
		QueryWrapper<Role2Permission> query1 = new QueryWrapper<Role2Permission>();
		query1.eq(TableCol.PERMISSION_ID,permissionId);
		Set<Role> list = new HashSet<Role>();
		List<Role2Permission> list1 = role2PermissionDao.selectList(query1);
		for(Role2Permission data:list1) {					
			list.add(baseMapper.selectById(data.getRoleId()));
		}
		return list;
	}

	@Override
	public Set<Role> listByPermissionName(String name) {
		QueryWrapper<Permission> query0 = new QueryWrapper<Permission>();
		query0.eq(TableCol.NAME, name);
		Permission perimission = permissionDao.selectOne(query0);
		
		QueryWrapper<Role2Permission> query1 = new QueryWrapper<Role2Permission>();
		query1.eq(TableCol.PERMISSION_ID,perimission.getId());
		
		Set<Role> list = new HashSet<Role>();
		List<Role2Permission> list1 = role2PermissionDao.selectList(query1);
		for(Role2Permission data:list1) {					
			list.add(baseMapper.selectById(data.getRoleId()));
		}
		return list;
	}

	@Override
	public Set<Role> listByUserId(Integer userId) {
		QueryWrapper<User2Role> query1 = new QueryWrapper<User2Role>();
		query1.eq(TableCol.USER_ID,userId);
		Set<Role> list = new HashSet<Role>();
		List<User2Role> list1 = user2RoleDao.selectList(query1);
		for(User2Role data:list1) {					
			list.add(baseMapper.selectById(data.getRoleId()));
		}
		return list;
	}

	@Override
	public Set<Role> listByUsername(String username) {
		QueryWrapper<User> query0 = new QueryWrapper<User>();
		query0.eq(TableCol.USERNAME, username);
		User user = userDao.selectOne(query0);
		
		QueryWrapper<User2Role> query1 = new QueryWrapper<User2Role>();
		query1.eq(TableCol.USER_ID,user.getId());
		
		Set<Role> list = new HashSet<Role>();
		List<User2Role> list1 = user2RoleDao.selectList(query1);
		for(User2Role data:list1) {					
			list.add(baseMapper.selectById(data.getRoleId()));
		}
		return list;
	}

	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<Role> page = new QueryUtil<Role>().getQueryPage(param);
		List<Role> list = baseMapper.selectForPage(page, param);
		page.setRecords(list);
		return new PageUtil(page);
	}
		
	@Override
	@Transactional(rollbackFor = Exception.class)
	public R edit(Map<String, Object> param) {
		Role role = new Role(param);
		if(this.updateById(role)) {
			return R.OK();
		}
		else
			return R.ERROR();
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public R create(Map<String, Object> param) {
		Role role = new Role(param);
		if(this.save(role)) {
			return R.OK();
		}
		else
			return R.ERROR();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Map<String, Object> param) {
		for(Object id :param.values()) {
			this.removeById(Integer.valueOf(id.toString()));			
			user2RoleService.removeByUserId(Integer.valueOf(id.toString()));
			role2PermissionService.removeByRoleId(Integer.valueOf(id.toString()));
		}
	}	
}
