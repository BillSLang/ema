package com.bill.ema.emaModel.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bill.ema.emaCommon.util.Constant;

import lombok.Data;

@Data
@TableName(value="uri")
public class Uri {
	
	@TableId(value="id")
	private Integer id;
	
	private String name;
	
	private String title;
	@TableField("authorId")
	private Integer authorId;
	
	@TableField(exist = false)
	private String author;
	
	@TableField("siteId")
	private Integer siteId;
	
	@TableField(exist = false)
	private String site;
	private Date date;
	private String url;
	private String content;
	private String description;
	
	public Uri() {
		
	}
	public Uri(Map<String,Object> param) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);
		if(param.get(Constant.ID)!=null&&param.get(Constant.ID)!="")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());
		if(param.get(Constant.NAME)!=null&&param.get(Constant.NAME)!="")
			this.name = param.get(Constant.NAME).toString();
		if(param.get(Constant.TITLE)!=null&&param.get(Constant.TITLE)!="")
			this.title = param.get(Constant.TITLE).toString();
		if(param.get(Constant.AUTHOR_ID)!=null&&param.get(Constant.AUTHOR_ID)!="")
			this.authorId =Integer.valueOf(param.get(Constant.AUTHOR_ID).toString());
		if(param.get(Constant.AUTHOR)!=null&&param.get(Constant.AUTHOR)!="")
			this.author =param.get(Constant.AUTHOR).toString();
		if(param.get(Constant.SITE_ID)!=null&&param.get(Constant.SITE_ID)!="")
			this.siteId =Integer.valueOf(param.get(Constant.SITE_ID).toString());
		
		if(param.get(Constant.SITE)!=null&&param.get(Constant.SITE)!="")
			this.site =param.get(Constant.SITE).toString();
		

		this.date =new Date();
			
		
		if(param.get(Constant.URL)!=null&&param.get(Constant.URL)!="")
			this.url =param.get(Constant.URL).toString();
		
		if(param.get(Constant.CONTENT)!=null&&param.get(Constant.CONTENT)!="")
			this.content =param.get(Constant.CONTENT).toString();
		
		if(param.get(Constant.DESCRIPTION)!=null&&param.get(Constant.DESCRIPTION)!="")
			this.description = param.get(Constant.DESCRIPTION).toString();
	}

}
