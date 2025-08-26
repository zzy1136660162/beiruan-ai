package com.ruoyi.kpi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * KPI 考核周期
 */
public class KpiCycle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "周期类型")
    private String cycleType; // quarter/half/year/custom

    @Excel(name = "年份")
    private Integer year;

    @Excel(name = "开始时间")
    private java.util.Date startTime;

    @Excel(name = "结束时间")
    private java.util.Date endTime;

    @Excel(name = "状态")
    private String status; // not_started/ongoing/finished

    private String remark;

    private String delFlag;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCycleType() { return cycleType; }
    public void setCycleType(String cycleType) { this.cycleType = cycleType; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public java.util.Date getStartTime() { return startTime; }
    public void setStartTime(java.util.Date startTime) { this.startTime = startTime; }

    public java.util.Date getEndTime() { return endTime; }
    public void setEndTime(java.util.Date endTime) { this.endTime = endTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public String getDelFlag() { return delFlag; }
    public void setDelFlag(String delFlag) { this.delFlag = delFlag; }
} 