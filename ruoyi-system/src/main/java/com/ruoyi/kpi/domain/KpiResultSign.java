package com.ruoyi.kpi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class KpiResultSign extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long id;
	@Excel(name = "结果ID")
	private Long resultId;
	@Excel(name = "用户ID")
	private Long userId;
	@Excel(name = "签字状态")
	private String signStatus; // 0未签 1已签
	@Excel(name = "签字时间")
	private java.util.Date signTime;
	public Long getId(){return id;} public void setId(Long v){this.id=v;}
	public Long getResultId(){return resultId;} public void setResultId(Long v){this.resultId=v;}
	public Long getUserId(){return userId;} public void setUserId(Long v){this.userId=v;}
	public String getSignStatus(){return signStatus;} public void setSignStatus(String v){this.signStatus=v;}
	public java.util.Date getSignTime(){return signTime;} public void setSignTime(java.util.Date v){this.signTime=v;}
} 