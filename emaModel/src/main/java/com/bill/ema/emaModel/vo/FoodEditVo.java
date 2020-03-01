package com.bill.ema.emaModel.vo;

import lombok.Data;

@Data
public class FoodEditVo {
	private Integer id;
	private String name;
	private Integer foodTypeId;
	private Integer productCodeId;
	private Integer brandId;
	private Integer producerId;
	private Integer storeMethodId;
	private Integer tasteId;
	private Integer expireId;
	private String description;
}
