package com.ruoyi.kpi.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kpi.domain.KpiEvalTask;
import com.ruoyi.kpi.mapper.KpiEvalTaskMapper;
import com.ruoyi.kpi.service.IKpiEvalTaskService;

@Service
public class KpiEvalTaskServiceImpl implements IKpiEvalTaskService
{
	@Autowired
	private KpiEvalTaskMapper taskMapper;
	@Override
	public KpiEvalTask selectKpiEvalTaskById(Long id){return taskMapper.selectKpiEvalTaskById(id);} 
	@Override
	public List<KpiEvalTask> selectKpiEvalTaskList(KpiEvalTask task){return taskMapper.selectKpiEvalTaskList(task);} 
	@Override
	public int insertKpiEvalTask(KpiEvalTask task){return taskMapper.insertKpiEvalTask(task);} 
	@Override
	public int updateKpiEvalTask(KpiEvalTask task){return taskMapper.updateKpiEvalTask(task);} 
	@Override
	public int deleteKpiEvalTaskByIds(Long[] ids){return taskMapper.deleteKpiEvalTaskByIds(ids);} 
	@Override
	public int deleteKpiEvalTaskById(Long id){return taskMapper.deleteKpiEvalTaskById(id);} 
} 