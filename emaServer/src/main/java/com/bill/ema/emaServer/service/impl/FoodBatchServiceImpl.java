package com.bill.ema.emaServer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaModel.dao.FoodBatchDao;
import com.bill.ema.emaModel.entity.FoodBatch;
import com.bill.ema.emaServer.service.FoodBatchService;

@Service("FoodBatchService")
public class FoodBatchServiceImpl extends ServiceImpl<FoodBatchDao,FoodBatch> implements FoodBatchService{

	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<FoodBatch> page = new QueryUtil<FoodBatch>().getQueryPage(param);
		List<FoodBatch> list = baseMapper.selectForPage(page, param);
		return new PageUtil(page,list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		FoodBatch FoodBatch = new FoodBatch(param);
		if (save(FoodBatch))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		FoodBatch FoodBatch = new FoodBatch(param);
		if (updateById(FoodBatch))
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
