package com.bill.ema.emaServer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.ProducerDao;
import com.bill.ema.emaModel.entity.Producer;
import com.bill.ema.emaServer.service.ProducerService;

@Service("producserService")
public class ProducerServiceImpl extends ServiceImpl<ProducerDao,Producer> implements ProducerService{

}
