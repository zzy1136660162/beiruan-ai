package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiCycle;

public interface KpiCycleMapper
{
    public KpiCycle selectKpiCycleById(Long id);

    public List<KpiCycle> selectKpiCycleList(KpiCycle cycle);

    public int insertKpiCycle(KpiCycle cycle);

    public int updateKpiCycle(KpiCycle cycle);

    public int deleteKpiCycleById(Long id);

    public int deleteKpiCycleByIds(Long[] ids);
} 