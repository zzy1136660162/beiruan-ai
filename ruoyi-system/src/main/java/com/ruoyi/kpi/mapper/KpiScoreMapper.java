package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiScore;

public interface KpiScoreMapper
{
	public KpiScore selectKpiScoreById(Long id);
	public List<KpiScore> selectKpiScoreList(KpiScore score);
	public int insertKpiScore(KpiScore score);
	public int updateKpiScore(KpiScore score);
	public int deleteKpiScoreById(Long id);
	public int deleteKpiScoreByIds(Long[] ids);
} 