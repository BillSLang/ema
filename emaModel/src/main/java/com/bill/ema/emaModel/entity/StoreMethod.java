package com.bill.ema.emaModel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="storemethod")
public class StoreMethod {
	@TableId
	private Integer id;

	private String name;
	
	private String description;
}
