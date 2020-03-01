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
import com.bill.ema.emaModel.vo.RoleVo;
import com.bill.ema.emaServer.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/list")
	@ResponseBody
	public R list(@RequestParam @Validated Map<String, Object> param) {
		return R.OK(roleService.queryPage(param));
	}

	@RequestMapping("/all")
	@ResponseBody
	public R all() {
		return R.OK(roleService.list());
	}
	
	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable @Validated Integer id) {
		RoleVo role = new RoleVo(roleService.getById(id));
		return R.OK(role);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public R edit(@RequestParam @Validated Map<String, Object> param) {
		System.out.println("20200226测试" + param);
		roleService.edit(param);
		return R.OK();
	}

	@RequestMapping("/create")
	@ResponseBody
	public R create(@RequestParam @Validated Map<String, Object> param) {
		System.out.println(param);
		roleService.create(param);
		return R.OK();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public R delete(@RequestParam @Validated Map<String, Object> param) {
		System.out.println(param);
		roleService.delete(param);
		return R.OK();
	}
}
