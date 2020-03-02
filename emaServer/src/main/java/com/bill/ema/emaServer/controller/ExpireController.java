package com.bill.ema.emaServer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.TableCol;
import com.bill.ema.emaModel.entity.Expire;
import com.bill.ema.emaServer.service.ExpireService;
import com.bill.ema.emaServer.service.FoodService;

@RestController
@RequestMapping("/expire")
public class ExpireController {
	
	@Autowired
	private ExpireService ExpireService;
	
	@RequestMapping("/list")
	public R list(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(ExpireService.queryPage(param));
	}
	
	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable @Validated Integer id) {
		Expire Expire = ExpireService.getById(id);
		return R.OK(Expire);
	}
	
	@RequestMapping("/create")
	public R create(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(ExpireService.create(param));
	}
	
	@RequestMapping("/edit")
	public R edit(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(ExpireService.edit(param));
	}
	
	@RequestMapping("/delete")
	public R delete(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(ExpireService.delete(param));
	}
	
	
	@RequestMapping("/all")
	@ResponseBody
	public R all() {
		QueryWrapper<Expire> query = new QueryWrapper();
		query.ne(TableCol.NAME, "未知");
		return R.OK(ExpireService.list(query));
	}
}
