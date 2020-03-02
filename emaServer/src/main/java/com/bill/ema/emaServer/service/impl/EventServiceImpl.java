package com.bill.ema.emaServer.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaModel.dao.EventDao;
import com.bill.ema.emaModel.entity.Event;
import com.bill.ema.emaServer.service.EventService;
import com.bill.ema.emaServer.service.FoodBatchService;
import com.bill.ema.emaServer.service.FoodService;
import com.bill.ema.emaServer.service.SubjectService;
import com.bill.ema.emaServer.service.UriService;
import com.bill.ema.emaServer.service.UserService;
import com.bill.ema.emaServer.service.shiro.ShiroUtil;

@Service("EventService")
public class EventServiceImpl extends ServiceImpl<EventDao,Event> implements EventService{

	@Autowired
	private UserService userService;
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private FoodBatchService foodBatchService;
	
	@Autowired
	private UriService uriService;
	
	@Autowired
	private SubjectService subjectService;
	
	
	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<Event> page = new QueryUtil<Event>().getQueryPage(param);
		List<Event> list = baseMapper.selectForPage(page, param);
		for(Event entity:list) {
			if(entity.getAuditorId()!=null)
				entity.setAuditor(userService.getById(entity.getAuditorId()).getName());
			if(entity.getFoodbatchId()!=null)
				entity.setFoodbatch(foodService.getById(foodBatchService.getById(entity.getFoodbatchId()).getFoodId()).getName());
			if(entity.getUriId()!=null)
				entity.setUri(uriService.getById(entity.getUriId()).getName());
			if(entity.getReportorId()!=null)
				entity.setReportor(userService.getById(entity.getReportorId()).getName());
			if(entity.getAuditorId()!=null)
				entity.setAuditor(userService.getById(entity.getAuditorId()).getName());
			if(entity.getSubjectId()!=null)
				entity.setSubject(subjectService.getById(entity.getSubjectId()).getName());
		}
		return new PageUtil(page,list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		Event Event = new Event(param);
		System.out.println(ShiroUtil.getUserId());
		Event.setReportorId(ShiroUtil.getUserId());
		if (save(Event))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		Event Event = new Event(param);
		Event.setStatus(Constant.RE);
		if (updateById(Event))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean delete(Map<String, Object> param) {
		for (Object id : param.values()) {
			if(!this.removeById(Integer.valueOf(id.toString())))
				return false;
		}
		return true;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean pass(Map<String, Object> param) {
		System.out.println(ShiroUtil.getUserId());
		for (Object id : param.values()) {
			Event event = getById(Integer.valueOf(id.toString()));
			event.setStatus(Constant.PASS);
			event.setAuditorId(ShiroUtil.getUserId());

			event.setAuditTime(new Date());
			if(!updateById(event))
				return false;
		}
		return true;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean noPass(Map<String, Object> param) {
		System.out.println(ShiroUtil.getUserId());
		for (Object id : param.values()) {
			Event event = getById(Integer.valueOf(id.toString()));
			event.setStatus(Constant.NO_PASS);
			event.setAuditorId(ShiroUtil.getUserId());
			event.setAuditTime(new Date());

			if(!updateById(event))
				return false;
		}
		return true;
	}
}
