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
import com.ruoyi.kpi.domain.KpiDeptPlan;
import com.ruoyi.kpi.service.IKpiDeptPlanService;

@RestController
@RequestMapping("/kpi/deptPlan")
public class KpiDeptPlanController extends BaseController
{
	@Autowired
	private IKpiDeptPlanService planService;

	@PreAuthorize("@ss.hasPermi('kpi:deptPlan:list')")
	@GetMapping("/list")
	public TableDataInfo list(KpiDeptPlan plan)
	{
		startPage();
		List<KpiDeptPlan> list = planService.selectKpiDeptPlanList(plan);
		return getDataTable(list);
	}

	@Log(title = "部门考核方案", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('kpi:deptPlan:export')")
	@PostMapping("/export")
	public void export(HttpServletResponse response, KpiDeptPlan plan)
	{
		List<KpiDeptPlan> list = planService.selectKpiDeptPlanList(plan);
		ExcelUtil<KpiDeptPlan> util = new ExcelUtil<>(KpiDeptPlan.class);
		util.exportExcel(response, list, "部门考核方案");
	}

	@PreAuthorize("@ss.hasPermi('kpi:deptPlan:query')")
	@GetMapping("/{id}")
	public AjaxResult getInfo(@PathVariable Long id)
	{
		return success(planService.selectKpiDeptPlanById(id));
	}

	@PreAuthorize("@ss.hasPermi('kpi:deptPlan:add')")
	@Log(title = "部门考核方案", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody KpiDeptPlan plan)
	{
		plan.setCreateBy(getUsername());
		return toAjax(planService.insertKpiDeptPlan(plan));
	}

	@PreAuthorize("@ss.hasPermi('kpi:deptPlan:edit')")
	@Log(title = "部门考核方案", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody KpiDeptPlan plan)
	{
		plan.setUpdateBy(getUsername());
		return toAjax(planService.updateKpiDeptPlan(plan));
	}

	@PreAuthorize("@ss.hasPermi('kpi:deptPlan:remove')")
	@Log(title = "部门考核方案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids)
	{
		return toAjax(planService.deleteKpiDeptPlanByIds(ids));
	}
} 