package com.bill.ema.emaServer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.ExpireDao;
import com.bill.ema.emaModel.entity.Expire;
import com.bill.ema.emaServer.service.ExpireService;

@Service("expireServie")
public class ExpireServiceImpl extends ServiceImpl<ExpireDao,Expire> implements ExpireService{

}
