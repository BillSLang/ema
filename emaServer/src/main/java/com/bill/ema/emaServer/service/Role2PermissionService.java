package com.bill.ema.emaServer.service;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaModel.entity.Role2Permission;

public interface Role2PermissionService extends IService<Role2Permission>{
	List<Role2Permission> listByRoleId(Integer roleId);
	Boolean removeByRoleId(Integer roleId);
	Boolean removeByPermissionId(Integer permissionId);
	Boolean removeByRPId(Integer rId,Integer pId);
	R saveOrUpdateByMap(Map<String,Object> param) ;
}
