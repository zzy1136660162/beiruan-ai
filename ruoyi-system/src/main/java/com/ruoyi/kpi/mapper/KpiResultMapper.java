package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiResult;

public interface KpiResultMapper
{
	public KpiResult selectKpiResultById(Long id);
	public List<KpiResult> selectKpiResultList(KpiResult result);
	public int insertKpiResult(KpiResult result);
	public int updateKpiResult(KpiResult result);
	public int deleteKpiResultById(Long id);
	public int deleteKpiResultByIds(Long[] ids);
} 