package com.bill.ema.emaModel.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

@Data
public class Address {
	private Integer id;
	private String name;
	@TableField("parent")
	private Integer parentId;
}
