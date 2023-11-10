package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.B2bRecrelation;
import com.ruoyi.system.service.IB2bRecrelationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 推荐关联Controller
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@RestController
@RequestMapping("/system/recRelation")
public class B2bRecrelationController extends BaseController
{
    @Autowired
    private IB2bRecrelationService b2bRecrelationService;

    /**
     * 查询推荐关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:recRelation:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bRecrelation b2bRecrelation)
    {
        startPage();
        List<B2bRecrelation> list = b2bRecrelationService.selectB2bRecrelationList(b2bRecrelation);
        return getDataTable(list);
    }

    /**
     * 导出推荐关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:recRelation:export')")
    @Log(title = "推荐关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bRecrelation b2bRecrelation)
    {
        List<B2bRecrelation> list = b2bRecrelationService.selectB2bRecrelationList(b2bRecrelation);
        ExcelUtil<B2bRecrelation> util = new ExcelUtil<B2bRecrelation>(B2bRecrelation.class);
        util.exportExcel(response, list, "推荐关联数据");
    }

    /**
     * 获取推荐关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:recRelation:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bRecrelationService.selectB2bRecrelationBySid(sid));
    }

    /**
     * 新增推荐关联
     */
    @PreAuthorize("@ss.hasPermi('system:recRelation:add')")
    @Log(title = "推荐关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bRecrelation b2bRecrelation)
    {
        return toAjax(b2bRecrelationService.insertB2bRecrelation(b2bRecrelation));
    }

    /**
     * 修改推荐关联
     */
    @PreAuthorize("@ss.hasPermi('system:recRelation:edit')")
    @Log(title = "推荐关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bRecrelation b2bRecrelation)
    {
        return toAjax(b2bRecrelationService.updateB2bRecrelation(b2bRecrelation));
    }

    /**
     * 删除推荐关联
     */
    @PreAuthorize("@ss.hasPermi('system:recRelation:remove')")
    @Log(title = "推荐关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bRecrelationService.deleteB2bRecrelationBySids(sids));
    }
}
