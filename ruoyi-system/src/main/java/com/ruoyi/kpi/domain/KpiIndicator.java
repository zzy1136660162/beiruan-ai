package com.ruoyi.kpi.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * KPI 指标
 */
public class KpiIndicator extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "年份")
    private Integer year;

    @Excel(name = "种类")
    private String kind; // dept/person/middle

    @Excel(name = "分类ID")
    private Long categoryId;

    @Excel(name = "指标名称")
    private String name;

    @Excel(name = "指标说明")
    private String description;

    @Excel(name = "评分标准")
    private String scoringStandard;

    @Excel(name = "评分来源")
    private String source;

    @Excel(name = "满分")
    private java.math.BigDecimal scoreFull;

    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    private String delFlag;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public String getKind() { return kind; }
    public void setKind(String kind) { this.kind = kind; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getScoringStandard() { return scoringStandard; }
    public void setScoringStandard(String scoringStandard) { this.scoringStandard = scoringStandard; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public java.math.BigDecimal getScoreFull() { return scoreFull; }
    public void setScoreFull(java.math.BigDecimal scoreFull) { this.scoreFull = scoreFull; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDelFlag() { return delFlag; }
    public void setDelFlag(String delFlag) { this.delFlag = delFlag; }
} 