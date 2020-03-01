package com.bill.ema.emaServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaServer.service.ValidateService;

@RestController
@RequestMapping("/verify")
public class ValidateController {

	@Autowired
	private ValidateService validateService;
	
	@GetMapping("/isUsernameExist/{username}")
	public R isUsernameExist(@PathVariable @Validated String username) {
		System.out.println(username);
		return validateService.verifyUseranme(username);
	}
	
	@GetMapping("/isPermissionnameExist/{username}")
	public R isPermissionExist(@PathVariable @Validated String username) {
		System.out.println(username);
		return validateService.verifyPermissionname(username);
	}
	
	@GetMapping("/isRolenameExist/{rolename}")
	public R isRolenameExist(@PathVariable @Validated String rolename) {
		System.out.println(rolename);
		return validateService.verifyRoleanme(rolename);
	}
	
	@GetMapping("/isEmailExist/{email}")
	public R isEmailExist(@PathVariable @Validated String email) {
		System.out.println(email);
		return validateService.verifyEmail(email);
	}
	
	@GetMapping("/isPhoneExist/{phone}")
	public R isPhoneExist(@PathVariable @Validated String phone) {
		System.out.println(phone);
		return validateService.verifyPhone(phone);
	}
	
}
