package com.bill.ema.emaModel.entity;

import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bill.ema.emaCommon.root.BaseAction;
import com.bill.ema.emaCommon.util.Constant;

import lombok.Data;

@Data
@TableName(value="permission")
public class Permission implements BaseAction{

	@TableId(value="id")
	private Integer id;
	
	private String name;
	
	private String description;
	
	public Permission() {
		
	}
	
	public Permission(Map<String, Object> param) {
		if(param.get(Constant.ID)!=null&&param.get(Constant.ID).toString()!="")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());
		if(param.get(Constant.NAME)!=null)
			this.name = param.get(Constant.NAME).toString();
		if(param.get(Constant.DESCRIPTION)!=null)
			this.description = param.get(Constant.DESCRIPTION).toString();
	}

}
