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
import com.ruoyi.kpi.domain.KpiPersonPlan;
import com.ruoyi.kpi.service.IKpiPersonPlanService;

@RestController
@RequestMapping("/kpi/personPlan")
public class KpiPersonPlanController extends BaseController
{
	@Autowired
	private IKpiPersonPlanService planService;

	@PreAuthorize("@ss.hasPermi('kpi:personPlan:list')")
	@GetMapping("/list")
	public TableDataInfo list(KpiPersonPlan plan)
	{
		startPage();
		List<KpiPersonPlan> list = planService.selectKpiPersonPlanList(plan);
		return getDataTable(list);
	}

	@Log(title = "个人考核方案", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('kpi:personPlan:export')")
	@PostMapping("/export")
	public void export(HttpServletResponse response, KpiPersonPlan plan)
	{
		List<KpiPersonPlan> list = planService.selectKpiPersonPlanList(plan);
		ExcelUtil<KpiPersonPlan> util = new ExcelUtil<>(KpiPersonPlan.class);
		util.exportExcel(response, list, "个人考核方案");
	}

	@PreAuthorize("@ss.hasPermi('kpi:personPlan:query')")
	@GetMapping("/{id}")
	public AjaxResult getInfo(@PathVariable Long id)
	{
		return success(planService.selectKpiPersonPlanById(id));
	}

	@PreAuthorize("@ss.hasPermi('kpi:personPlan:add')")
	@Log(title = "个人考核方案", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody KpiPersonPlan plan)
	{
		plan.setCreateBy(getUsername());
		return toAjax(planService.insertKpiPersonPlan(plan));
	}

	@PreAuthorize("@ss.hasPermi('kpi:personPlan:edit')")
	@Log(title = "个人考核方案", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody KpiPersonPlan plan)
	{
		plan.setUpdateBy(getUsername());
		return toAjax(planService.updateKpiPersonPlan(plan));
	}

	@PreAuthorize("@ss.hasPermi('kpi:personPlan:remove')")
	@Log(title = "个人考核方案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids)
	{
		return toAjax(planService.deleteKpiPersonPlanByIds(ids));
	}
} 