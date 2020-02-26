package com.bill.ema.emaModel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value="user2role")
public class User2Role {
	
	@TableId
	private Integer id;
	
	private Integer userId;
	
	private Integer roleId;
}
