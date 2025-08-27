package com.ruoyi.kpi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class KpiPersonPlanItem extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long id;
	@Excel(name = "计划ID")
	private Long planId;
	private Long indicatorId;
	@Excel(name = "指标名称")
	private String indicatorName;
	@Excel(name = "评分标准")
	private String scoringStandard;
	@Excel(name = "满分")
	private java.math.BigDecimal scoreFull;
	@Excel(name = "项权重")
	private java.math.BigDecimal itemWeight;
	@Excel(name = "排序")
	private Integer sort;
	public Long getId(){return id;} public void setId(Long id){this.id=id;}
	public Long getPlanId(){return planId;} public void setPlanId(Long planId){this.planId=planId;}
	public Long getIndicatorId(){return indicatorId;} public void setIndicatorId(Long indicatorId){this.indicatorId=indicatorId;}
	public String getIndicatorName(){return indicatorName;} public void setIndicatorName(String v){this.indicatorName=v;}
	public String getScoringStandard(){return scoringStandard;} public void setScoringStandard(String v){this.scoringStandard=v;}
	public java.math.BigDecimal getScoreFull(){return scoreFull;} public void setScoreFull(java.math.BigDecimal v){this.scoreFull=v;}
	public java.math.BigDecimal getItemWeight(){return itemWeight;} public void setItemWeight(java.math.BigDecimal v){this.itemWeight=v;}
	public Integer getSort(){return sort;} public void setSort(Integer v){this.sort=v;}
} 