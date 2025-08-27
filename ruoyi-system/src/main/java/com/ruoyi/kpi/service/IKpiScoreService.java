package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiScore;

public interface IKpiScoreService
{
	public KpiScore selectKpiScoreById(Long id);
	public List<KpiScore> selectKpiScoreList(KpiScore score);
	public int insertKpiScore(KpiScore score);
	public int updateKpiScore(KpiScore score);
	public int deleteKpiScoreByIds(Long[] ids);
	public int deleteKpiScoreById(Long id);
} 