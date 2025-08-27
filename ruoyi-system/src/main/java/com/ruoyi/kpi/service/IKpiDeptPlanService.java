package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiDeptPlan;

public interface IKpiDeptPlanService
{
	public KpiDeptPlan selectKpiDeptPlanById(Long id);
	public List<KpiDeptPlan> selectKpiDeptPlanList(KpiDeptPlan plan);
	public int insertKpiDeptPlan(KpiDeptPlan plan);
	public int updateKpiDeptPlan(KpiDeptPlan plan);
	public int deleteKpiDeptPlanByIds(Long[] ids);
	public int deleteKpiDeptPlanById(Long id);
} 