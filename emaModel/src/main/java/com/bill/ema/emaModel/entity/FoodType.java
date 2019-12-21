package com.bill.ema.emaModel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="foodtype")
public class FoodType {
	
	@TableId(value="id")
	private Integer id;
	
	private String name;
	
	@TableField(value="parent",exist=true)
	private Integer parentId;
	
	private String description;
}
