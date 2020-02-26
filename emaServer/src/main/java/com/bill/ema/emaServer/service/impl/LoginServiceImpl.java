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
import com.bill.ema.emaCommon.response.Statuscode;
import com.bill.ema.emaCommon.util.Constant;
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
		if (retry > 0||!kaptcha.equals((String)param.get("captcha")))
			return R.ERROR(Statuscode.InvalidCaptcha);
		Subject subject = SecurityUtils.getSubject();
		try {
			if (!subject.isAuthenticated()) {// subject是否已经登录（认证）
				UsernamePasswordToken token = new UsernamePasswordToken((String) param.get("username"),
						(String) param.get("password"));
				ShiroUtil.setRetryNum(++retry);
				subject.login(token);
			}
		} catch (UnknownAccountException e) {
			return R.ERROR(Statuscode.AccountNotExist);
		} catch (IncorrectCredentialsException e) {
			return R.ERROR(Statuscode.AccountPasswordIncorrect);
		} catch (LockedAccountException e) {
			return R.ERROR(Statuscode.AccountHasBeenLocked);
		} catch (DisabledAccountException e) {
			return R.ERROR(Statuscode.AccountHasBeenActive);
		} catch (UnsupportedTokenException e) {
			return R.ERROR(Statuscode.CurrUserNotPermission);
		} catch (AuthenticationException e) {
			return R.ERROR(Statuscode.AccountValidatedFail);
		}

		// 登录成功异步写日志
		if (subject.isAuthenticated()) {
			ShiroUtil.setRetryNum(0);
			// 写日志
		}
		return R.OK("登录成功");
	}
	
	@Override
	public R register(Map<String,Object> param) {
		if(userService.getByUsername((String)param.get(Constant.USER_NAME))!=null) {
			return R.ERROR(Statuscode.UserNameExist);
		}
		userService.create(param);
		return R.OK("注册成功");
	}
}
