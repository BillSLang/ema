package com.bill.ema.emaCommon.root;

import lombok.Data;

@Data
public abstract class BaseEntity implements BaseAction{
	private Integer id;
	private String name;
	private String description;
}
