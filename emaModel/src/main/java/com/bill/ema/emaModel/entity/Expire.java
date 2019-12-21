package com.bill.ema.emaModel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="expire")
public class Expire {
	
	@TableId
	private Integer id;
	
	private String name;
	
	private Integer quantity;
	
	@TableField(value="unit",exist=true)
	private Integer unitId;
	
	private String description;

}
