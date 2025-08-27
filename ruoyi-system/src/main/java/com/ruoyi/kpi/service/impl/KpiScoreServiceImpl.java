package com.ruoyi.kpi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.domain.KpiScore;
import com.ruoyi.kpi.mapper.KpiScoreMapper;
import com.ruoyi.kpi.service.IKpiScoreService;

@Service
public class KpiScoreServiceImpl implements IKpiScoreService
{
	@Autowired
	private KpiScoreMapper scoreMapper;
	@Override
	public KpiScore selectKpiScoreById(Long id){return scoreMapper.selectKpiScoreById(id);} 
	@Override
	public List<KpiScore> selectKpiScoreList(KpiScore score){return scoreMapper.selectKpiScoreList(score);} 
	@Override
	public int insertKpiScore(KpiScore score){return scoreMapper.insertKpiScore(score);} 
	@Override
	public int updateKpiScore(KpiScore score){return scoreMapper.updateKpiScore(score);} 
	@Override
	public int deleteKpiScoreByIds(Long[] ids){return scoreMapper.deleteKpiScoreByIds(ids);} 
	@Override
	public int deleteKpiScoreById(Long id){return scoreMapper.deleteKpiScoreById(id);} 
} 