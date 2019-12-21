package com.bill.ema.emaModel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "food")
public class Food {
	@TableId(value="id")
	private Integer id;
	
	private String name;
	
	@TableField(value="foodtype",exist=true)
	private Integer foodTypeId;
	
	@TableField(value="productcode",exist=true)
	private Integer productCodeId;
	
	@TableField(value="brand",exist=true)
	private Integer brandId;
	
	@TableField(value="producer",exist=true)
	private Integer producerId;
	
	@TableField(value="storemethod",exist=true)
	private Integer storeMethodId;
	
	@TableField(value="taste",exist=true)
	private Integer tasteId;

	@TableField(value="expire",exist=true)
	private Integer expireId;
	
	@TableField(value="description",exist=true)
	private String description;
	
	
}
