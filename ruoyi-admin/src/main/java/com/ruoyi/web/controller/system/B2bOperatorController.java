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
import com.ruoyi.system.domain.B2bOperator;
import com.ruoyi.system.service.IB2bOperatorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 操作员Controller
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@RestController
@RequestMapping("/system/operator")
public class B2bOperatorController extends BaseController
{
    @Autowired
    private IB2bOperatorService b2bOperatorService;

    /**
     * 查询操作员列表
     */
    @PreAuthorize("@ss.hasPermi('system:operator:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bOperator b2bOperator)
    {
        startPage();
        List<B2bOperator> list = b2bOperatorService.selectB2bOperatorList(b2bOperator);
        return getDataTable(list);
    }

    /**
     * 导出操作员列表
     */
    @PreAuthorize("@ss.hasPermi('system:operator:export')")
    @Log(title = "操作员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bOperator b2bOperator)
    {
        List<B2bOperator> list = b2bOperatorService.selectB2bOperatorList(b2bOperator);
        ExcelUtil<B2bOperator> util = new ExcelUtil<B2bOperator>(B2bOperator.class);
        util.exportExcel(response, list, "操作员数据");
    }

    /**
     * 获取操作员详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:operator:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bOperatorService.selectB2bOperatorBySid(sid));
    }

    /**
     * 新增操作员
     */
    @PreAuthorize("@ss.hasPermi('system:operator:add')")
    @Log(title = "操作员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bOperator b2bOperator)
    {
        return toAjax(b2bOperatorService.insertB2bOperator(b2bOperator));
    }

    /**
     * 修改操作员
     */
    @PreAuthorize("@ss.hasPermi('system:operator:edit')")
    @Log(title = "操作员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bOperator b2bOperator)
    {
        return toAjax(b2bOperatorService.updateB2bOperator(b2bOperator));
    }

    /**
     * 删除操作员
     */
    @PreAuthorize("@ss.hasPermi('system:operator:remove')")
    @Log(title = "操作员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bOperatorService.deleteB2bOperatorBySids(sids));
    }
}
