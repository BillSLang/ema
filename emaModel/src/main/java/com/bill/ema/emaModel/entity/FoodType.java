package com.bill.ema.emaModel.entity;

import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bill.ema.emaCommon.util.Constant;

import lombok.Data;

@Data
@TableName(value="foodtype")
public class FoodType {
	
	public FoodType(Map<String, Object> param) {
		if(param.get(Constant.ID)!="")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());
		if(param.get(Constant.NAME)!="")
			this.name = param.get(Constant.NAME).toString();
		if(param.get(Constant.DESCRIPTION)!="")
			this.description = param.get(Constant.DESCRIPTION).toString();
		if(param.get(Constant.PARENT_ID)!="")
			this.parentId = Integer.valueOf(param.get(Constant.PARENT_ID).toString());
		
	}

	public FoodType() {
		
	}
	@TableId(value="id")
	private Integer id;
	
	private String name;
	
	@TableField(value="parentId",exist=true)
	private Integer parentId;
	
	private String description;
}
