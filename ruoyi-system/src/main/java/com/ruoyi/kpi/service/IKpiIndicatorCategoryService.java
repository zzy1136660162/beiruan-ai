package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiIndicatorCategory;

public interface IKpiIndicatorCategoryService
{
    public KpiIndicatorCategory selectKpiIndicatorCategoryById(Long id);

    public List<KpiIndicatorCategory> selectKpiIndicatorCategoryList(KpiIndicatorCategory category);

    public int insertKpiIndicatorCategory(KpiIndicatorCategory category);

    public int updateKpiIndicatorCategory(KpiIndicatorCategory category);

    public int deleteKpiIndicatorCategoryByIds(Long[] ids);

    public int deleteKpiIndicatorCategoryById(Long id);
} 