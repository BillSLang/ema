package com.bill.ema.emaServer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaModel.dao.User2RoleDao;
import com.bill.ema.emaModel.entity.User2Role;
import com.bill.ema.emaServer.service.User2RoleService;

@Service
public class User2RoleServiceImpl extends ServiceImpl<User2RoleDao,User2Role> implements User2RoleService{

	@Autowired
	private User2RoleDao user2RoleDao;
	
	@Override
	public User2Role getByUserId(Integer id) {
		QueryWrapper<User2Role> query = new QueryWrapper();
		query.eq("user_id", id);
		return user2RoleDao.selectOne(query);
	}
	
	@Override
	public Boolean removeByUserId(Integer id) {
		QueryWrapper<User2Role> query = new QueryWrapper();
		query.eq("user_id", id);
		return this.remove(query);
	} 

}
