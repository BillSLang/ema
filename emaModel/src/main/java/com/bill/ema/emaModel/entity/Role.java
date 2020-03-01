package com.bill.ema.emaModel.entity;

import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bill.ema.emaCommon.root.BaseAction;
import com.bill.ema.emaCommon.util.Constant;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@TableName(value="role")
@Slf4j
public class Role implements BaseAction{
	
	@TableId(value="id")
	private Integer id;
	private String name;	
	private String description;
	/*
	 * private Set<Permission> permissions;
	 */
	public Role() {
		
	}
	public Role(Map<String,Object> param) {
		try {
			if(param.get(Constant.ID)==null)
				throw new NullPointerException();
			if(param.get(Constant.ID)!="")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());
			this.name = param.get(Constant.NAME).toString();
			this.description = param.get(Constant.DESCRIPTION).toString();
		}catch(NullPointerException e) {
			log.error("数据存在空值");
		}
	}
	
}
