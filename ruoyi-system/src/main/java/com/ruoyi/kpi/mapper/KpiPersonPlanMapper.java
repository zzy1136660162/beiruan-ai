package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiPersonPlan;

public interface KpiPersonPlanMapper
{
	public KpiPersonPlan selectKpiPersonPlanById(Long id);
	public List<KpiPersonPlan> selectKpiPersonPlanList(KpiPersonPlan plan);
	public int insertKpiPersonPlan(KpiPersonPlan plan);
	public int updateKpiPersonPlan(KpiPersonPlan plan);
	public int deleteKpiPersonPlanById(Long id);
	public int deleteKpiPersonPlanByIds(Long[] ids);
} 