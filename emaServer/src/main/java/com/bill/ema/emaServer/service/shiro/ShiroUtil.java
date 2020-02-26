package com.bill.ema.emaServer.service.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaModel.entity.User;

public class ShiroUtil {
	//加密算法
	public final static String hashAlgrithmName = "SHA-256";
	
	//循环次数
	public final static int hashInterations = 16;
	
	public static String sha256(String password,String salt) {
		return new SimpleHash(hashAlgrithmName,password,salt,hashInterations).toString();
	}
	
	//获取shiro session
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	//获取shiro subject
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	//获取shiro的真正主体
	public static User getUser() {
		return (User)SecurityUtils.getSubject().getPrincipal();
	}
	
	public static Integer getUserId() {
		return getUser().getId();
	}
	
	public static String getUserName() {
		return getUser().getUsername();
	}
	
	public static void setSessionAttribute(Object key,Object value) {
		getSession().setAttribute(key, value);
	}
	
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}
	
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() !=null;			
	}
	
	public void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	//获取验证码
	public static String getKaptcha(String key) {
		Object object = getSessionAttribute(key);
		if(object==null) {
			//throw new CommonException("验证码已失效！");
		}
		String newCode = object.toString();
		getSession().removeAttribute(key);
		System.out.println("新验证码"+newCode);
		return newCode;
	}
	
	//验证码错误次数
	public static Integer getRetryNum() {
		Object object = getSessionAttribute(Constant.RETRY_NUM);
		if(object==null)
			return 0;
		return (Integer)object;
	}
	
	public static void setRetryNum(int num) {
		setSessionAttribute(Constant.RETRY_NUM, num);
	}
}
