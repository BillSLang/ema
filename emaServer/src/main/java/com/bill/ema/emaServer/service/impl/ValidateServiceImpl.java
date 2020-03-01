package com.bill.ema.emaServer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.response.Statuscode;
import com.bill.ema.emaServer.service.PermissionService;
import com.bill.ema.emaServer.service.RoleService;
import com.bill.ema.emaServer.service.UserService;
import com.bill.ema.emaServer.service.ValidateService;

@Service
public class ValidateServiceImpl implements ValidateService{

	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public R verifyUseranme(String username) {
		if(userService.getByUsername(username)!=null)
			return R.ERROR(Statuscode.UserNameExist);
		return R.OK();
	}

	@Override
	public R verifyEmail(String email) {
		if(userService.getByEmail(email)!=null)
			return R.ERROR(Statuscode.EmailExist);
		return R.OK();
	}

	@Override
	public R verifyPhone(String phone) {
		if(userService.getByPhone(phone)!=null)
			return R.ERROR(Statuscode.PhoneExist);
		return R.OK();
	}

	@Override
	public R verifyRoleanme(String name) {
		if(roleService.getByName(name)!=null)
			return R.ERROR(Statuscode.RolenameExist);
		return R.OK();
	}

	@Override
	public R verifyPermissionname(String name) {
		if(permissionService.getByName(name)!=null)
			return R.ERROR(Statuscode.PermissionnameExist);
		return R.OK();
	}
}
