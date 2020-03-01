package com.bill.ema.emaModel.vo;

import com.bill.ema.emaModel.entity.Permission;

import lombok.Data;
@Data
public class PermissionVo {
	private Integer id;
	private String name;
	private String description;
	
	public PermissionVo() {
		
	}
	
	public PermissionVo(Permission pe) {
		if(pe.getId()!=null)
			this.id = pe.getId();
		if(pe.getName()!=null)
			this.name = pe.getName();
		if(pe.getDescription()!=null)
			this.description = pe.getDescription();
	}

}
