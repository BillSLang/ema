package com.bill.ema.emaModel.vo;

import java.util.Date;

import com.bill.ema.emaModel.entity.User;

import lombok.Data;

@Data
public class UserVo {
	private String username;
	private String name;
	private String phone;
	private String email;
	private String gender;
	private Date birthday;
	
	public UserVo(User user) {
		this.username = user.getUsername();
		this.name = user.getName();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.gender = user.getGender();
		this.birthday = user.getBirthday();
	}
}
