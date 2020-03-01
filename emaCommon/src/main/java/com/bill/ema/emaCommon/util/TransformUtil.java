package com.bill.ema.emaCommon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bill.ema.emaCommon.root.BaseAction;
import com.bill.ema.emaCommon.root.BaseEntity;
import com.bill.ema.emaCommon.root.UserAction;
import com.bill.ema.emaCommon.root.UserEntity;

public class TransformUtil {

	public static List<Integer> toIntegerList(List<String> data){
		List<Integer> ret = new ArrayList<Integer>();
		for(String str :data) {
			ret.add(Integer.valueOf(str));
		}
		return ret;
	}
	
	public static List<Integer> toIntegerList(String data){
		
		List<Integer> ret = new ArrayList<Integer>();
		if(data!="")
		for(String str :toStringList(data)) {
			ret.add(Integer.valueOf(str));
		}
		return ret;
	}
	
	public static List<String> toStringList(String data) {
		if(data.contains(","))
			return Arrays.asList(data.split(","));
		return Arrays.asList(data);
	}
	
	public static List<String> nameList(Collection<? extends BaseAction> data){
		List<String> nameList = new ArrayList<String>();
		for(BaseAction b : data) {
			nameList.add(b.getName());
		}
		return nameList;
	}
	
	public static Set<String> usernameSet(Collection<? extends UserAction> data){
		Set<String> usernameList = new HashSet<String>();
		for(UserAction b : data) {
			if(b!=null)
			usernameList.add(b.getUsername());
		}
		return usernameList;
	}
	
	public static Set<String> nameSet(Collection<? extends BaseAction> data){
		Set<String> nameList = new HashSet<String>();	
		for(BaseAction b : data) {
			if(b!=null)
				nameList.add(b.getName());
		}
		return nameList;
	}
	
	public static List<Integer> idList(Collection<? extends BaseAction> data){
		List<Integer> idSet = new ArrayList<Integer>();
		for(BaseAction b : data) {
			if(b!=null)
			idSet.add(b.getId());
		}
		return idSet;
	}
	
}
