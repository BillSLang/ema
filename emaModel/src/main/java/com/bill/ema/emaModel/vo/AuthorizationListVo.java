package com.bill.ema.emaModel.vo;

import java.util.Set;

import com.bill.ema.emaModel.entity.Role;
import com.bill.ema.emaModel.entity.User;

import lombok.Data;

@Data
public class AuthorizationListVo {
	private Integer id;
	private String roleName;
	private Set<String> userNames;
	private Set<String> permissionNames;
	
	public AuthorizationListVo() {
		
	}
	
	public AuthorizationListVo(Role role) {
		this.id = role.getId();
		this.roleName = role.getName();
	}
}
