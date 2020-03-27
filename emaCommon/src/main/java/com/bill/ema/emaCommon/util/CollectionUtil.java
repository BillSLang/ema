package com.bill.ema.emaCommon.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class CollectionUtil {

	public static void edit(List<? extends Object> newC,List<? extends Object> oldC) {
		List<Object> tmp = new ArrayList<Object>();
		tmp.addAll(newC);
		tmp.retainAll(oldC);
		newC.removeAll(tmp);
		oldC.removeAll(tmp);
	}
	
}
