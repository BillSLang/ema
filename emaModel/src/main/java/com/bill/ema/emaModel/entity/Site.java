package com.bill.ema.emaModel.entity;

import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bill.ema.emaCommon.util.Constant;

import lombok.Data;

@Data
@TableName(value="site")
public class Site {
	
	@TableId(value="id")
	private Integer id;
	
	private String name;
	
	private String domain;
	private String description;
	
	public Site() {
		
	}
	public Site(Map<String,Object> param) {
		if(param.get(Constant.ID)!=null&&param.get(Constant.ID)!="")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());
		if(param.get(Constant.NAME)!=null&&param.get(Constant.NAME)!="")
			this.name = param.get(Constant.NAME).toString();
		if(param.get(Constant.DOMAIN)!=null&&param.get(Constant.DOMAIN)!="")
			this.domain = param.get(Constant.DOMAIN).toString();
		if(param.get(Constant.DESCRIPTION)!=null&&param.get(Constant.DESCRIPTION)!="")
			this.description = param.get(Constant.DESCRIPTION).toString();
	}

}
