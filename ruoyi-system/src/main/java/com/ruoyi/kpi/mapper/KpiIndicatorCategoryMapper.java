package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiIndicatorCategory;

public interface KpiIndicatorCategoryMapper
{
    public KpiIndicatorCategory selectKpiIndicatorCategoryById(Long id);

    public List<KpiIndicatorCategory> selectKpiIndicatorCategoryList(KpiIndicatorCategory category);

    public int insertKpiIndicatorCategory(KpiIndicatorCategory category);

    public int updateKpiIndicatorCategory(KpiIndicatorCategory category);

    public int deleteKpiIndicatorCategoryById(Long id);

    public int deleteKpiIndicatorCategoryByIds(Long[] ids);
} 