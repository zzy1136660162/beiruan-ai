package com.ruoyi.kpi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class KpiEvalTask extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long id;
	@Excel(name = "计划类型")
	private String planType; // person/dept
	@Excel(name = "计划ID")
	private Long planId;
	@Excel(name = "计划项ID")
	private Long planItemId;
	@Excel(name = "目标用户")
	private Long targetUserId;
	@Excel(name = "目标部门")
	private Long targetDeptId;
	@Excel(name = "评价人")
	private Long raterUserId;
	@Excel(name = "评价职位")
	private Long raterPostId;
	@Excel(name = "截止时间")
	private java.util.Date deadline;
	@Excel(name = "状态")
	private String status; // pending/done/expired
	@Excel(name = "完成时间")
	private java.util.Date finishTime;
	public Long getId(){return id;} public void setId(Long v){this.id=v;}
	public String getPlanType(){return planType;} public void setPlanType(String v){this.planType=v;}
	public Long getPlanId(){return planId;} public void setPlanId(Long v){this.planId=v;}
	public Long getPlanItemId(){return planItemId;} public void setPlanItemId(Long v){this.planItemId=v;}
	public Long getTargetUserId(){return targetUserId;} public void setTargetUserId(Long v){this.targetUserId=v;}
	public Long getTargetDeptId(){return targetDeptId;} public void setTargetDeptId(Long v){this.targetDeptId=v;}
	public Long getRaterUserId(){return raterUserId;} public void setRaterUserId(Long v){this.raterUserId=v;}
	public Long getRaterPostId(){return raterPostId;} public void setRaterPostId(Long v){this.raterPostId=v;}
	public java.util.Date getDeadline(){return deadline;} public void setDeadline(java.util.Date v){this.deadline=v;}
	public String getStatus(){return status;} public void setStatus(String v){this.status=v;}
	public java.util.Date getFinishTime(){return finishTime;} public void setFinishTime(java.util.Date v){this.finishTime=v;}
} 