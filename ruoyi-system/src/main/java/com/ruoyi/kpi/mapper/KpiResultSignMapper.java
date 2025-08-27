package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiResultSign;

public interface KpiResultSignMapper
{
	public KpiResultSign selectKpiResultSignById(Long id);
	public List<KpiResultSign> selectKpiResultSignList(KpiResultSign sign);
	public int insertKpiResultSign(KpiResultSign sign);
	public int updateKpiResultSign(KpiResultSign sign);
	public int deleteKpiResultSignById(Long id);
	public int deleteKpiResultSignByIds(Long[] ids);
} 