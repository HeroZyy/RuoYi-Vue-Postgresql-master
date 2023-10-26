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
import com.ruoyi.system.domain.B2bProduct;
import com.ruoyi.system.service.IB2bProductService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品基本信息Controller
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@RestController
@RequestMapping("/system/product")
public class B2bProductController extends BaseController
{
    @Autowired
    private IB2bProductService b2bProductService;

    /**
     * 查询产品基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bProduct b2bProduct)
    {
        startPage();
        List<B2bProduct> list = b2bProductService.selectB2bProductList(b2bProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:product:export')")
    @Log(title = "产品基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bProduct b2bProduct)
    {
        List<B2bProduct> list = b2bProductService.selectB2bProductList(b2bProduct);
        ExcelUtil<B2bProduct> util = new ExcelUtil<B2bProduct>(B2bProduct.class);
        util.exportExcel(response, list, "产品基本信息数据");
    }

    /**
     * 获取产品基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bProductService.selectB2bProductBySid(sid));
    }

    /**
     * 新增产品基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:add')")
    @Log(title = "产品基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bProduct b2bProduct)
    {
        return toAjax(b2bProductService.insertB2bProduct(b2bProduct));
    }

    /**
     * 修改产品基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:edit')")
    @Log(title = "产品基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bProduct b2bProduct)
    {
        return toAjax(b2bProductService.updateB2bProduct(b2bProduct));
    }

    /**
     * 删除产品基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:remove')")
    @Log(title = "产品基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bProductService.deleteB2bProductBySids(sids));
    }
}
