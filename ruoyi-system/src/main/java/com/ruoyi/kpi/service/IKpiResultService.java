package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiResult;

public interface IKpiResultService
{
	public KpiResult selectKpiResultById(Long id);
	public List<KpiResult> selectKpiResultList(KpiResult result);
	public int insertKpiResult(KpiResult result);
	public int updateKpiResult(KpiResult result);
	public int deleteKpiResultByIds(Long[] ids);
	public int deleteKpiResultById(Long id);
} 