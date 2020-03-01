package com.bill.ema.emaServer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.TransformUtil;
import com.bill.ema.emaModel.vo.RoleVo;
import com.bill.ema.emaServer.service.AuthorizationService;
import com.bill.ema.emaServer.service.RoleService;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthorizationService authorizationService;
	

	@RequestMapping("/list")
	@ResponseBody
	public R list(@RequestParam @Validated Map<String, Object> param) {
		return R.OK(authorizationService.queryPage(param));
	}
	
	@RequestMapping("/authorize")
	@ResponseBody
	public R authorize(@RequestParam @Validated Map<String, Object> param) {
		authorizationService.authorize(param);
		return R.OK();
	}

	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable @Validated Integer id) {
		return R.OK(authorizationService.getByRoleId(id));
	}

}
