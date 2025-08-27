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
import com.ruoyi.kpi.domain.KpiResultSign;
import com.ruoyi.kpi.service.IKpiResultSignService;

@RestController
@RequestMapping("/kpi/resultSign")
public class KpiResultSignController extends BaseController
{
	@Autowired
	private IKpiResultSignService signService;

	@PreAuthorize("@ss.hasPermi('kpi:resultSign:list')")
	@GetMapping("/list")
	public TableDataInfo list(KpiResultSign sign)
	{
		startPage();
		List<KpiResultSign> list = signService.selectKpiResultSignList(sign);
		return getDataTable(list);
	}

	@Log(title = "结果签字", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('kpi:resultSign:export')")
	@PostMapping("/export")
	public void export(HttpServletResponse response, KpiResultSign sign)
	{
		List<KpiResultSign> list = signService.selectKpiResultSignList(sign);
		ExcelUtil<KpiResultSign> util = new ExcelUtil<>(KpiResultSign.class);
		util.exportExcel(response, list, "结果签字");
	}

	@PreAuthorize("@ss.hasPermi('kpi:resultSign:query')")
	@GetMapping("/{id}")
	public AjaxResult getInfo(@PathVariable Long id)
	{
		return success(signService.selectKpiResultSignById(id));
	}

	@PreAuthorize("@ss.hasPermi('kpi:resultSign:add')")
	@Log(title = "结果签字", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody KpiResultSign sign)
	{
		sign.setCreateBy(getUsername());
		return toAjax(signService.insertKpiResultSign(sign));
	}

	@PreAuthorize("@ss.hasPermi('kpi:resultSign:edit')")
	@Log(title = "结果签字", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody KpiResultSign sign)
	{
		sign.setUpdateBy(getUsername());
		return toAjax(signService.updateKpiResultSign(sign));
	}

	@PreAuthorize("@ss.hasPermi('kpi:resultSign:remove')")
	@Log(title = "结果签字", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids)
	{
		return toAjax(signService.deleteKpiResultSignByIds(ids));
	}

	@PreAuthorize("@ss.hasPermi('kpi:resultSign:add')")
	@Log(title = "结果签字", businessType = BusinessType.INSERT)
	@PostMapping("/sign/{resultId}")
	public AjaxResult sign(@PathVariable Long resultId)
	{
		KpiResultSign sign = new KpiResultSign();
		sign.setResultId(resultId);
		sign.setUserId(getUserId());
		sign.setSignStatus("1");
		sign.setCreateBy(getUsername());
		return toAjax(signService.insertKpiResultSign(sign));
	}
} 