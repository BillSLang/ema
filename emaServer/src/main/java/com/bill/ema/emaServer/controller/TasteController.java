package com.bill.ema.emaServer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.TableCol;
import com.bill.ema.emaModel.entity.Taste;
import com.bill.ema.emaServer.service.FoodService;
import com.bill.ema.emaServer.service.TasteService;

@RestController
@RequestMapping("/taste")
public class TasteController {

	@Autowired
	private FoodService foodService;
	@Autowired
	private TasteService tasteService;
	
	@RequestMapping("/list")
	public R list(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(foodService.queryPage(param));
	}
	@RequestMapping("/all")
	@ResponseBody
	public R all() {
		QueryWrapper<Taste> query = new QueryWrapper();
		query.ne(TableCol.NAME, "未知");
		return R.OK(tasteService.list(query));
	}
}
