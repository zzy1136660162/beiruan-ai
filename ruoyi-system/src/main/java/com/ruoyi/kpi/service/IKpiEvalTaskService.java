package com.ruoyi.kpi.service;

import java.util.List;
import com.ruoyi.kpi.domain.KpiEvalTask;

public interface IKpiEvalTaskService
{
	public KpiEvalTask selectKpiEvalTaskById(Long id);
	public List<KpiEvalTask> selectKpiEvalTaskList(KpiEvalTask task);
	public int insertKpiEvalTask(KpiEvalTask task);
	public int updateKpiEvalTask(KpiEvalTask task);
	public int deleteKpiEvalTaskByIds(Long[] ids);
	public int deleteKpiEvalTaskById(Long id);
} 