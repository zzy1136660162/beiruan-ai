package com.ruoyi.kpi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.domain.KpiDeptPlan;
import com.ruoyi.kpi.mapper.KpiDeptPlanMapper;
import com.ruoyi.kpi.service.IKpiDeptPlanService;

@Service
public class KpiDeptPlanServiceImpl implements IKpiDeptPlanService
{
	@Autowired
	private KpiDeptPlanMapper planMapper;
	@Override
	public KpiDeptPlan selectKpiDeptPlanById(Long id){return planMapper.selectKpiDeptPlanById(id);} 
	@Override
	public List<KpiDeptPlan> selectKpiDeptPlanList(KpiDeptPlan plan){return planMapper.selectKpiDeptPlanList(plan);} 
	@Override
	public int insertKpiDeptPlan(KpiDeptPlan plan){return planMapper.insertKpiDeptPlan(plan);} 
	@Override
	public int updateKpiDeptPlan(KpiDeptPlan plan){return planMapper.updateKpiDeptPlan(plan);} 
	@Override
	public int deleteKpiDeptPlanByIds(Long[] ids){return planMapper.deleteKpiDeptPlanByIds(ids);} 
	@Override
	public int deleteKpiDeptPlanById(Long id){return planMapper.deleteKpiDeptPlanById(id);} 
} 