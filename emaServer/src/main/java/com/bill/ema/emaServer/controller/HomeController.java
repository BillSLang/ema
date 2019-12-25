package com.bill.ema.emaServer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "redirect:/login";
	}
}
