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
import com.bill.ema.emaModel.dao.User2RoleDao;
import com.bill.ema.emaModel.entity.User;
import com.bill.ema.emaModel.entity.User2Role;
import com.bill.ema.emaServer.service.User2RoleService;
import com.bill.ema.emaServer.service.UserService;

@Service
public class User2RoleServiceImpl extends ServiceImpl<User2RoleDao,User2Role> implements User2RoleService{

	@Autowired
	private User2RoleDao user2RoleDao;
	
	@Autowired
	private UserService userService;
	
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
	
	@Override
	public Boolean removeByURId(Integer uId,Integer rId) {
		QueryWrapper<User2Role> query = new QueryWrapper<User2Role>();
		query.and(i->i.eq(TableCol.USER_ID, uId).eq(TableCol.ROLE_ID, rId));
		return this.remove(query);	
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public R saveOrUpdateByMap(Map<String,Object> param) {
		try {
			List<Integer> newIdList = TransformUtil.toIntegerList(param.get(Constant.USERS).toString());
			Set<User> uSet = userService.listByRoleId(Integer.valueOf(param.get(Constant.ID).toString()));
			List<Integer> oldIdList =  TransformUtil.idList(uSet);
			System.out.println("202002292106n"+newIdList);
			System.out.println("202002292106o"+oldIdList);

			CollectionUtil.edit(newIdList, oldIdList);
			//旧的剩余的id需要删除，新的剩余的id需要添加
			//删除操作
			if(!oldIdList.isEmpty())
			for(Integer id : oldIdList) {
				//先uid 再rid
				removeByURId(id,Integer.valueOf(param.get(Constant.ID).toString()));
			}
			//添加操作
			if(!newIdList.isEmpty())
			for(Integer id : newIdList) {
				User2Role u2r = new User2Role();
				u2r.setUserId(id);
				u2r.setRoleId(Integer.valueOf(param.get(Constant.ID).toString()));
				save(u2r);
			}
			
		}catch(NullPointerException e) {
			return R.ERROR("返回的数据存在空值");
		}
		return R.OK();
	}

}
