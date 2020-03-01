package com.bill.ema.emaServer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.TasteDao;
import com.bill.ema.emaModel.entity.Taste;
import com.bill.ema.emaServer.service.TasteService;

@Service
public class TasteServiceImpl extends ServiceImpl<TasteDao,Taste> implements TasteService{

}
