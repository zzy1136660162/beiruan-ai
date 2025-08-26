package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiCycleWeight;

public interface IKpiCycleWeightService
{
    public KpiCycleWeight selectKpiCycleWeightById(Long id);

    public List<KpiCycleWeight> selectKpiCycleWeightList(KpiCycleWeight weight);

    public int insertKpiCycleWeight(KpiCycleWeight weight);

    public int updateKpiCycleWeight(KpiCycleWeight weight);

    public int deleteKpiCycleWeightByIds(Long[] ids);

    public int deleteKpiCycleWeightById(Long id);
} 