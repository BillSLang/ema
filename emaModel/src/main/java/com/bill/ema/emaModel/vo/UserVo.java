package com.bill.ema.emaModel.vo;

import java.text.SimpleDateFormat;
import java.util.Set;

import com.bill.ema.emaCommon.root.UserAction;
import com.bill.ema.emaCommon.root.UserEntity;
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaModel.entity.User;

import lombok.Data;

@Data
public class UserVo extends UserEntity implements UserAction{
	private Integer id;
	private String username;
	private String name;
	private String phone;
	private String email;
	private String gender;
	private String birthday;
	private Set<String> roleNames;
	private Byte enabled;
	private String createTime;
	private String updaetTime;

	public UserVo(User user) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);
		if (user.getId() != null)
			this.id = user.getId();
		this.username = user.getUsername();
		this.name = user.getName();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.gender = user.getGender();
		this.enabled = user.getEnabled();
		if (user.getBirthday() != null)
			this.birthday = sdf.format(user.getBirthday());
		if (user.getCreateTime() != null)
			this.createTime = sdf.format(user.getCreateTime());
		if (user.getUpdateTime() != null)
			this.updaetTime = sdf.format(user.getUpdateTime());
	}
}
