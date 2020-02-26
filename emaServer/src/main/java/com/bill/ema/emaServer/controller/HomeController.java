package com.bill.ema.emaServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = { "index.html", "/" })
	public String home() {
		return "index";
	}

	@RequestMapping("login.html")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("register.html")
	public String toRegister() {

		return "register";
	}

	@RequestMapping("{page}.html")
	public String page1(@PathVariable String page) {
		System.out.println(page);
		return page;
	}
	
	@RequestMapping("{page}.html/{id}")
	public String page2(@PathVariable String page,@PathVariable String id,Model model) {
		model.addAttribute("id",id);
		System.out.println("20200226测试");
		System.out.println(id);
		return page;
	}

}
