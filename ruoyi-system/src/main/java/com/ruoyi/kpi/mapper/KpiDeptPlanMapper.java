package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiDeptPlan;

public interface KpiDeptPlanMapper
{
	public KpiDeptPlan selectKpiDeptPlanById(Long id);
	public List<KpiDeptPlan> selectKpiDeptPlanList(KpiDeptPlan plan);
	public int insertKpiDeptPlan(KpiDeptPlan plan);
	public int updateKpiDeptPlan(KpiDeptPlan plan);
	public int deleteKpiDeptPlanById(Long id);
	public int deleteKpiDeptPlanByIds(Long[] ids);
} 