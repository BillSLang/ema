package com.bill.ema.emaModel.entity;

import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableId;
import com.bill.ema.emaCommon.util.Constant;

import lombok.Data;

@Data
public class Unit {

	@TableId
	private Integer id;
	
	private String name;
	
	private String type;
	
	private String description;
	
	public Unit() {
		
	}
	public Unit(Map<String,Object> param) {
		if(param.get(Constant.ID)!=null&&param.get(Constant.ID)!="")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());
		if(param.get(Constant.NAME)!=null&&param.get(Constant.NAME)!="")
			this.name = param.get(Constant.NAME).toString();
		if(param.get(Constant.DESCRIPTION)!=null&&param.get(Constant.DESCRIPTION)!="")
			this.description = param.get(Constant.DESCRIPTION).toString();
	}

	
}
