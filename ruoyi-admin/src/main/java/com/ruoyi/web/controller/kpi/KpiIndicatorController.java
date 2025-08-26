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
import com.ruoyi.kpi.domain.KpiIndicator;
import com.ruoyi.kpi.service.IKpiIndicatorService;

@RestController
@RequestMapping("/kpi/indicator")
public class KpiIndicatorController extends BaseController
{
    @Autowired
    private IKpiIndicatorService indicatorService;

    @PreAuthorize("@ss.hasPermi('kpi:indicator:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiIndicator indicator)
    {
        startPage();
        List<KpiIndicator> list = indicatorService.selectKpiIndicatorList(indicator);
        return getDataTable(list);
    }

    @Log(title = "指标库", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('kpi:indicator:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiIndicator indicator)
    {
        List<KpiIndicator> list = indicatorService.selectKpiIndicatorList(indicator);
        ExcelUtil<KpiIndicator> util = new ExcelUtil<>(KpiIndicator.class);
        util.exportExcel(response, list, "指标库数据");
    }

    @PreAuthorize("@ss.hasPermi('kpi:indicator:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(indicatorService.selectKpiIndicatorById(id));
    }

    @PreAuthorize("@ss.hasPermi('kpi:indicator:add')")
    @Log(title = "指标库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody KpiIndicator indicator)
    {
        indicator.setCreateBy(getUsername());
        return toAjax(indicatorService.insertKpiIndicator(indicator));
    }

    @PreAuthorize("@ss.hasPermi('kpi:indicator:edit')")
    @Log(title = "指标库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody KpiIndicator indicator)
    {
        indicator.setUpdateBy(getUsername());
        return toAjax(indicatorService.updateKpiIndicator(indicator));
    }

    @PreAuthorize("@ss.hasPermi('kpi:indicator:remove')")
    @Log(title = "指标库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(indicatorService.deleteKpiIndicatorByIds(ids));
    }
} 