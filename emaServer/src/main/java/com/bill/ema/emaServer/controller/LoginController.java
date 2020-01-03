package com.bill.ema.emaServer.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bill.ema.emaModel.entity.User;
import com.bill.ema.emaServer.service.shiro.ShiroUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
public class LoginController {
	
	@Autowired
	private Producer producer;
	
	@RequestMapping("/login")
	public void login(@RequestBody @Validated User user) {
		System.out.println(user);
	}
	
	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-store,no-cache");
		response.setContentType("image/jpeg");
		
		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);		
		System.out.println(text);
		//保存到shiro session
		ShiroUtil.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY,text);
		
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image,"jpg",out);		
		System.out.println("验证码："+text);
	}

}
