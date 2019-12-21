package com.bill.ema.emaServer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.BrandDao;
import com.bill.ema.emaModel.entity.Brand;
import com.bill.ema.emaServer.service.BrandService;

@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao,Brand> implements BrandService{

}
