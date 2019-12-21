package com.bill.ema.emaModel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="productcode")
public class ProductCode {

	@TableId(value="id")
	private Integer id;
	
	private String name;
	
	private String description;
}
