package com.ruoyi.kpi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.domain.KpiPersonPlan;
import com.ruoyi.kpi.mapper.KpiPersonPlanMapper;
import com.ruoyi.kpi.service.IKpiPersonPlanService;

@Service
public class KpiPersonPlanServiceImpl implements IKpiPersonPlanService
{
	@Autowired
	private KpiPersonPlanMapper planMapper;

	@Override
	public KpiPersonPlan selectKpiPersonPlanById(Long id) { return planMapper.selectKpiPersonPlanById(id); }
	@Override
	public List<KpiPersonPlan> selectKpiPersonPlanList(KpiPersonPlan plan) { return planMapper.selectKpiPersonPlanList(plan); }
	@Override
	public int insertKpiPersonPlan(KpiPersonPlan plan) { return planMapper.insertKpiPersonPlan(plan); }
	@Override
	public int updateKpiPersonPlan(KpiPersonPlan plan) { return planMapper.updateKpiPersonPlan(plan); }
	@Override
	public int deleteKpiPersonPlanByIds(Long[] ids) { return planMapper.deleteKpiPersonPlanByIds(ids); }
	@Override
	public int deleteKpiPersonPlanById(Long id) { return planMapper.deleteKpiPersonPlanById(id); }
} 