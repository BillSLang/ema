package com.bill.ema.emaServer.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
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
import com.bill.ema.emaServer.service.UserService;
import com.bill.ema.emaServer.service.shiro.ShiroUtil;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService{

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private User2RoleDao user2RoleDao;
	
	@Autowired
	private User2RoleService user2RoleService;
	
	@Autowired
	private Role2PermissionService role2PermissionService;
	
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
			list.add(baseMapper.selectById(data.getUserId()));
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
			list.add(baseMapper.selectById(data.getUserId()));
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
	
	public PageUtil queryPage(Map<String,Object> param){
		IPage<User> page = new QueryUtil<User>().getQueryPage(param);
		List<User> list = baseMapper.selectForPage(page, param);
		page.setRecords(list);
		return new PageUtil(page);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		User newUser = new User();
		Role role = roleService.getByName(Constant.NORMAL_USER);
		if(role==null) {
			role = new Role();
			role.setName(Constant.NORMAL_USER);
			roleService.save(role);
			boolean isExist = false;
			Set<Permission> permissions = permissionService.getByRoleName(role.getName());
			if(permissions.isEmpty()) {
				for(Permission entity:permissions) {
					if(entity.equals(Constant.PERMISSION_LOGIN)) {
						isExist = true;
					}
				}
			}
			if(!isExist) {
				Permission permission = new Permission();
				permission.setName(Constant.PERMISSION_LOGIN);
				permissionService.save(permission);
				Role2Permission r2p = new Role2Permission();
				r2p.setPermissionId(permission.getId());
				r2p.setRoleId(role.getId());
				role2PermissionService.save(r2p);
			}
		}
		newUser.setUsername((String)param.get(Constant.USER_NAME));
		newUser.setPassword(ShiroUtil.sha256((String)param.get(Constant.PASSWORD), newUser.getUsername()));
		newUser.setPhone((String)param.get(Constant.PHONE));
		newUser.setEmail((String)param.get(Constant.EMAIL));
		newUser.setGender((String)param.get(Constant.GENDER));
		newUser.setName((String)param.get(Constant.NAME));
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		try {
			newUser.setBirthday(date.parse((String) param.get(Constant.BIRTHDAY)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newUser.setCreateTime(new Date());
		this.save(newUser);
		User2Role u2r = new User2Role();
		
		u2r.setUserId(newUser.getId());
		u2r.setRoleId(role.getId());
		return user2RoleService.save(u2r);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		User user = new User();
		user.setId(Integer.valueOf((String) param.get(Constant.ID)));
		user.setUsername((String)param.get(Constant.USER_NAME));
		user.setPhone((String)param.get(Constant.PHONE));
		user.setEmail((String)param.get(Constant.EMAIL));
		user.setGender((String)param.get(Constant.GENDER));
		user.setName((String)param.get(Constant.NAME));
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		try {
			user.setBirthday(date.parse((String) param.get(Constant.BIRTHDAY)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setUpdateTime(new Date());
		
		return  this.updateById(user);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Map<String,Object> param) {
		for(Object id :param.values()) {
			this.removeById(Integer.valueOf(id.toString()));			
			user2RoleService.removeByUserId(Integer.valueOf(id.toString()));
		}
	}

	@Override
	public User getByPhone(String phone) {
		QueryWrapper<User> query = new QueryWrapper<User>();
		query.eq("phone", phone);		
		return baseMapper.selectOne(query);
	}
}
