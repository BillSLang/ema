package com.bill.ema.emaServer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.StoreMethodDao;
import com.bill.ema.emaModel.entity.StoreMethod;
import com.bill.ema.emaServer.service.StoreMethodService;


@Service
public class StoreMethodServiceImpl extends ServiceImpl<StoreMethodDao,StoreMethod> implements StoreMethodService{

}
