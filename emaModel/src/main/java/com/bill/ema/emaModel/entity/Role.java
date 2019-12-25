package com.bill.ema.emaModel.entity;

import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="role")
public class Role {
	
	@TableId(value="id")
	private Integer id;
	private String name;	
	private String description;
	private Set<Permission> permissions;
}
