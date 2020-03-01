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
import lombok.extern.slf4j.Slf4j;

@Data
@TableName(value = "user")
@Slf4j
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
		SimpleDateFormat date = new SimpleDateFormat(Constant.DATE_FORMAT);
		try {
			if (param.get(Constant.ID) == null)
				throw new NullPointerException();
			if(param.get(Constant.ID)!="")
				this.setId(Integer.valueOf(param.get(Constant.ID).toString()));
			this.setUsername(param.get(Constant.USER_NAME).toString());
			this.setPhone(param.get(Constant.PHONE).toString());
			this.setEmail(param.get(Constant.EMAIL).toString());
			this.setGender(param.get(Constant.GENDER).toString());
			this.setName(param.get(Constant.NAME).toString());
			this.setBirthday(date.parse(param.get(Constant.BIRTHDAY).toString()));
			this.setUpdateTime(new Date());
		} catch (ParseException e) {
			log.error("获取的数据的日期格式错误");
		} catch (NullPointerException e) {
			log.error("获取的数据存在空值");
		}
	}
}
