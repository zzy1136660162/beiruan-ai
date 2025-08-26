package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiCycleWeight;

public interface KpiCycleWeightMapper
{
    public KpiCycleWeight selectKpiCycleWeightById(Long id);

    public List<KpiCycleWeight> selectKpiCycleWeightList(KpiCycleWeight weight);

    public int insertKpiCycleWeight(KpiCycleWeight weight);

    public int updateKpiCycleWeight(KpiCycleWeight weight);

    public int deleteKpiCycleWeightById(Long id);

    public int deleteKpiCycleWeightByIds(Long[] ids);
} 