package com.bill.ema.emaServer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.AddressDao;
import com.bill.ema.emaModel.entity.Address;
import com.bill.ema.emaServer.service.AddressService;
@Service
public class AddressServiceImpl extends ServiceImpl<AddressDao,Address> implements AddressService{
}
