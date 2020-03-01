package com.bill.ema.emaServer.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaCommon.util.TableCol;
import com.bill.ema.emaModel.dao.PermissionDao;
import com.bill.ema.emaModel.dao.Role2PermissionDao;
import com.bill.ema.emaModel.dao.User2RoleDao;
import com.bill.ema.emaModel.entity.Permission;
import com.bill.ema.emaModel.entity.Role;
import com.bill.ema.emaModel.entity.Role2Permission;
import com.bill.ema.emaModel.entity.User2Role;
import com.bill.ema.emaServer.service.PermissionService;
import com.bill.ema.emaServer.service.Role2PermissionService;
import com.bill.ema.emaServer.service.RoleService;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDao,Permission> implements PermissionService{

	@Autowired
	private Role2PermissionDao role2PermissionDao;
	
	@Autowired
	private User2RoleDao user2RoleDao;
		
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private Role2PermissionService role2PermissionService;
		

	@Override
	public Permission getByName(String name) {
		QueryWrapper<Permission> query = new QueryWrapper<Permission>();
		query.eq(TableCol.NAME,name);
		return baseMapper.selectOne(query);
	}
	
	@Override
	public Set<Permission> listByRoleId(Integer roleId) {
		QueryWrapper<Role2Permission> query = new QueryWrapper<Role2Permission>();
		query.eq(TableCol.ROLE_ID, roleId);
		Set<Permission> set = new HashSet<Permission>();
		List<Role2Permission> r2ps = role2PermissionDao.selectList(query);
		for(Role2Permission r2p:r2ps)
			set.add(getById(r2p.getPermissionId()));
		return set;
	}
	
	@Override
	public Set<Permission> listByRoleName(String roleName) {
		
		Role role = roleService.getByName(roleName);
		List<Role2Permission> role2PermissionList =  role2PermissionService.listByRoleId(role.getId());
		Set<Permission> set = new HashSet<Permission>();
		for(Role2Permission data:role2PermissionList) {				
			set.add(getById(data.getPermissionId()));
		}
		return set;
	}
	

	@Override
	public Set<Permission> listByUserId(Integer userId) {
		QueryWrapper<User2Role> query = new QueryWrapper<User2Role>();
		query.eq(TableCol.USER_ID, userId);
		Set<Permission> set = new HashSet<Permission>();
		List<User2Role> u2rs = user2RoleDao.selectList(query);
		for(User2Role u2r:u2rs) {
			set.addAll(listByRoleId(u2r.getRoleId()));
		}
		return set;
	}

	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<Permission> page = new QueryUtil<Permission>().getQueryPage(param);
		List<Permission> list = baseMapper.selectForPage(page, param);
		page.setRecords(list);
		return new PageUtil(page);
	}

	@Override
	public R edit(Map<String, Object> param) {
		Permission permission = new Permission(param);
		if(this.updateById(permission)) {
			return R.OK();
		}
		else
			return R.ERROR();
	}

	@Override
	public R create(Map<String, Object> param) {
		Permission permission = new Permission(param);
		if(this.save(permission)) {
			return R.OK();
		}
		else
			return R.ERROR();
	}

	@Override
	public void delete(Map<String, Object> param) {
		for(Object id :param.values()) {
			this.removeById(Integer.valueOf(id.toString()));			
			role2PermissionService.removeByRoleId(Integer.valueOf(id.toString()));
		}		
	}

}
