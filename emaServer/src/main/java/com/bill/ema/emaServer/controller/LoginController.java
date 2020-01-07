package com.bill.ema.emaServer.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaServer.service.LoginService;
import com.bill.ema.emaServer.service.shiro.ShiroUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@RestController
public class LoginController {
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public R login(@RequestParam @Validated Map<String,Object> param) {
		System.out.println(param);		
		return loginService.authentication(param); 
	}
	
	@PostMapping("/register")
	public R register(@RequestParam @Validated Map<String,Object> param) {
		System.out.println(param);		
		return loginService.authentication(param); 
	}
	
	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-store,no-cache");
		response.setContentType("image/jpeg");
		
		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);		
		//保存到shiro session
		ShiroUtil.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY,text);
		
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image,"jpg",out);		
	}
	
}
