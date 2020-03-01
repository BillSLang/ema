package com.bill.ema.emaServer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaCommon.util.TransformUtil;
import com.bill.ema.emaModel.dao.RoleDao;
import com.bill.ema.emaModel.entity.Role;
import com.bill.ema.emaModel.entity.User;
import com.bill.ema.emaModel.vo.AuthorizationEditVo;
import com.bill.ema.emaModel.vo.AuthorizationListVo;
import com.bill.ema.emaServer.service.AuthorizationService;
import com.bill.ema.emaServer.service.PermissionService;
import com.bill.ema.emaServer.service.Role2PermissionService;
import com.bill.ema.emaServer.service.RoleService;
import com.bill.ema.emaServer.service.User2RoleService;
import com.bill.ema.emaServer.service.UserService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private Role2PermissionService role2PermissionService;
	
	@Autowired
	private User2RoleService user2RoleService;

	@Override
	public PageUtil queryPage(Map<String, Object> param) {

		IPage<Role> page1 = new QueryUtil<Role>().getQueryPage(param);
		IPage<AuthorizationListVo> page2 = new QueryUtil<AuthorizationListVo>().getQueryPage(param);

		List<Role> list = roleDao.selectForPage(page1, param);
		List<AuthorizationListVo> list2 = new ArrayList<AuthorizationListVo>();

		list.forEach(entity -> {
			AuthorizationListVo aVo = new AuthorizationListVo(entity);
			
			aVo.setUserNames(TransformUtil.usernameSet(userService.listByRoleId(entity.getId())));
			aVo.setPermissionNames(TransformUtil.nameSet(permissionService.listByRoleId(entity.getId())));
			if (param.get(Constant.USER_NAME) != null 
				|| param.get(Constant.PERMISSION_NAME) != null
				|| param.get(Constant.ROLE_NAME) != null) {
				
				if (param.get(Constant.USER_NAME) != "") {
					aVo.getUserNames().forEach(name -> {
						if (name.contains(param.get(Constant.USER_NAME).toString()))
							list2.add(aVo);
					});
				}
				else if (param.get(Constant.PERMISSION_NAME) != "") {
					aVo.getPermissionNames().forEach(name -> {
						if (name.contains(param.get(Constant.PERMISSION_NAME).toString()))
							list2.add(aVo);
					});
				} else if (param.get(Constant.ROLE_NAME) != "") {
					if (aVo.getRoleName().contains(param.get(Constant.ROLE_NAME).toString()))
						list2.add(aVo);
				}
			}
			if (param.get(Constant.ROLE_NAME) == null && param.get(Constant.PERMISSION_NAME) == null
					&& param.get(Constant.USER_NAME) == null)
				list2.add(aVo);
		});
		page2.setRecords(list2);
		return new PageUtil(page2);
	}


	@Override
	public AuthorizationEditVo getByRoleId(Integer id) {
		Role role = roleService.getById(id);
		List<Integer> userIds =  TransformUtil.idList(userService.listByRoleId(id));
		List<Integer> permissionIds =  TransformUtil.idList(permissionService.listByRoleId(id));
		AuthorizationEditVo aev = new AuthorizationEditVo(role);
		aev.setUserIds(userIds);
		aev.setPermissionIds(permissionIds);
		return aev;
	}


	@Override
	public R authorize(Map<String, Object> param) {
		System.out.println(param);
		user2RoleService.saveOrUpdateByMap(param);
		role2PermissionService.saveOrUpdateByMap(param);
		return R.OK();
	}
	
}
