package com.bill.ema.emaServer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.ema.emaModel.entity.User2Role;


public interface User2RoleService extends IService<User2Role>{

	User2Role getByUserId(Integer id);
	
	Boolean removeByUserId(Integer id);

}
