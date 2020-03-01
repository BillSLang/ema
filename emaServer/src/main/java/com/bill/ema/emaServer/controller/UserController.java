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
import com.bill.ema.emaModel.entity.User;
import com.bill.ema.emaModel.vo.UserVo;
import com.bill.ema.emaServer.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/list")
	@ResponseBody
	public R list(@RequestParam @Validated Map<String,Object> param) {
		return R.OK(userService.queryPage(param)); 
	}
	
	@RequestMapping("/all")
	@ResponseBody
	public R all() {
		List<UserVo> list = new ArrayList<UserVo>();
		for(User user :userService.list())
			list.add(new UserVo(user));
		return R.OK(list);
	}
	
	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable @Validated Integer id) {
		UserVo user = new UserVo(userService.getById(id));
		return R.OK(user);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public R edit(@RequestParam @Validated Map<String,Object> param) {
		userService.edit(param);
		return R.OK();
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public R create(@RequestParam @Validated Map<String,Object> param) {
		System.out.println(param);
		userService.create(param);
		return R.OK();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public R delete(@RequestParam  @Validated Map<String,Object> param) {
		System.out.println(param);
		userService.delete(param);
		return R.OK();
	}
}
