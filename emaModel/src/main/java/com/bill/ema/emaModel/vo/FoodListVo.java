package com.bill.ema.emaModel.vo;


import lombok.Data;

@Data
public class FoodListVo {
	private Integer id;
	private String name;
	private String foodType;
	private String productCode;
	private String brand;
	private String producer;
	private String storeMethod;
	private String taste;
	private String expire;
	private String description;
}
