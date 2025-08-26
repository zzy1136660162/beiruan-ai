package com.ruoyi.kpi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.domain.KpiIndicatorCategory;
import com.ruoyi.kpi.mapper.KpiIndicatorCategoryMapper;
import com.ruoyi.kpi.service.IKpiIndicatorCategoryService;

@Service
public class KpiIndicatorCategoryServiceImpl implements IKpiIndicatorCategoryService
{
    @Autowired
    private KpiIndicatorCategoryMapper categoryMapper;

    @Override
    public KpiIndicatorCategory selectKpiIndicatorCategoryById(Long id)
    {
        return categoryMapper.selectKpiIndicatorCategoryById(id);
    }

    @Override
    public List<KpiIndicatorCategory> selectKpiIndicatorCategoryList(KpiIndicatorCategory category)
    {
        return categoryMapper.selectKpiIndicatorCategoryList(category);
    }

    @Override
    public int insertKpiIndicatorCategory(KpiIndicatorCategory category)
    {
        return categoryMapper.insertKpiIndicatorCategory(category);
    }

    @Override
    public int updateKpiIndicatorCategory(KpiIndicatorCategory category)
    {
        return categoryMapper.updateKpiIndicatorCategory(category);
    }

    @Override
    public int deleteKpiIndicatorCategoryByIds(Long[] ids)
    {
        return categoryMapper.deleteKpiIndicatorCategoryByIds(ids);
    }

    @Override
    public int deleteKpiIndicatorCategoryById(Long id)
    {
        return categoryMapper.deleteKpiIndicatorCategoryById(id);
    }
} 