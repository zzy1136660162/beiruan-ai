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
import com.ruoyi.kpi.domain.KpiIndicatorCategory;
import com.ruoyi.kpi.service.IKpiIndicatorCategoryService;

@RestController
@RequestMapping("/kpi/indicatorCategory")
public class KpiIndicatorCategoryController extends BaseController
{
    @Autowired
    private IKpiIndicatorCategoryService categoryService;

    @PreAuthorize("@ss.hasPermi('kpi:indicatorCategory:list')")
    @GetMapping("/list")
    public TableDataInfo list(KpiIndicatorCategory category)
    {
        startPage();
        List<KpiIndicatorCategory> list = categoryService.selectKpiIndicatorCategoryList(category);
        return getDataTable(list);
    }

    @Log(title = "指标分类", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('kpi:indicatorCategory:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, KpiIndicatorCategory category)
    {
        List<KpiIndicatorCategory> list = categoryService.selectKpiIndicatorCategoryList(category);
        ExcelUtil<KpiIndicatorCategory> util = new ExcelUtil<>(KpiIndicatorCategory.class);
        util.exportExcel(response, list, "指标分类数据");
    }

    @PreAuthorize("@ss.hasPermi('kpi:indicatorCategory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(categoryService.selectKpiIndicatorCategoryById(id));
    }

    @PreAuthorize("@ss.hasPermi('kpi:indicatorCategory:add')")
    @Log(title = "指标分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody KpiIndicatorCategory category)
    {
        category.setCreateBy(getUsername());
        return toAjax(categoryService.insertKpiIndicatorCategory(category));
    }

    @PreAuthorize("@ss.hasPermi('kpi:indicatorCategory:edit')")
    @Log(title = "指标分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody KpiIndicatorCategory category)
    {
        category.setUpdateBy(getUsername());
        return toAjax(categoryService.updateKpiIndicatorCategory(category));
    }

    @PreAuthorize("@ss.hasPermi('kpi:indicatorCategory:remove')")
    @Log(title = "指标分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryService.deleteKpiIndicatorCategoryByIds(ids));
    }
} 