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
import com.ruoyi.kpi.domain.KpiCycle;
import com.ruoyi.kpi.service.IKpiCycleService;

@RestController
@RequestMapping("/kpi/cycle")
public class KpiCycleController extends BaseController
{
    @Autowired
    private IKpiCycleService cycleService;

    @PreAuthorize("@ss.hasPermi('kpi:cycle:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiCycle cycle)
    {
        startPage();
        List<KpiCycle> list = cycleService.selectKpiCycleList(cycle);
        return getDataTable(list);
    }

    @Log(title = "考核周期", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('kpi:cycle:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiCycle cycle)
    {
        List<KpiCycle> list = cycleService.selectKpiCycleList(cycle);
        ExcelUtil<KpiCycle> util = new ExcelUtil<>(KpiCycle.class);
        util.exportExcel(response, list, "考核周期数据");
    }

    @PreAuthorize("@ss.hasPermi('kpi:cycle:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(cycleService.selectKpiCycleById(id));
    }

    @PreAuthorize("@ss.hasPermi('kpi:cycle:add')")
    @Log(title = "考核周期", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody KpiCycle cycle)
    {
        cycle.setCreateBy(getUsername());
        return toAjax(cycleService.insertKpiCycle(cycle));
    }

    @PreAuthorize("@ss.hasPermi('kpi:cycle:edit')")
    @Log(title = "考核周期", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody KpiCycle cycle)
    {
        cycle.setUpdateBy(getUsername());
        return toAjax(cycleService.updateKpiCycle(cycle));
    }

    @PreAuthorize("@ss.hasPermi('kpi:cycle:remove')")
    @Log(title = "考核周期", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cycleService.deleteKpiCycleByIds(ids));
    }
} 