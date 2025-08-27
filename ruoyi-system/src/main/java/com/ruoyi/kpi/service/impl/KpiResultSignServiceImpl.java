package com.ruoyi.kpi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.domain.KpiResultSign;
import com.ruoyi.kpi.mapper.KpiResultSignMapper;
import com.ruoyi.kpi.service.IKpiResultSignService;

@Service
public class KpiResultSignServiceImpl implements IKpiResultSignService
{
	@Autowired
	private KpiResultSignMapper signMapper;
	@Override
	public KpiResultSign selectKpiResultSignById(Long id){return signMapper.selectKpiResultSignById(id);} 
	@Override
	public List<KpiResultSign> selectKpiResultSignList(KpiResultSign sign){return signMapper.selectKpiResultSignList(sign);} 
	@Override
	public int insertKpiResultSign(KpiResultSign sign){return signMapper.insertKpiResultSign(sign);} 
	@Override
	public int updateKpiResultSign(KpiResultSign sign){return signMapper.updateKpiResultSign(sign);} 
	@Override
	public int deleteKpiResultSignByIds(Long[] ids){return signMapper.deleteKpiResultSignByIds(ids);} 
	@Override
	public int deleteKpiResultSignById(Long id){return signMapper.deleteKpiResultSignById(id);} 
} 