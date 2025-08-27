package com.ruoyi.kpi.mapper;

import java.util.List;
import com.ruoyi.kpi.domain.KpiEvalTask;

public interface KpiEvalTaskMapper
{
	public KpiEvalTask selectKpiEvalTaskById(Long id);
	public List<KpiEvalTask> selectKpiEvalTaskList(KpiEvalTask task);
	public int insertKpiEvalTask(KpiEvalTask task);
	public int updateKpiEvalTask(KpiEvalTask task);
	public int deleteKpiEvalTaskById(Long id);
	public int deleteKpiEvalTaskByIds(Long[] ids);
} 