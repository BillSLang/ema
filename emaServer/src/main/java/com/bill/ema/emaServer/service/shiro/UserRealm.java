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

import com.bill.ema.emaCommon.response.Statuscode;
import com.bill.ema.emaCommon.util.Constant;
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
	 * 璧勬簮-鏉冮檺鍒嗛厤 ~ 鎺堟潈 ~ 闇�瑕佸皢鍒嗛厤缁欏綋鍓嶇敤鎴风殑鏉冮檺鍒楄〃濉炵粰shiro鐨勬潈闄愬瓧娈典腑鍘�
	 * 
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		System.out.println(token);
		final String username = token.getUsername();
		final String password = String.valueOf(token.getPassword());

		User user = userService.getByUsername(username);
		if (user == null || Constant.NO_ENABLED.equals(user.getEnabled())) {
			throw new UnknownAccountException(Statuscode.AccountNotExist.getMsg());
		}

		if (Constant.SUPER_ADMIN.equals(user.getUsername())) {
			boolean notAccess = true;
			Set<Role> roles = roleService.listByUsername(username);
			for (Role role : roles) {
				if (role != null) {
					Set<Permission> permissions = permissionService.getByRoleId(role.getId());
					if (permissions.isEmpty() == false) {
						for (Permission entity : permissions)
							if (entity.getName().equals(Constant.PERMISSION_LOGIN))
								notAccess = false;
						break;
					}
				}
			}
			if (notAccess) {
				throw new UnsupportedTokenException(Statuscode.CurrUserNotPermission.getMsg());
			}
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());

		System.out.println("user:" + user);
		return info;
	}

}
