package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiCycle;

public interface IKpiCycleService
{
    public KpiCycle selectKpiCycleById(Long id);

    public List<KpiCycle> selectKpiCycleList(KpiCycle cycle);

    public int insertKpiCycle(KpiCycle cycle);

    public int updateKpiCycle(KpiCycle cycle);

    public int deleteKpiCycleByIds(Long[] ids);

    public int deleteKpiCycleById(Long id);
} 