package com.bill.ema.emaServer.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bill.ema.emaServer.service.shiro.UserRealm;


@Configuration
public class ShiroConfig {
	
	//安全器管理（管理所有subject）
	@Bean
	public SecurityManager securityManager(UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm);
		securityManager.setRememberMeManager(null);
		securityManager.setSessionManager(sessionManager());		
		return securityManager;
	}
	
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(1 * 60 * 60 * 1000);//session1小时过期
		return sessionManager;
	}
	
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);				
		
		//设定用户没有登录认证时的跳转链接、没有授权时的跳转链接
		shiroFilter.setLoginUrl("/login.html");
		//shiroFilter.setSuccessUrl("/index");
		shiroFilter.setUnauthorizedUrl("login");
		
		//过滤器链配置
		Map<String,String> filterMap = new LinkedHashMap<>();
		
		//退出 logout地址，shiro去清楚session
		filterMap.put("/logout", "logout");
		
		//不需要拦截的访问
		filterMap.put("/login.html", "anon");
		filterMap.put("/login", "anon");
		filterMap.put("/verify", "anon");
		filterMap.put("/css/**", "anon");
		filterMap.put("/lib/**", "anon");		
		filterMap.put("/js/login/**", "anon");
		filterMap.put("/js/common.js", "anon");
		filterMap.put("/captcha.jpg", "anon");
		filterMap.put("/**", "authc");		
		
		shiroFilter.setFilterChainDefinitionMap(filterMap);

		Map<String,Filter> customFilterMap = new HashMap();
		System.out.println("拦截器启动成功！");
		//map里面key值要为authc才能使用自定义的过滤器
		//customFilterMap.put("authc", new SessionCheckFilter());
		//shiroFilter.setFilters(customFiltermap)
		return shiroFilter;		
	}
	
	//关于Shiro的bean生命周期的管理
	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifcycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
