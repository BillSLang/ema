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
import com.bill.ema.emaModel.dao.UriDao;
import com.bill.ema.emaModel.entity.Uri;
import com.bill.ema.emaServer.service.SiteService;
import com.bill.ema.emaServer.service.UriService;

@Service("UriService")
public class UriServiceImpl extends ServiceImpl<UriDao,Uri> implements UriService{

	@Autowired
	private SiteService siteService;
	
	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<Uri> page = new QueryUtil<Uri>().getQueryPage(param);
		List<Uri> list = baseMapper.selectForPage(page, param);
		for(Uri entity :list) {
			if(entity.getSiteId()!=null)
				entity.setSite(siteService.getById(entity.getSiteId()).getName());
		}
		return new PageUtil(page,list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		Uri Uri = new Uri(param);
		if (save(Uri))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		Uri Uri = new Uri(param);
		if (updateById(Uri))
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
