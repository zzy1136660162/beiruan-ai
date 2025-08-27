package com.ruoyi.kpi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.domain.KpiResult;
import com.ruoyi.kpi.mapper.KpiResultMapper;
import com.ruoyi.kpi.service.IKpiResultService;

@Service
public class KpiResultServiceImpl implements IKpiResultService
{
	@Autowired
	private KpiResultMapper resultMapper;
	@Override
	public KpiResult selectKpiResultById(Long id){return resultMapper.selectKpiResultById(id);} 
	@Override
	public List<KpiResult> selectKpiResultList(KpiResult result){return resultMapper.selectKpiResultList(result);} 
	@Override
	public int insertKpiResult(KpiResult result){return resultMapper.insertKpiResult(result);} 
	@Override
	public int updateKpiResult(KpiResult result){return resultMapper.updateKpiResult(result);} 
	@Override
	public int deleteKpiResultByIds(Long[] ids){return resultMapper.deleteKpiResultByIds(ids);} 
	@Override
	public int deleteKpiResultById(Long id){return resultMapper.deleteKpiResultById(id);} 
} 