package com.bill.ema.emaServer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaModel.dao.ProductCodeDao;
import com.bill.ema.emaModel.entity.ProductCode;
import com.bill.ema.emaServer.service.ProductCodeService;

@Service
public class ProductCodeServiceImpl extends ServiceImpl<ProductCodeDao,ProductCode> implements ProductCodeService{

	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<ProductCode> page = new QueryUtil<ProductCode>().getQueryPage(param);
		List<ProductCode> list = baseMapper.selectForPage(page, param);
		return new PageUtil(page,list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		ProductCode productCode = new ProductCode(param);
		if (save(productCode))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		ProductCode productCode = new ProductCode(param);
		if (updateById(productCode))
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
