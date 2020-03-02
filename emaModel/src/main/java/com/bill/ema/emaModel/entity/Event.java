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
@TableName(value = "event")
public class Event {

	@TableId(value = "id")
	private Integer id;

	@TableField("subjectId")
	private Integer subjectId = -2;

	@TableField("auditorId")
	private Integer auditorId = -2;

	@TableField("uriId")
	private Integer uriId = -2;

	@TableField("reportorId")
	private Integer reportorId = -2;

	@TableField("parentId")
	private Integer parentId = -2;

	@TableField("foodbatchId")
	private Integer foodbatchId = -2;

	@TableField(exist = false)
	private String subject;

	@TableField(exist = false)
	private String auditor;

	@TableField(exist = false)
	private String uri;

	@TableField(exist = false)
	private String reportor;

	@TableField(exist = false)
	private String parent;

	@TableField(exist = false)
	private String foodbatch;
	
	@TableField(exist = false)
	private String food;

	private String name;

	@TableField("auditTime")
	private Date auditTime;

	@TableField("crawlTime")
	private Date crawlTime;

	private Byte status = 0;// 审核状态

	private String description;

	public Event() {

	}

	public Event(Map<String, Object> param) {
		if (param.get(Constant.ID) != null && param.get(Constant.ID) != "")
			this.id = Integer.valueOf(param.get(Constant.ID).toString());

		if (param.get(Constant.SUBJECT_ID) != null && param.get(Constant.SUBJECT_ID) != "")
			this.subjectId = Integer.valueOf(param.get(Constant.SUBJECT_ID).toString());
		if (param.get(Constant.URI_ID) != null && param.get(Constant.URI_ID) != "")
			this.uriId = Integer.valueOf(param.get(Constant.URI_ID).toString());
		if (param.get(Constant.FOODBATCH_ID) != null && param.get(Constant.FOODBATCH_ID) != "")
			this.foodbatchId = Integer.valueOf(param.get(Constant.FOODBATCH_ID).toString());
		if (param.get(Constant.AUDITOR_ID) != null && param.get(Constant.AUDITOR_ID) != "")
			this.auditorId = Integer.valueOf(param.get(Constant.AUDITOR_ID).toString());
		if (param.get(Constant.REPORTOR_ID) != null && param.get(Constant.REPORTOR_ID) != "")
			this.reportorId = Integer.valueOf(param.get(Constant.ID).toString());
		if (param.get(Constant.PARENT_ID) != null && param.get(Constant.PARENT_ID) != "")
			this.parentId = Integer.valueOf(param.get(Constant.PARENT_ID).toString());

		if (param.get(Constant.SUBJECT) != null && param.get(Constant.SUBJECT) != "")
			this.subject = param.get(Constant.SUBJECT).toString();
		if (param.get(Constant.URI) != null && param.get(Constant.URI) != "")
			this.uri = param.get(Constant.URI).toString();
		if (param.get(Constant.FOODBATCH) != null && param.get(Constant.FOODBATCH) != "")
			this.foodbatch = param.get(Constant.FOODBATCH).toString();
		if (param.get(Constant.FOOD) != null && param.get(Constant.FOOD) != "")
			this.food = param.get(Constant.FOOD).toString();
		if (param.get(Constant.AUDITOR) != null && param.get(Constant.AUDITOR) != "")
			this.auditor = param.get(Constant.AUDITOR).toString();
		if (param.get(Constant.REPORTOR) != null && param.get(Constant.REPORTOR) != "")
			this.reportor = param.get(Constant.REPORTOR).toString();
		if (param.get(Constant.PARENT) != null && param.get(Constant.PARENT) != "")
			this.parent = param.get(Constant.PARENT).toString();

		if (param.get(Constant.NAME) != null && param.get(Constant.NAME) != "")
			this.name = param.get(Constant.NAME).toString();
		if (param.get(Constant.DESCRIPTION) != null && param.get(Constant.DESCRIPTION) != "")
			this.description = param.get(Constant.DESCRIPTION).toString();

		try {
			if (param.get(Constant.AUDIT_TIME) != null && param.get(Constant.AUDIT_TIME) != "")
				this.auditTime = TransformUtil.toDate(param.get(Constant.AUDIT_TIME).toString());

			if (param.get(Constant.CRAWL_TIME) != null && param.get(Constant.CRAWL_TIME) != "")
				this.crawlTime = TransformUtil.toDate(param.get(Constant.CRAWL_TIME).toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
