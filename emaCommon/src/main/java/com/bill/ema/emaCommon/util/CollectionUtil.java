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
	
	@Test
	public void test() {
		List<Integer> newC = new ArrayList();
		List<Integer> oldC = new ArrayList();
		oldC.add(3);
		oldC.add(2);
		oldC.add(4);
		edit(newC,oldC);
		System.out.println(newC);
		System.out.println(oldC);
	}
}
