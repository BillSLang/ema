package com.bill.ema.emaServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

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
		return page;
	}
	
	@RequestMapping("{parent}/{page}.html")
	public String page3(@PathVariable String parent,@PathVariable String page) {
		return parent+"/"+page;
	}
	
	@RequestMapping("{parent}/{page}.html/{id}")
	public String page4(@PathVariable String parent,@PathVariable String page,@PathVariable String id,Model model) {
		model.addAttribute("id",id);
		return parent+"/"+page;
	}
	
	@RequestMapping("{page}.html/{id}")
	public String page2(@PathVariable String page,@PathVariable String id,Model model) {
		model.addAttribute("id",id);
		return page;
	}

}
