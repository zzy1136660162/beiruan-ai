package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiIndicator;

public interface IKpiIndicatorService
{
    public KpiIndicator selectKpiIndicatorById(Long id);

    public List<KpiIndicator> selectKpiIndicatorList(KpiIndicator indicator);

    public int insertKpiIndicator(KpiIndicator indicator);

    public int updateKpiIndicator(KpiIndicator indicator);

    public int deleteKpiIndicatorByIds(Long[] ids);

    public int deleteKpiIndicatorById(Long id);
} 