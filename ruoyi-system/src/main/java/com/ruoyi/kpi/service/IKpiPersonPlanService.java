package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiPersonPlan;

public interface IKpiPersonPlanService
{
	public KpiPersonPlan selectKpiPersonPlanById(Long id);
	public List<KpiPersonPlan> selectKpiPersonPlanList(KpiPersonPlan plan);
	public int insertKpiPersonPlan(KpiPersonPlan plan);
	public int updateKpiPersonPlan(KpiPersonPlan plan);
	public int deleteKpiPersonPlanByIds(Long[] ids);
	public int deleteKpiPersonPlanById(Long id);
} 