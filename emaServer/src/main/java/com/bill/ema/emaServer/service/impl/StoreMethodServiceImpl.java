package com.bill.ema.emaServer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaModel.dao.StoreMethodDao;
import com.bill.ema.emaModel.entity.StoreMethod;
import com.bill.ema.emaServer.service.StoreMethodService;


@Service
public class StoreMethodServiceImpl extends ServiceImpl<StoreMethodDao,StoreMethod> implements StoreMethodService{

	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<StoreMethod> page = new QueryUtil<StoreMethod>().getQueryPage(param);
		List<StoreMethod> list = baseMapper.selectForPage(page, param);
		return new PageUtil(page,list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		StoreMethod storeMethod = new StoreMethod(param);
		if (save(storeMethod))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		StoreMethod storeMethod = new StoreMethod(param);
		if (updateById(storeMethod))
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
