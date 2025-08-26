package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiIndicator;

public interface KpiIndicatorMapper
{
    public KpiIndicator selectKpiIndicatorById(Long id);

    public List<KpiIndicator> selectKpiIndicatorList(KpiIndicator indicator);

    public int insertKpiIndicator(KpiIndicator indicator);

    public int updateKpiIndicator(KpiIndicator indicator);

    public int deleteKpiIndicatorById(Long id);

    public int deleteKpiIndicatorByIds(Long[] ids);
} 