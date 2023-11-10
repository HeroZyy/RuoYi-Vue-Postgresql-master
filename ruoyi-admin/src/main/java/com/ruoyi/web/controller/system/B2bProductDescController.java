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
import com.ruoyi.system.domain.B2bProductDesc;
import com.ruoyi.system.service.IB2bProductDescService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品描述
Controller
 * 
 * @author ruoyi
 * @date 2023-10-30
 */
@RestController
@RequestMapping("/system/desc")
public class B2bProductDescController extends BaseController
{
    @Autowired
    private IB2bProductDescService b2bProductDescService;

    /**
     * 查询产品描述列表
     */
    @PreAuthorize("@ss.hasPermi('system:desc:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bProductDesc b2bProductDesc)
    {
        startPage();
        List<B2bProductDesc> list = b2bProductDescService.selectB2bProductDescList(b2bProductDesc);
        return getDataTable(list);
    }

    /**
     * 导出产品描述列表
     */
    @PreAuthorize("@ss.hasPermi('system:desc:export')")
    @Log(title = "产品描述", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bProductDesc b2bProductDesc)
    {
        List<B2bProductDesc> list = b2bProductDescService.selectB2bProductDescList(b2bProductDesc);
        ExcelUtil<B2bProductDesc> util = new ExcelUtil<B2bProductDesc>(B2bProductDesc.class);
        util.exportExcel(response, list, "产品描述数据");
    }

    /**
     * 获取产品描述详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:desc:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bProductDescService.selectB2bProductDescBySid(sid));
    }

    /**
     * 新增产品描述
     */
    @PreAuthorize("@ss.hasPermi('system:desc:add')")
    @Log(title = "产品描述", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bProductDesc b2bProductDesc)
    {
        return toAjax(b2bProductDescService.insertB2bProductDesc(b2bProductDesc));
    }

    /**
     * 修改产品描述

     */
    @PreAuthorize("@ss.hasPermi('system:desc:edit')")
    @Log(title = "产品描述", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bProductDesc b2bProductDesc)
    {
        return toAjax(b2bProductDescService.updateB2bProductDesc(b2bProductDesc));
    }

    /**
     * 删除产品描述

     */
    @PreAuthorize("@ss.hasPermi('system:desc:remove')")
    @Log(title = "产品描述", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bProductDescService.deleteB2bProductDescBySids(sids));
    }
}
