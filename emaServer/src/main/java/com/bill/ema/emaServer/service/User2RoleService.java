package com.bill.ema.emaServer.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaModel.entity.User2Role;


public interface User2RoleService extends IService<User2Role>{

	User2Role getByUserId(Integer id);
	
	Boolean removeByUserId(Integer id);
	Boolean removeByURId(Integer uId,Integer rId) ;
	R saveOrUpdateByMap(Map<String,Object> param) ;


}
