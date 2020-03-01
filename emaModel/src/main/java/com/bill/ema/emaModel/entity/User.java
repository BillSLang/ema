package com.bill.ema.emaModel.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bill.ema.emaCommon.root.UserAction;
import com.bill.ema.emaCommon.util.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@TableName(value = "user")
public class User implements UserAction{

	@TableId(value = "id")
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

	private Byte enabled = 0;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;

	public User() {
		
	}
	
	public User(Map<String, Object> param) {
		
			SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);
			if(param.get(Constant.ID)!=null&&param.get(Constant.ID)!="")
				this.id = Integer.valueOf(param.get(Constant.ID).toString());
			
			if(param.get(Constant.PASSWORD)!="")
				this.password = param.get(Constant.PASSWORD).toString();
			this.username = param.get(Constant.USER_NAME).toString();
			this.phone = param.get(Constant.PHONE).toString();
			this.email = param.get(Constant.EMAIL).toString();
			this.gender = param.get(Constant.GENDER).toString();
			this.name = param.get(Constant.NAME).toString();
			if(param.get(Constant.BIRTHDAY)!=null&&param.get(Constant.BIRTHDAY).toString()!="")
				try {
					this.birthday =  sdf.parse(param.get(Constant.BIRTHDAY).toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
