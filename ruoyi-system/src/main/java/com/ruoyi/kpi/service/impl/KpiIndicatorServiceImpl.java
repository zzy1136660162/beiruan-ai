package com.ruoyi.kpi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.domain.KpiIndicator;
import com.ruoyi.kpi.mapper.KpiIndicatorMapper;
import com.ruoyi.kpi.service.IKpiIndicatorService;

@Service
public class KpiIndicatorServiceImpl implements IKpiIndicatorService
{
    @Autowired
    private KpiIndicatorMapper indicatorMapper;

    @Override
    public KpiIndicator selectKpiIndicatorById(Long id)
    {
        return indicatorMapper.selectKpiIndicatorById(id);
    }

    @Override
    public List<KpiIndicator> selectKpiIndicatorList(KpiIndicator indicator)
    {
        return indicatorMapper.selectKpiIndicatorList(indicator);
    }

    @Override
    public int insertKpiIndicator(KpiIndicator indicator)
    {
        return indicatorMapper.insertKpiIndicator(indicator);
    }

    @Override
    public int updateKpiIndicator(KpiIndicator indicator)
    {
        return indicatorMapper.updateKpiIndicator(indicator);
    }

    @Override
    public int deleteKpiIndicatorByIds(Long[] ids)
    {
        return indicatorMapper.deleteKpiIndicatorByIds(ids);
    }

    @Override
    public int deleteKpiIndicatorById(Long id)
    {
        return indicatorMapper.deleteKpiIndicatorById(id);
    }
} 