package com.bill.ema.emaServer.service.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bill.ema.emaCommon.response.STATUSCODE;
import com.bill.ema.emaCommon.util.CONSTANT;
import com.bill.ema.emaModel.entity.Role;
import com.bill.ema.emaModel.entity.User;
import com.bill.ema.emaServer.service.RoleService;
import com.bill.ema.emaServer.service.UserService;

@Component
public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	/**
     * 资源-权限分配 ~ 授权 ~ 需要将分配给当前用户的权限列表塞给shiro的权限字段中去
     * @param principalCollection
     * @return
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		System.out.println(token);
		final String username = token.getUsername();
		final String password = String.valueOf(token.getPassword());
		
		User user = userService.getByUsername(username);
		if(user==null||CONSTANT.NO_ENABLED.equals(user.getEnabled())) {
			throw new UnknownAccountException(STATUSCODE.ACCOUNTNOTEXIST.getMsg());
		}
		
		if(CONSTANT.SUPER_ADMIN_NAME.equals(user.getUsername())) {
			boolean notAccess = true;
			Set<Role> roles = roleService.listByUsername(username);
			for(Role role:roles) {
				if(role.getPermissions().contains("LOGIN")) {
					notAccess = false;
					break;
				}
				
			}
			if(notAccess) {
				throw new UnsupportedTokenException(STATUSCODE.CURRUSERNOTPERMISSION.getMsg());
			}
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
		
		System.out.println("user:"+user);
		return info;
	}

}
