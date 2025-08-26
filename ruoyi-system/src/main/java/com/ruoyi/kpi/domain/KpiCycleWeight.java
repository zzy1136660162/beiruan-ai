package com.ruoyi.kpi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * KPI 周期评分权重配置
 */
public class KpiCycleWeight extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "周期ID")
    private Long cycleId;

    @Excel(name = "主体")
    private String subjectType; // person/dept/middle

    @Excel(name = "评分职位ID")
    private Long raterPostId; // sys_post.id

    @Excel(name = "权重")
    private java.math.BigDecimal weight; // 0-100

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCycleId() { return cycleId; }
    public void setCycleId(Long cycleId) { this.cycleId = cycleId; }

    public String getSubjectType() { return subjectType; }
    public void setSubjectType(String subjectType) { this.subjectType = subjectType; }

    public Long getRaterPostId() { return raterPostId; }
    public void setRaterPostId(Long raterPostId) { this.raterPostId = raterPostId; }

    public java.math.BigDecimal getWeight() { return weight; }
    public void setWeight(java.math.BigDecimal weight) { this.weight = weight; }
} 