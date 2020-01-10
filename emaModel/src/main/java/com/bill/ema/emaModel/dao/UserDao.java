package com.bill.ema.emaModel.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bill.ema.emaModel.entity.User;

public interface UserDao extends BaseMapper<User>{
	
	List<User> selectForPage(IPage<User> page,@Param("param") Map<String,Object> param);
}
