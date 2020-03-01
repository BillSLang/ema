package com.bill.ema.emaServer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.ProductCodeDao;
import com.bill.ema.emaModel.entity.ProductCode;
import com.bill.ema.emaServer.service.ProductCodeService;

@Service
public class ProductCodeServiceImpl extends ServiceImpl<ProductCodeDao,ProductCode> implements ProductCodeService{

}
