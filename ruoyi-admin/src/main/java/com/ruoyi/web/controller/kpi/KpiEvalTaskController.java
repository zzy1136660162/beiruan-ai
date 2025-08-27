package com.ruoyi.web.controller.kpi;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.kpi.domain.KpiEvalTask;
import com.ruoyi.kpi.service.IKpiEvalTaskService;

@RestController
@RequestMapping("/kpi/evalTask")
public class KpiEvalTaskController extends BaseController
{
	@Autowired
	private IKpiEvalTaskService taskService;

	@PreAuthorize("@ss.hasPermi('kpi:evalTask:list')")
	@GetMapping("/list")
	public TableDataInfo list(KpiEvalTask task)
	{
		startPage();
		List<KpiEvalTask> list = taskService.selectKpiEvalTaskList(task);
		return getDataTable(list);
	}

	@PreAuthorize("@ss.hasPermi('kpi:evalTask:my')")
	@GetMapping("/my")
	public TableDataInfo my(KpiEvalTask task)
	{
		startPage();
		task.setRaterUserId(getUserId());
		List<KpiEvalTask> list = taskService.selectKpiEvalTaskList(task);
		return getDataTable(list);
	}

	@Log(title = "评价任务", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('kpi:evalTask:export')")
	@PostMapping("/export")
	public void export(HttpServletResponse response, KpiEvalTask task)
	{
		List<KpiEvalTask> list = taskService.selectKpiEvalTaskList(task);
		ExcelUtil<KpiEvalTask> util = new ExcelUtil<>(KpiEvalTask.class);
		util.exportExcel(response, list, "评价任务");
	}

	@PreAuthorize("@ss.hasPermi('kpi:evalTask:query')")
	@GetMapping("/{id}")
	public AjaxResult getInfo(@PathVariable Long id)
	{
		return success(taskService.selectKpiEvalTaskById(id));
	}

	@PreAuthorize("@ss.hasPermi('kpi:evalTask:add')")
	@Log(title = "评价任务", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody KpiEvalTask task)
	{
		task.setCreateBy(getUsername());
		return toAjax(taskService.insertKpiEvalTask(task));
	}

	@PreAuthorize("@ss.hasPermi('kpi:evalTask:edit')")
	@Log(title = "评价任务", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody KpiEvalTask task)
	{
		task.setUpdateBy(getUsername());
		return toAjax(taskService.updateKpiEvalTask(task));
	}

	@PreAuthorize("@ss.hasPermi('kpi:evalTask:remove')")
	@Log(title = "评价任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids)
	{
		return toAjax(taskService.deleteKpiEvalTaskByIds(ids));
	}
} 