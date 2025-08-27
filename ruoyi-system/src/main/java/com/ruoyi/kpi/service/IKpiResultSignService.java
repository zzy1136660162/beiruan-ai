package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiResultSign;

public interface IKpiResultSignService
{
	public KpiResultSign selectKpiResultSignById(Long id);
	public List<KpiResultSign> selectKpiResultSignList(KpiResultSign sign);
	public int insertKpiResultSign(KpiResultSign sign);
	public int updateKpiResultSign(KpiResultSign sign);
	public int deleteKpiResultSignByIds(Long[] ids);
	public int deleteKpiResultSignById(Long id);
} 