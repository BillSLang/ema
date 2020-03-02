package com.bill.ema.emaModel.entity;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.TransformUtil;

import lombok.Data;

@Data
@TableName(value="foodbatch")
public class FoodBatch {
	
	@TableId(value="id")
	private Integer id;
	
	private String name;
	
	@TableField("foodId")
	private Integer foodId;
	
	@TableField(exist=false)
	private String food;
	
	@TableField("unitId")
	private Integer unitId;
	@TableField(exist=false)

	private String unit;
	
	private Date date;
	
	private Double price;
	
	private String description;
	
	public FoodBatch() {
		
	}
	public FoodBatch(Map<String,Object> param) {
		
		if(param.get(Constant.ID)!=null&&param.get(Constant.ID)!="")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());
		if(param.get(Constant.UNIT_ID)!=null&&param.get(Constant.UNIT_ID)!="")
			this.unitId = Integer.valueOf(param.get(Constant.UNIT_ID).toString());
		if(param.get(Constant.FOOD_ID)!=null&&param.get(Constant.FOOD_ID)!="")
			this.foodId = Integer.valueOf(param.get(Constant.FOOD_ID).toString());
		
		if(param.get(Constant.PRICE)!=null&&param.get(Constant.PRICE)!="")
			this.price = Double.valueOf(param.get(Constant.PRICE).toString());
		if(param.get(Constant.NAME)!=null&&param.get(Constant.NAME)!="")
			this.name = param.get(Constant.NAME).toString();
		
		if(param.get(Constant.UNIT)!=null&&param.get(Constant.UNIT)!="")
			this.unit = param.get(Constant.UNIT).toString();
		
		if(param.get(Constant.FOOD)!=null&&param.get(Constant.FOOD)!="")
			this.food = param.get(Constant.FOOD).toString();
		
		if(param.get(Constant.DATE)!=null&&param.get(Constant.DATE)!="")
			try {
				this.date = TransformUtil.toDate(param.get(Constant.DATE).toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(param.get(Constant.NAME)!=null&&param.get(Constant.NAME)!="")
			this.name = param.get(Constant.NAME).toString();
		if(param.get(Constant.DESCRIPTION)!=null&&param.get(Constant.DESCRIPTION)!="")
			this.description = param.get(Constant.DESCRIPTION).toString();
	}

}
