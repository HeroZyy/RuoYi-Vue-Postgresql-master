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
import com.ruoyi.system.domain.B2bProductPfiles;
import com.ruoyi.system.service.IB2bProductPfilesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品文件Controller
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@RestController
@RequestMapping("/system/pfiles")
public class B2bProductPfilesController extends BaseController
{
    @Autowired
    private IB2bProductPfilesService b2bProductPfilesService;

    /**
     * 查询产品文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:pfiles:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bProductPfiles b2bProductPfiles)
    {
        startPage();
        List<B2bProductPfiles> list = b2bProductPfilesService.selectB2bProductPfilesList(b2bProductPfiles);
        return getDataTable(list);
    }

    /**
     * 导出产品文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:pfiles:export')")
    @Log(title = "产品文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bProductPfiles b2bProductPfiles)
    {
        List<B2bProductPfiles> list = b2bProductPfilesService.selectB2bProductPfilesList(b2bProductPfiles);
        ExcelUtil<B2bProductPfiles> util = new ExcelUtil<B2bProductPfiles>(B2bProductPfiles.class);
        util.exportExcel(response, list, "产品文件数据");
    }

    /**
     * 获取产品文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:pfiles:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bProductPfilesService.selectB2bProductPfilesBySid(sid));
    }

    /**
     * 新增产品文件
     */
    @PreAuthorize("@ss.hasPermi('system:pfiles:add')")
    @Log(title = "产品文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bProductPfiles b2bProductPfiles)
    {
        return toAjax(b2bProductPfilesService.insertB2bProductPfiles(b2bProductPfiles));
    }

    /**
     * 修改产品文件
     */
    @PreAuthorize("@ss.hasPermi('system:pfiles:edit')")
    @Log(title = "产品文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bProductPfiles b2bProductPfiles)
    {
        return toAjax(b2bProductPfilesService.updateB2bProductPfiles(b2bProductPfiles));
    }

    /**
     * 删除产品文件
     */
    @PreAuthorize("@ss.hasPermi('system:pfiles:remove')")
    @Log(title = "产品文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bProductPfilesService.deleteB2bProductPfilesBySids(sids));
    }
}
