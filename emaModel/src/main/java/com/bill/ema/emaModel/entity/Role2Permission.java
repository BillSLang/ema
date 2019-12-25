package com.bill.ema.emaModel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="role2permission")
public class Role2Permission {
	
	@TableId(value="id")
	private Integer id;
	
	private Integer roleId;
	
	private Integer permissionId;
}
