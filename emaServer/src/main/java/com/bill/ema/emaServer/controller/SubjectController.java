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
import com.bill.ema.emaModel.entity.Subject;
import com.bill.ema.emaServer.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {

	
	@Autowired
	private SubjectService SubjectService;
	
	@RequestMapping("/list")
	public R list(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(SubjectService.queryPage(param));
	}
	
	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable @Validated Integer id) {
		Subject Subject = SubjectService.getById(id);
		return R.OK(Subject);
	}
	
	@RequestMapping("/create")
	public R create(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(SubjectService.create(param));
	}
	
	@RequestMapping("/edit")
	public R edit(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(SubjectService.edit(param));
	}
	
	@RequestMapping("/delete")
	public R delete(@RequestParam Map<String,Object> param){
		System.out.println(param);
		return R.OK(SubjectService.delete(param));
	}
	
	
	@RequestMapping("/all")
	@ResponseBody
	public R all() {
		QueryWrapper<Subject> query = new QueryWrapper();
		query.ne(TableCol.NAME, "未知");
		return R.OK(SubjectService.list(query));
	}
}
