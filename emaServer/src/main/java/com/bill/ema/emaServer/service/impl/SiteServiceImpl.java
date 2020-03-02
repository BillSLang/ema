package com.bill.ema.emaServer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaModel.dao.SiteDao;
import com.bill.ema.emaModel.entity.Site;
import com.bill.ema.emaServer.service.SiteService;

@Service("SiteService")
public class SiteServiceImpl extends ServiceImpl<SiteDao,Site> implements SiteService{

	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<Site> page = new QueryUtil<Site>().getQueryPage(param);
		List<Site> list = baseMapper.selectForPage(page, param);
		return new PageUtil(page,list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		Site Site = new Site(param);
		if (save(Site))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		Site Site = new Site(param);
		if (updateById(Site))
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
