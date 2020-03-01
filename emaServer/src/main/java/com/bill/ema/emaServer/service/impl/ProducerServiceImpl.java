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
import com.bill.ema.emaModel.dao.ProducerDao;
import com.bill.ema.emaModel.entity.Producer;
import com.bill.ema.emaServer.service.AddressService;
import com.bill.ema.emaServer.service.ProducerService;

@Service("producserService")
public class ProducerServiceImpl extends ServiceImpl<ProducerDao,Producer> implements ProducerService{

	@Autowired
	private AddressService addressService;
	
	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<Producer> page = new QueryUtil<Producer>().getQueryPage(param);
		List<Producer> list = baseMapper.selectForPage(page, param);
		for(Producer entity:list) {
			entity.setAddress(addressService.getById(entity.getAddressId()).getName());
		}
		return new PageUtil(page,list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		Producer producer = new Producer(param);
		if (save(producer))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		Producer producer = new Producer(param);
		if (updateById(producer))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean delete(Map<String, Object> param) {
		for (Object id : param.values()) {
			System.out.println(id);
			if(!this.removeById(Integer.valueOf(id.toString())))
				return false;
		}
		return true;
	}

}
