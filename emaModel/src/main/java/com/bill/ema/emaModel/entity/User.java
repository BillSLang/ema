package com.bill.ema.emaModel.entity;

import java.util.Date;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName(value="user")
public class User {

	@TableId(value="id")
	private Integer id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	private String firstname;
	private String lastname;
	private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date birthday;
    
    private String phone;
    
    private String email;
    
    private String qq;
    
    private Byte enabled;	
}
