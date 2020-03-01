package com.bill.ema.emaServer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bill.ema.emaCommon.util.Constant;
import com.bill.ema.emaCommon.util.PageUtil;
import com.bill.ema.emaCommon.util.QueryUtil;
import com.bill.ema.emaModel.dao.FoodDao;
import com.bill.ema.emaModel.entity.Brand;
import com.bill.ema.emaModel.entity.Expire;
import com.bill.ema.emaModel.entity.Food;
import com.bill.ema.emaModel.entity.FoodType;
import com.bill.ema.emaModel.entity.Producer;
import com.bill.ema.emaModel.entity.ProductCode;
import com.bill.ema.emaModel.entity.StoreMethod;
import com.bill.ema.emaModel.entity.Taste;
import com.bill.ema.emaModel.vo.FoodListVo;
import com.bill.ema.emaServer.service.BrandService;
import com.bill.ema.emaServer.service.ExpireService;
import com.bill.ema.emaServer.service.FoodService;
import com.bill.ema.emaServer.service.FoodTypeService;
import com.bill.ema.emaServer.service.ProducerService;
import com.bill.ema.emaServer.service.ProductCodeService;
import com.bill.ema.emaServer.service.StoreMethodService;
import com.bill.ema.emaServer.service.TasteService;

@Service
public class FoodServiceImpl extends ServiceImpl<FoodDao, Food> implements FoodService {

	@Autowired
	private FoodTypeService foodTypeService;

	@Autowired
	private ProductCodeService productCodeService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProducerService prodcerService;

	@Autowired
	private StoreMethodService storeMethodService;

	@Autowired
	private TasteService tasteService;

	@Autowired
	private ExpireService expireService;

	@Override
	public PageUtil queryPage(Map<String, Object> param) {
		IPage<Food> page = new QueryUtil<Food>().getQueryPage(param);

		List<Food> list = baseMapper.selectForPage(page, param);
		List<FoodListVo> list2 = new ArrayList();
		for (Food entity : list) {
			FoodListVo foodVo = new FoodListVo();
			FoodType foodType = foodTypeService.getById(entity.getFoodTypeId());
			ProductCode productCode = productCodeService.getById(entity.getProductCodeId());
			Brand brand = brandService.getById(entity.getBrandId());
			Producer producer = prodcerService.getById(entity.getProducerId());
			StoreMethod storeMethod = storeMethodService.getById(entity.getStoreMethodId());
			Taste taste = tasteService.getById(entity.getTasteId());
			Expire expire = expireService.getById(entity.getExpireId());
			if (!(param.get(Constant.FOOD_TYPE) == null && param.get(Constant.NAME) == null
					&& param.get(Constant.PRODUCER) == null)) {

				if (param.get(Constant.NAME) != "" && !(entity.getName().contains(param.get(Constant.NAME).toString())))
					continue;
				if (param.get(Constant.FOOD_TYPE) != ""
						&& !(foodType.getName().contains(param.get(Constant.FOOD_TYPE).toString())))
					continue;
				if (param.get(Constant.PRODUCER) != ""
						&& !(producer.getName().contains(param.get(Constant.PRODUCER).toString())))
					continue;
			}
			if (foodType != null)
				foodVo.setFoodType(foodType.getName());
			if (productCode != null)
				foodVo.setProductCode(productCode.getName());
			if (brand != null)
				foodVo.setBrand(brand.getName());
			if (producer != null)
				foodVo.setProducer(producer.getName());
			if (storeMethod != null)
				foodVo.setStoreMethod(storeMethod.getName());
			if (taste != null)
				foodVo.setTaste(taste.getName());
			if (expire != null)
				foodVo.setExpire(expire.getName());
			foodVo.setId(entity.getId());
			foodVo.setName(entity.getName());
			foodVo.setDescription(entity.getDescription());
			list2.add(foodVo);
		}
		return new PageUtil(page, list2);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean create(Map<String, Object> param) {
		Food food = new Food(param);
		if (save(food))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean edit(Map<String, Object> param) {
		Food food = new Food(param);
		if (updateById(food))
			return true;
		else
			return false;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean delete(Map<String, Object> param) {
		for (Object id : param.values()) {
			if(!this.removeById(Integer.valueOf(id.toString())))
				return false;
		}
		return true;
	}

}
