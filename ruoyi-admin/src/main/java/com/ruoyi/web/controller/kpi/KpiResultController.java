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
import com.ruoyi.kpi.domain.KpiResult;
import com.ruoyi.kpi.service.IKpiResultService;

@RestController
@RequestMapping("/kpi/result")
public class KpiResultController extends BaseController
{
	@Autowired
	private IKpiResultService resultService;

	@PreAuthorize("@ss.hasPermi('kpi:result:list')")
	@GetMapping("/list")
	public TableDataInfo list(KpiResult result)
	{
		startPage();
		List<KpiResult> list = resultService.selectKpiResultList(result);
		return getDataTable(list);
	}

	@Log(title = "考核结果", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('kpi:result:export')")
	@PostMapping("/export")
	public void export(HttpServletResponse response, KpiResult result)
	{
		List<KpiResult> list = resultService.selectKpiResultList(result);
		ExcelUtil<KpiResult> util = new ExcelUtil<>(KpiResult.class);
		util.exportExcel(response, list, "考核结果");
	}

	@PreAuthorize("@ss.hasPermi('kpi:result:query')")
	@GetMapping("/{id}")
	public AjaxResult getInfo(@PathVariable Long id)
	{
		return success(resultService.selectKpiResultById(id));
	}

	@PreAuthorize("@ss.hasPermi('kpi:result:add')")
	@Log(title = "考核结果", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody KpiResult result)
	{
		result.setCreateBy(getUsername());
		return toAjax(resultService.insertKpiResult(result));
	}

	@PreAuthorize("@ss.hasPermi('kpi:result:edit')")
	@Log(title = "考核结果", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody KpiResult result)
	{
		result.setUpdateBy(getUsername());
		return toAjax(resultService.updateKpiResult(result));
	}

	@PreAuthorize("@ss.hasPermi('kpi:result:remove')")
	@Log(title = "考核结果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids)
	{
		return toAjax(resultService.deleteKpiResultByIds(ids));
	}
} 