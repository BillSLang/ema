package com.bill.ema.emaCommon.util;

import java.util.List;

import lombok.Data;

@Data
public class PageUtil {
	private int page;//页码
	private int count;//每页记录数
	private int totalPage;//总页数
	private int totalCount;	
	private List<?> list;//列表数据
	
}
