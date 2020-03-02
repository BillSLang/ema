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
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.TableCol;
import com.bill.ema.emaModel.entity.Event;
import com.bill.ema.emaServer.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	
	@Autowired
	private EventService EventService;
	
	@RequestMapping("/list")
	public R list(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(EventService.queryPage(param));
	}
	
	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable @Validated Integer id) {
		Event Event = EventService.getById(id);
		return R.OK(Event);
	}
	
	@RequestMapping("/create")
	public R create(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(EventService.create(param));
	}
	
	@RequestMapping("/edit")
	public R edit(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(EventService.edit(param));
	}
	
	@RequestMapping("/delete")
	public R delete(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(EventService.delete(param));
	}
	
	@RequestMapping("/pass")
	public R pass(@RequestParam Map<String,Object> param){
		return R.OK(EventService.pass(param));
	}
	
	@RequestMapping("/noPass")
	public R noPass(@RequestParam Map<String,Object> param){
		return R.OK(EventService.noPass(param));
	}
	
	
	@RequestMapping("/all")
	@ResponseBody
	public R all() {
		QueryWrapper<Event> query = new QueryWrapper();
		query.ne(TableCol.NAME, "未知");
		return R.OK(EventService.list(query));
	}
}
