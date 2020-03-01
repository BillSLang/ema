package com.bill.ema.emaModel.entity;

import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bill.ema.emaCommon.util.Constant;

import lombok.Data;

@Data
@TableName("food")
public class Food {
	@TableId("id")
	private Integer id;

	private String name;

	@TableField("foodTypeId")
	private Integer foodTypeId;
	
	@TableField("productCodeId")
	private Integer productCodeId;
	
	@TableField("brandId")
	private Integer brandId;
	
	@TableField("producerId")
	private Integer producerId;
	
	@TableField("storeMethodId")
	private Integer storeMethodId;
	
	@TableField("tasteId")
	private Integer tasteId;
	
	@TableField("expireId")
	private Integer expireId;

	private String description;

	public Food() {

	}

	public Food(Map<String, Object> param) {
		if (param.get(Constant.ID) != "")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());
		if (param.get(Constant.NAME) != "")
			this.name = param.get(Constant.NAME).toString();
		if (param.get(Constant.DESCRIPTION) != "")
			this.description = param.get(Constant.DESCRIPTION).toString();
		if (param.get(Constant.FOOD_TYPE_ID) != "")
			this.foodTypeId = Integer.valueOf(param.get(Constant.FOOD_TYPE_ID).toString());
		if (param.get(Constant.PRODUCT_CODE_ID) != "")
			this.productCodeId = Integer.valueOf(param.get(Constant.PRODUCT_CODE_ID).toString());
		if (param.get(Constant.PRODUCER_ID) != "")
			this.producerId = Integer.valueOf(param.get(Constant.PRODUCER_ID).toString());
		if (param.get(Constant.STORE_METHOD_ID) != "")
			this.storeMethodId = Integer.valueOf(param.get(Constant.STORE_METHOD_ID).toString());
		if (param.get(Constant.TASTE_ID) != "")
			this.tasteId = Integer.valueOf(param.get(Constant.TASTE_ID).toString());
		if (param.get(Constant.EXPIRE_ID) != "")
			this.expireId = Integer.valueOf(param.get(Constant.EXPIRE_ID).toString());
		if (param.get(Constant.BRAND_ID) != "")
			this.brandId = Integer.valueOf(param.get(Constant.BRAND_ID).toString());
	}

}
