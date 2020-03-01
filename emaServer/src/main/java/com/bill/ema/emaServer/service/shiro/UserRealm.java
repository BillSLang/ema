package com.bill.ema.emaServer.service.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.response.Statuscode;
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.TransformUtil;
import com.bill.ema.emaModel.entity.Permission;
import com.bill.ema.emaModel.entity.Role;
import com.bill.ema.emaModel.entity.User;
import com.bill.ema.emaServer.service.PermissionService;
import com.bill.ema.emaServer.service.RoleService;
import com.bill.ema.emaServer.service.UserService;

@Component
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	/**
	 * 
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	        System.out.println(ShiroUtil.getSessionAttribute(Constant.ROLES));
	        authorizationInfo.setRoles((Set<String>) ShiroUtil.getSessionAttribute(Constant.ROLES));
	        authorizationInfo.setStringPermissions((Set<String>) ShiroUtil.getSessionAttribute(Constant.PERMISSIONS));
	        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		final String username = token.getUsername();
		final String password = String.valueOf(token.getPassword());
		System.out.println("202002282054测试"+password);

		User user = userService.getByUsername(username);
		if (user == null || Constant.NO_ENABLED.equals(user.getEnabled())) {
			throw new UnknownAccountException(Statuscode.AccountNotExist.getMsg());
		}
		//如果是超级管理员
		System.out.println("202002282032测试"+user.getUsername());
		boolean notAccess = true;//true的时候不许链接
		if(Constant.SUPER_ADMIN.equals(user.getUsername()))
			notAccess = false;//超级用户允许登陆
		else {//普通用户验证是否有权限
			Set<Role> roles = roleService.listByUsername(username);
			System.out.println("202002282032测试"+roles);
			if(!roles.isEmpty())
			for (Role role : roles) {
				if (role != null) {
					Set<Permission> permissions = permissionService.listByRoleId(role.getId());
					System.out.println(permissions);
					if (permissions.isEmpty() == false) {//权限集合不为空，则查看是否有登陆权限
						for (Permission entity : permissions)
							if (entity.getName().equals(Constant.PERMISSION_LOGIN))
								notAccess = false;//如果有登陆的权限，则为false 运行登陆
						break;
					}
				}
			}
		}
		if (notAccess) {
			throw new UnsupportedTokenException(Statuscode.CurrUserNotPermission.getMsg());
		}
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
		System.out.println("user:" + user);
		return info;
	}

}
