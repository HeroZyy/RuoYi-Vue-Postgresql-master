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
import com.ruoyi.system.domain.B2bSku;
import com.ruoyi.system.service.IB2bSkuService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品单元Controller
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@RestController
@RequestMapping("/system/sku")
public class B2bSkuController extends BaseController
{
    @Autowired
    private IB2bSkuService b2bSkuService;

    /**
     * 查询产品单元列表
     */
    @PreAuthorize("@ss.hasPermi('system:sku:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bSku b2bSku)
    {
        startPage();
        List<B2bSku> list = b2bSkuService.selectB2bSkuList(b2bSku);
        return getDataTable(list);
    }

    /**
     * 导出产品单元列表
     */
    @PreAuthorize("@ss.hasPermi('system:sku:export')")
    @Log(title = "产品单元", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bSku b2bSku)
    {
        List<B2bSku> list = b2bSkuService.selectB2bSkuList(b2bSku);
        ExcelUtil<B2bSku> util = new ExcelUtil<B2bSku>(B2bSku.class);
        util.exportExcel(response, list, "产品单元数据");
    }

    /**
     * 获取产品单元详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sku:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bSkuService.selectB2bSkuBySid(sid));
    }

    /**
     * 新增产品单元
     */
    @PreAuthorize("@ss.hasPermi('system:sku:add')")
    @Log(title = "产品单元", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bSku b2bSku)
    {
        return toAjax(b2bSkuService.insertB2bSku(b2bSku));
    }

    /**
     * 修改产品单元
     */
    @PreAuthorize("@ss.hasPermi('system:sku:edit')")
    @Log(title = "产品单元", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bSku b2bSku)
    {
        return toAjax(b2bSkuService.updateB2bSku(b2bSku));
    }

    /**
     * 删除产品单元
     */
    @PreAuthorize("@ss.hasPermi('system:sku:remove')")
    @Log(title = "产品单元", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bSkuService.deleteB2bSkuBySids(sids));
    }
}
