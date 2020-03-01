package com.bill.ema.emaServer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaModel.entity.Permission;
import com.bill.ema.emaModel.vo.PermissionVo;
import com.bill.ema.emaServer.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {


	
	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/list")
	@ResponseBody
	public R list(@RequestParam @Validated Map<String, Object> param) {
		return R.OK(permissionService.queryPage(param));
	}
	
	@RequestMapping("/all")
	@ResponseBody
	public R all() {
		List<PermissionVo> list = new ArrayList<PermissionVo>();
		for(Permission permission :permissionService.list())
			list.add(new PermissionVo(permission));
		return R.OK(list);
	}

	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable @Validated Integer id) {
		PermissionVo permission = new PermissionVo(permissionService.getById(id));
		return R.OK(permission);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public R edit(@RequestParam @Validated Map<String, Object> param) {
		permissionService.edit(param);
		return R.OK();
	}

	@RequestMapping("/create")
	@ResponseBody
	public R create(@RequestParam @Validated Map<String, Object> param) {
		permissionService.create(param);
		return R.OK();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public R delete(@RequestParam @Validated Map<String, Object> param) {
		permissionService.delete(param);
		return R.OK();
	}
}
