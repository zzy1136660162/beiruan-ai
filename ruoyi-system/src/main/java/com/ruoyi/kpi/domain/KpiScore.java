package com.ruoyi.kpi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class KpiScore extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long id;
	@Excel(name = "任务ID")
	private Long taskId;
	@Excel(name = "得分")
	private java.math.BigDecimal score;
	@Excel(name = "评语")
	private String comment;
	public Long getId(){return id;} public void setId(Long v){this.id=v;}
	public Long getTaskId(){return taskId;} public void setTaskId(Long v){this.taskId=v;}
	public java.math.BigDecimal getScore(){return score;} public void setScore(java.math.BigDecimal v){this.score=v;}
	public String getComment(){return comment;} public void setComment(String v){this.comment=v;}
} 