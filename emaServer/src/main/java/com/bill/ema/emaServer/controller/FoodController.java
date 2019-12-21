package com.bill.ema.emaServer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bill.ema.emaModel.entity.Food;
import com.bill.ema.emaServer.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@RequestMapping("/list")
	public void list(@RequestParam Map<String,Object> params){
	    Food food = foodService.getById(1);
		System.out.println(food);
	}
}
