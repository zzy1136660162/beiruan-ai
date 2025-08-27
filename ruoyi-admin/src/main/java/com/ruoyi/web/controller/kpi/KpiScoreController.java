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
import com.ruoyi.kpi.domain.KpiScore;
import com.ruoyi.kpi.service.IKpiScoreService;

@RestController
@RequestMapping("/kpi/score")
public class KpiScoreController extends BaseController
{
	@Autowired
	private IKpiScoreService scoreService;

	@PreAuthorize("@ss.hasPermi('kpi:score:list')")
	@GetMapping("/list")
	public TableDataInfo list(KpiScore score)
	{
		startPage();
		List<KpiScore> list = scoreService.selectKpiScoreList(score);
		return getDataTable(list);
	}

	@Log(title = "评分记录", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('kpi:score:export')")
	@PostMapping("/export")
	public void export(HttpServletResponse response, KpiScore score)
	{
		List<KpiScore> list = scoreService.selectKpiScoreList(score);
		ExcelUtil<KpiScore> util = new ExcelUtil<>(KpiScore.class);
		util.exportExcel(response, list, "评分记录");
	}

	@PreAuthorize("@ss.hasPermi('kpi:score:query')")
	@GetMapping("/{id}")
	public AjaxResult getInfo(@PathVariable Long id)
	{
		return success(scoreService.selectKpiScoreById(id));
	}

	@PreAuthorize("@ss.hasPermi('kpi:score:add')")
	@Log(title = "评分记录", businessType = BusinessType.INSERT)
	@PostMapping
	public AjaxResult add(@Validated @RequestBody KpiScore score)
	{
		score.setCreateBy(getUsername());
		return toAjax(scoreService.insertKpiScore(score));
	}

	@PreAuthorize("@ss.hasPermi('kpi:score:edit')")
	@Log(title = "评分记录", businessType = BusinessType.UPDATE)
	@PutMapping
	public AjaxResult edit(@Validated @RequestBody KpiScore score)
	{
		score.setUpdateBy(getUsername());
		return toAjax(scoreService.updateKpiScore(score));
	}

	@PreAuthorize("@ss.hasPermi('kpi:score:remove')")
	@Log(title = "评分记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult remove(@PathVariable Long[] ids)
	{
		return toAjax(scoreService.deleteKpiScoreByIds(ids));
	}
} 