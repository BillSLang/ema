package com.bill.ema.emaServer.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.Role2PermissionDao;
import com.bill.ema.emaModel.entity.Role2Permission;
import com.bill.ema.emaServer.service.Role2PermissionService;

@Service
public class Role2PermissionServiceImpl extends ServiceImpl<Role2PermissionDao,Role2Permission> implements Role2PermissionService{

}
