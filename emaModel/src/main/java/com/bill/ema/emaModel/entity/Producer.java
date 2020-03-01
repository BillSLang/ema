package com.bill.ema.emaModel.entity;

import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bill.ema.emaCommon.util.Constant;

import lombok.Data;

@Data
@TableName(value="producer")
public class Producer {
	@TableId(value="id")
	private Integer id;
	
	private String name;
	
	private String license;
	
	@TableField("address")
	private Integer addressId = 9;
	
	@TableField(exist=false)
	private String address;
	
	private String description;
	
	public Producer() {
		
	}
	
	public Producer(Map<String, Object> param) {
		if(param.get(Constant.ID)!="")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());
		if(param.get(Constant.NAME)!="")
			this.name = param.get(Constant.NAME).toString();
		if(param.get(Constant.LICENSE)!="")
			this.license = param.get(Constant.LICENSE).toString();
		if(param.get(Constant.DESCRIPTION)!="")
			this.description = param.get(Constant.DESCRIPTION).toString();
		/*
		 * if(param.get(Constant.ADDRESS_ID)!="") this.addressId =
		 * Integer.valueOf(param.get(Constant.ADDRESS_ID).toString());
		 */
		
	}
}
