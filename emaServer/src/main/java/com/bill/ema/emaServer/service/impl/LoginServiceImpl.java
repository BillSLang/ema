package com.bill.ema.emaServer.service.impl;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.response.STATUSCODE;
import com.bill.ema.emaModel.entity.User;
import com.bill.ema.emaServer.service.LoginService;
import com.bill.ema.emaServer.service.UserService;
import com.bill.ema.emaServer.service.shiro.ShiroUtil;
import com.google.code.kaptcha.Constants;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserService userService;
	
	@Override
	public R authentication(Map<String, Object> param) {
		// 校验验证码
		String kaptcha = ShiroUtil.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		Integer retry = ShiroUtil.getRetryNum();
		if (retry > 0 && !kaptcha.equals(param.get("captcha")))
			return R.ERROR(STATUSCODE.INVALIDCAPTCHA);
		Subject subject = SecurityUtils.getSubject();
		try {
			if (!subject.isAuthenticated()) {// subject是否已经登录（认证）
				UsernamePasswordToken token = new UsernamePasswordToken((String) param.get("username"),
						(String) param.get("password"));
				ShiroUtil.setRetryNum(++retry);
				subject.login(token);
			}
		} catch (UnknownAccountException e) {
			return R.ERROR(STATUSCODE.ACCOUNTNOTEXIST);
		} catch (IncorrectCredentialsException e) {
			return R.ERROR(STATUSCODE.ACCOUNTPASSWORDINCORRECT);
		} catch (LockedAccountException e) {
			return R.ERROR(STATUSCODE.ACCOUNTHASBEENLOCKED);
		} catch (DisabledAccountException e) {
			return R.ERROR(STATUSCODE.ACCOUNTHASBEENACTIVE);
		} catch (UnsupportedTokenException e) {
			return R.ERROR(STATUSCODE.CURRUSERNOTPERMISSION);
		} catch (AuthenticationException e) {
			return R.ERROR(STATUSCODE.ACCOUNTVALIDATEFAIL);
		}

		// 登录成功异步写日志
		if (subject.isAuthenticated()) {
			ShiroUtil.setRetryNum(0);
			// 写日志
		}
		return R.OK("登录成功");
	}

	@Override
	public R register(User user) {
		if(userService.getByUsername(user.getUsername())!=null) {
			return R.ERROR(STATUSCODE.USERNAMEEXIST);
		}
		String passwrod = ShiroUtil.sha256(user.getPassword(), user.getUsername());
		user.setPassword(passwrod);
		userService.save(user);
		System.out.println("202001081050测试注册功能1"+user);
		return R.OK("注册成功");
	}

	@Override
	public R verifyUseranme(String username) {
		if(userService.getByUsername(username)!=null)
			return R.ERROR(STATUSCODE.USERNAMEEXIST);
		System.out.println(username);
		return R.OK();
	}

	@Override
	public R verifyEmail(String email) {
		if(userService.getByEmail(email)!=null)
			return R.ERROR(STATUSCODE.EMAILEXIST);
		System.out.println(email);
		return R.OK();
	}

}
