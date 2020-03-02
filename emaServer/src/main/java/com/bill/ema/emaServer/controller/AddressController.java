package com.bill.ema.emaServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bill.ema.emaCommon.response.R;
import com.bill.ema.emaCommon.util.TableCol;
import com.bill.ema.emaModel.entity.Address;
import com.bill.ema.emaServer.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	
	@RequestMapping("/all")
	@ResponseBody
	public R all() {
		QueryWrapper<Address> query = new QueryWrapper();
		query.ne(TableCol.NAME, "未知");
		return R.OK(addressService.list(query));
	}
	
}
