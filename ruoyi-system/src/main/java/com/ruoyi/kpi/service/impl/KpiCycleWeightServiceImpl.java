package com.ruoyi.kpi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.domain.KpiCycleWeight;
import com.ruoyi.kpi.mapper.KpiCycleWeightMapper;
import com.ruoyi.kpi.service.IKpiCycleWeightService;

@Service
public class KpiCycleWeightServiceImpl implements IKpiCycleWeightService
{
    @Autowired
    private KpiCycleWeightMapper weightMapper;

    @Override
    public KpiCycleWeight selectKpiCycleWeightById(Long id)
    {
        return weightMapper.selectKpiCycleWeightById(id);
    }

    @Override
    public List<KpiCycleWeight> selectKpiCycleWeightList(KpiCycleWeight weight)
    {
        return weightMapper.selectKpiCycleWeightList(weight);
    }

    @Override
    public int insertKpiCycleWeight(KpiCycleWeight weight)
    {
        return weightMapper.insertKpiCycleWeight(weight);
    }

    @Override
    public int updateKpiCycleWeight(KpiCycleWeight weight)
    {
        return weightMapper.updateKpiCycleWeight(weight);
    }

    @Override
    public int deleteKpiCycleWeightByIds(Long[] ids)
    {
        return weightMapper.deleteKpiCycleWeightByIds(ids);
    }

    @Override
    public int deleteKpiCycleWeightById(Long id)
    {
        return weightMapper.deleteKpiCycleWeightById(id);
    }
} 