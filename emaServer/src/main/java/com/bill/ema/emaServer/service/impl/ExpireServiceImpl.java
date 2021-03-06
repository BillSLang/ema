package com.bill.ema.emaServer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaModel.dao.ExpireDao;
import com.bill.ema.emaModel.entity.Expire;
import com.bill.ema.emaServer.service.ExpireService;
import com.bill.ema.emaServer.service.UnitService;

@Service("expireServie")
public class ExpireServiceImpl extends ServiceImpl<ExpireDao,Expire> implements ExpireService{
	
	@Autowired
	private UnitService unitService;
	
	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<Expire> page = new QueryUtil<Expire>().getQueryPage(param);
		List<Expire> list = baseMapper.selectForPage(page, param);
		list.forEach(entity->{
			System.out.println("20200302"+entity);
			if(unitService.getById(entity.getUnitId())!=null)
				entity.setUnit(unitService.getById(entity.getUnitId()).getName());
		});
		return new PageUtil(page,list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		Expire Expire = new Expire(param);
		if (save(Expire))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		Expire Expire = new Expire(param);
		if (updateById(Expire))
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
}
