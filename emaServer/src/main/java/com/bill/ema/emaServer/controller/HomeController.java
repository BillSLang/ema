package com.bill.ema.emaServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
		
	@RequestMapping(value= {"index.html","/"})
	public String home() {
		return "index";
	}
	
	@RequestMapping("login.html")
    public String login() {
    	return "login";
    }
	
}
