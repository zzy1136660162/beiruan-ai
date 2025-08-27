package com.ruoyi.kpi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class KpiPersonPlan extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	private Long id;
	@Excel(name = "周期ID")
	private Long cycleId;
	@Excel(name = "被考核人ID")
	private Long targetUserId;
	@Excel(name = "对象类型")
	private String targetType; // employee/middle
	@Excel(name = "状态")
	private String status; // draft/pending/approved/rejected/active/finished
	@Excel(name = "提交时间")
	private java.util.Date submitTime;
	private Long approveUserId;
	@Excel(name = "审批时间")
	private java.util.Date approveTime;
	private String remark;
	private String delFlag;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public Long getCycleId() { return cycleId; }
	public void setCycleId(Long cycleId) { this.cycleId = cycleId; }
	public Long getTargetUserId() { return targetUserId; }
	public void setTargetUserId(Long targetUserId) { this.targetUserId = targetUserId; }
	public String getTargetType() { return targetType; }
	public void setTargetType(String targetType) { this.targetType = targetType; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public java.util.Date getSubmitTime() { return submitTime; }
	public void setSubmitTime(java.util.Date submitTime) { this.submitTime = submitTime; }
	public Long getApproveUserId() { return approveUserId; }
	public void setApproveUserId(Long approveUserId) { this.approveUserId = approveUserId; }
	public java.util.Date getApproveTime() { return approveTime; }
	public void setApproveTime(java.util.Date approveTime) { this.approveTime = approveTime; }
	public String getRemark() { return remark; }
	public void setRemark(String remark) { this.remark = remark; }
	public String getDelFlag() { return delFlag; }
	public void setDelFlag(String delFlag) { this.delFlag = delFlag; }
} 