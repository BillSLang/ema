package com.bill.ema.emaServer.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.CollectionUtil;
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.TableCol;
import com.bill.ema.emaCommon.util.TransformUtil;
import com.bill.ema.emaModel.dao.Role2PermissionDao;
import com.bill.ema.emaModel.entity.Permission;
import com.bill.ema.emaModel.entity.Role2Permission;
import com.bill.ema.emaServer.service.PermissionService;
import com.bill.ema.emaServer.service.Role2PermissionService;

@Service
public class Role2PermissionServiceImpl extends ServiceImpl<Role2PermissionDao,Role2Permission> implements Role2PermissionService{

	@Autowired
	private PermissionService permissionService;
	
	@Override
	public Boolean removeByRoleId(Integer roleId) {
		QueryWrapper<Role2Permission> query = new QueryWrapper<Role2Permission>();
		query.eq(TableCol.ROLE_ID, roleId);
		return this.remove(query);	
	}
	
	@Override
	public Boolean removeByPermissionId(Integer permissionId) {
		QueryWrapper<Role2Permission> query = new QueryWrapper<Role2Permission>();
		query.eq(TableCol.PERMISSION_ID, permissionId);
		return this.remove(query);	
	}

	@Override
	public Boolean removeByRPId(Integer rId,Integer pId) {
		QueryWrapper<Role2Permission> query = new QueryWrapper<Role2Permission>();
		query.and(i->i.eq(TableCol.PERMISSION_ID, pId).eq(TableCol.ROLE_ID, rId));
		return this.remove(query);	
	}

	@Override
	public List<Role2Permission> listByRoleId(Integer roleId) {
		QueryWrapper<Role2Permission> query = new QueryWrapper<Role2Permission>();
		query.eq(TableCol.ROLE_ID, roleId);
		return list(query);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public R saveOrUpdateByMap(Map<String,Object> param) {
		try {
			List<Integer> newIdList = TransformUtil.toIntegerList(param.get(Constant.PERMISSIONS).toString());
			Set<Permission> pSet = permissionService.listByRoleId(Integer.valueOf(param.get(Constant.ID).toString()));
			List<Integer> oldIdList =  TransformUtil.idList(pSet);
			System.out.println("202002292106n"+newIdList);
			System.out.println("202002292106o"+oldIdList);

			
			CollectionUtil.edit(newIdList, oldIdList);
			//旧的剩余的id需要删除，新的剩余的id需要添加
			//删除操作
			if(!oldIdList.isEmpty())

			for(Integer id : oldIdList) {
				//先Rid 再 pid
				removeByRPId(Integer.valueOf(param.get(Constant.ID).toString()),id);
			}
			//添加操作
			if(!newIdList.isEmpty())

			for(Integer id : newIdList) {
				Role2Permission r2p = new Role2Permission();
				r2p.setPermissionId(id);
				r2p.setRoleId(Integer.valueOf(param.get(Constant.ID).toString()));
				save(r2p);
			}
			
		}catch(NullPointerException e) {
			return R.ERROR();
		}
		return R.OK();
	}
}
