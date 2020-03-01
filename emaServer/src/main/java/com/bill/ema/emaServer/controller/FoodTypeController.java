package com.bill.ema.emaServer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaModel.entity.FoodType;
import com.bill.ema.emaServer.service.FoodTypeService;

@RestController
@RequestMapping("/foodType")
public class FoodTypeController {

	@Autowired
	private FoodTypeService foodTypeService;
	
	@RequestMapping("/list")
	public R list(@RequestParam Map<String,Object> param){
		System.out.println(param);
		System.out.println(foodTypeService.queryPage(param));
		return R.OK(foodTypeService.queryPage(param));
	}
	
	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable @Validated Integer id) {
		FoodType foodType = foodTypeService.getById(id);
		return R.OK(foodType);
	}
	
	@RequestMapping("/create")
	public R create(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(foodTypeService.create(param));
	}
	
	@RequestMapping("/edit")
	public R edit(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(foodTypeService.edit(param));
	}
	
	@RequestMapping("/delete")
	public R delete(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(foodTypeService.delete(param));
	}
	
	@RequestMapping("/all")
	@ResponseBody
	public R all() {
		return R.OK(foodTypeService.list());
	}
}
