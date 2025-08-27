package com.ruoyi.kpi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class KpiResult extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long id;
	@Excel(name = "计划类型")
	private String planType; // person/dept
	@Excel(name = "计划ID")
	private Long planId;
	@Excel(name = "目标用户")
	private Long targetUserId;
	@Excel(name = "目标部门")
	private Long targetDeptId;
	@Excel(name = "最终得分")
	private java.math.BigDecimal finalScore;
	@Excel(name = "完成比例")
	private java.math.BigDecimal completeRatio;
	@Excel(name = "状态")
	private String status; // generated/confirmed
	@Excel(name = "生成时间")
	private java.util.Date generateTime;
	public Long getId(){return id;} public void setId(Long v){this.id=v;}
	public String getPlanType(){return planType;} public void setPlanType(String v){this.planType=v;}
	public Long getPlanId(){return planId;} public void setPlanId(Long v){this.planId=v;}
	public Long getTargetUserId(){return targetUserId;} public void setTargetUserId(Long v){this.targetUserId=v;}
	public Long getTargetDeptId(){return targetDeptId;} public void setTargetDeptId(Long v){this.targetDeptId=v;}
	public java.math.BigDecimal getFinalScore(){return finalScore;} public void setFinalScore(java.math.BigDecimal v){this.finalScore=v;}
	public java.math.BigDecimal getCompleteRatio(){return completeRatio;} public void setCompleteRatio(java.math.BigDecimal v){this.completeRatio=v;}
	public String getStatus(){return status;} public void setStatus(String v){this.status=v;}
	public java.util.Date getGenerateTime(){return generateTime;} public void setGenerateTime(java.util.Date v){this.generateTime=v;}
} 