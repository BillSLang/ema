package com.bill.ema.emaModel.vo;

import java.util.List;

import com.bill.ema.emaModel.entity.Role;

import lombok.Data;

@Data
public class AuthorizationEditVo {
	private Integer id;
	private String roleName;
	private List<Integer> userIds;
	private List<Integer> permissionIds;
	
	public AuthorizationEditVo() {
		
	}
	
	public AuthorizationEditVo(Role role) {
		this.id = role.getId();
		this.roleName = role.getName();
	}
}
