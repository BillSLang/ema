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
import com.bill.ema.emaModel.entity.FoodBatch;
import com.bill.ema.emaServer.service.FoodBatchService;

@RestController
@RequestMapping("/foodbatch")
public class FoodBatchController {

	
	@Autowired
	private FoodBatchService FoodBatchService;
	
	@RequestMapping("/list")
	public R list(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(FoodBatchService.queryPage(param));
	}
	
	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable @Validated Integer id) {
		FoodBatch FoodBatch = FoodBatchService.getById(id);
		return R.OK(FoodBatch);
	}
	
	@RequestMapping("/create")
	public R create(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(FoodBatchService.create(param));
	}
	
	@RequestMapping("/edit")
	public R edit(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(FoodBatchService.edit(param));
	}
	
	@RequestMapping("/delete")
	public R delete(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(FoodBatchService.delete(param));
	}
	
	
	@RequestMapping("/all")
	@ResponseBody
	public R all() {
		QueryWrapper<FoodBatch> query = new QueryWrapper();
		query.ne(TableCol.NAME, "未知");
		return R.OK(FoodBatchService.list(query));
	}
}
