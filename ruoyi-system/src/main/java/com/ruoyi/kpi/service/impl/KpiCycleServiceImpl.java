package com.ruoyi.kpi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.domain.KpiCycle;
import com.ruoyi.kpi.mapper.KpiCycleMapper;
import com.ruoyi.kpi.service.IKpiCycleService;

@Service
public class KpiCycleServiceImpl implements IKpiCycleService
{
    @Autowired
    private KpiCycleMapper cycleMapper;

    @Override
    public KpiCycle selectKpiCycleById(Long id)
    {
        return cycleMapper.selectKpiCycleById(id);
    }

    @Override
    public List<KpiCycle> selectKpiCycleList(KpiCycle cycle)
    {
        return cycleMapper.selectKpiCycleList(cycle);
    }

    @Override
    public int insertKpiCycle(KpiCycle cycle)
    {
        return cycleMapper.insertKpiCycle(cycle);
    }

    @Override
    public int updateKpiCycle(KpiCycle cycle)
    {
        return cycleMapper.updateKpiCycle(cycle);
    }

    @Override
    public int deleteKpiCycleByIds(Long[] ids)
    {
        return cycleMapper.deleteKpiCycleByIds(ids);
    }

    @Override
    public int deleteKpiCycleById(Long id)
    {
        return cycleMapper.deleteKpiCycleById(id);
    }
} 