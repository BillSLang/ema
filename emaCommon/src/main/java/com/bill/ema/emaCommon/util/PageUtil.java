package com.bill.ema.emaCommon.util;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.Data;

@Data
public class PageUtil {
	private int pageSize;//页码
	private int current;
	private int count;//每页记录数
	private int totalPage;//总页数
	private int totalCount;	
	private List<?> list;//列表数据
	public PageUtil(IPage<?> page) {
        this.list = page.getRecords();
        this.totalCount = (int)page.getTotal();
        this.pageSize = (int)page.getSize();
        this.current = (int)page.getCurrent();
        this.totalPage = (int)page.getPages();
    }
}
