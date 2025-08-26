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
import com.ruoyi.kpi.domain.KpiCycleWeight;
import com.ruoyi.kpi.service.IKpiCycleWeightService;

@RestController
@RequestMapping("/kpi/cycleWeight")
public class KpiCycleWeightController extends BaseController
{
    @Autowired
    private IKpiCycleWeightService weightService;

    @PreAuthorize("@ss.hasPermi('kpi:cycleWeight:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiCycleWeight weight)
    {
        startPage();
        List<KpiCycleWeight> list = weightService.selectKpiCycleWeightList(weight);
        return getDataTable(list);
    }

    @Log(title = "评分权重", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('kpi:cycleWeight:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiCycleWeight weight)
    {
        List<KpiCycleWeight> list = weightService.selectKpiCycleWeightList(weight);
        ExcelUtil<KpiCycleWeight> util = new ExcelUtil<>(KpiCycleWeight.class);
        util.exportExcel(response, list, "评分权重数据");
    }

    @PreAuthorize("@ss.hasPermi('kpi:cycleWeight:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(weightService.selectKpiCycleWeightById(id));
    }

    @PreAuthorize("@ss.hasPermi('kpi:cycleWeight:add')")
    @Log(title = "评分权重", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody KpiCycleWeight weight)
    {
        weight.setCreateBy(getUsername());
        return toAjax(weightService.insertKpiCycleWeight(weight));
    }

    @PreAuthorize("@ss.hasPermi('kpi:cycleWeight:edit')")
    @Log(title = "评分权重", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody KpiCycleWeight weight)
    {
        weight.setUpdateBy(getUsername());
        return toAjax(weightService.updateKpiCycleWeight(weight));
    }

    @PreAuthorize("@ss.hasPermi('kpi:cycleWeight:remove')")
    @Log(title = "评分权重", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(weightService.deleteKpiCycleWeightByIds(ids));
    }
} 