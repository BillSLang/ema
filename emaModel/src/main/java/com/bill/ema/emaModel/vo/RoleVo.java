package com.bill.ema.emaModel.vo;

import com.bill.ema.emaModel.entity.Role;

import lombok.Data;

@Data
public class RoleVo {
	private Integer id;
	private String name;
	private String description;
	
	public RoleVo(Role role) {
		this.id = role.getId();
		this.name = role.getName();
		this.description = role.getDescription();
	}

}
