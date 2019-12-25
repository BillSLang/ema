package com.bill.ema.emaServer.service.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bill.ema.emaModel.entity.User;
import com.bill.ema.emaServer.service.UserService;

@Component
public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	/**
     * 资源-权限分配 ~ 授权 ~ 需要将分配给当前用户的权限列表塞给shiro的权限字段中去
     * @param principalCollection
     * @return
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println(principals);
		User user = (User)principals.getPrimaryPrincipal();
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}
