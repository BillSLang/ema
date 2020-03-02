package com.bill.ema.emaModel.entity;

import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bill.ema.emaCommon.util.Constant;

import lombok.Data;

@Data
@TableName(value="expire")
public class Expire {
	
	@TableId
	private Integer id;
	
	private String name;
	
	private Integer quantity;
	
	@TableField(value="unitId",exist=true)
	private Integer unitId;
	
	@TableField(exist=false)
	private String unit;
	
	private String description;
	
	public Expire() {
		
	}
	public Expire(Map<String,Object> param) {
		if(param.get(Constant.ID)!=null&&param.get(Constant.ID)!="")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());
		if(param.get(Constant.UNIT_ID)!=null&&param.get(Constant.UNIT_ID)!="")
			this.unitId = Integer.valueOf(param.get(Constant.UNIT_ID).toString());
		if(param.get(Constant.NAME)!=null&&param.get(Constant.NAME)!="")
			this.name = param.get(Constant.NAME).toString();
		if(param.get(Constant.QUANTITY)!=null&&param.get(Constant.QUANTITY)!="")
			this.quantity = Integer.valueOf(param.get(Constant.QUANTITY).toString());
		if(param.get(Constant.DESCRIPTION)!=null&&param.get(Constant.DESCRIPTION)!="")
			this.description = param.get(Constant.DESCRIPTION).toString();
	}

}
