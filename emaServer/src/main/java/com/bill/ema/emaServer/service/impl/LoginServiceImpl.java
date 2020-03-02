package com.bill.ema.emaServer.service.impl;

import java.util.Date;
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

import com.bill.ema.emaCommon.exception.NullCaptchaException;
import com.bill.ema.emaCommon.exception.NullPasswordException;
import com.bill.ema.emaCommon.exception.NullUsernameException;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.response.Statuscode;
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.TransformUtil;
import com.bill.ema.emaServer.service.LoginService;
import com.bill.ema.emaServer.service.PermissionService;
import com.bill.ema.emaServer.service.RoleService;
import com.bill.ema.emaServer.service.UserService;
import com.bill.ema.emaServer.service.shiro.ShiroUtil;
import com.google.code.kaptcha.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Override
	public R authentication(Map<String, Object> param) {
		// 校验验证码
		String kaptcha = ShiroUtil.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		Integer retry = ShiroUtil.getRetryNum();
		Subject subject = SecurityUtils.getSubject();
		//验证用户名、密码、验证码是否空
		try {
			if(param.get(Constant.CAPTCHA)==null)
				throw new NullCaptchaException();
			if(param.get(Constant.USER_NAME)==null)
				throw new NullUsernameException();
			if(param.get(Constant.PASSWORD)==null)
				throw new NullPasswordException();	
			if (!(kaptcha.equals(param.get(Constant.CAPTCHA).toString())))
				return R.ERROR(Statuscode.InvalidCaptcha);
			System.out.println(subject.isAuthenticated());
			if (!subject.isAuthenticated()) {// subject是否已经登录（认证）
				UsernamePasswordToken token = new UsernamePasswordToken(param.get(Constant.USER_NAME).toString(),
						 param.get(Constant.PASSWORD).toString());
				//ShiroUtil.setRetryNum(++retry);
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
		}catch(NullCaptchaException e) {
			return R.ERROR(Statuscode.CaptchaIsNull);
		}catch(NullUsernameException e) {
			return R.ERROR(Statuscode.UsernameIsNull);
		}catch(NullPasswordException e) {
			return R.ERROR(Statuscode.PasswordIsNull);
		}

		// 登录成功异步写日志
		if (subject.isAuthenticated()) {
			Integer id = userService.getByUsername(param.get(Constant.USER_NAME).toString()).getId();
			ShiroUtil.setSessionAttribute(Constant.ROLES, TransformUtil.nameSet(roleService.listByUserId(id)));
			ShiroUtil.setSessionAttribute(Constant.PERMISSIONS, TransformUtil.nameSet(permissionService.listByUserId(id)));
			ShiroUtil.setSessionAttribute(Constant.USER,userService.getById(id));
			log.info(new Date()+"   用户"+param.get(Constant.USER_NAME).toString()+"登陆成功。");
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
		return R.OK(Constant.MESSAGE_REGISTER_SUCCESS);
	}
}
