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
import com.ruoyi.system.domain.B2bProductDownload;
import com.ruoyi.system.service.IB2bProductDownloadService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品资料下载信息Controller
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@RestController
@RequestMapping("/system/download")
public class B2bProductDownloadController extends BaseController
{
    @Autowired
    private IB2bProductDownloadService b2bProductDownloadService;

    /**
     * 查询产品资料下载信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:download:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bProductDownload b2bProductDownload)
    {
        startPage();
        List<B2bProductDownload> list = b2bProductDownloadService.selectB2bProductDownloadList(b2bProductDownload);
        return getDataTable(list);
    }

    /**
     * 导出产品资料下载信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:download:export')")
    @Log(title = "产品资料下载信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bProductDownload b2bProductDownload)
    {
        List<B2bProductDownload> list = b2bProductDownloadService.selectB2bProductDownloadList(b2bProductDownload);
        ExcelUtil<B2bProductDownload> util = new ExcelUtil<B2bProductDownload>(B2bProductDownload.class);
        util.exportExcel(response, list, "产品资料下载信息数据");
    }

    /**
     * 获取产品资料下载信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:download:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bProductDownloadService.selectB2bProductDownloadBySid(sid));
    }

    /**
     * 新增产品资料下载信息
     */
    @PreAuthorize("@ss.hasPermi('system:download:add')")
    @Log(title = "产品资料下载信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bProductDownload b2bProductDownload)
    {
        return toAjax(b2bProductDownloadService.insertB2bProductDownload(b2bProductDownload));
    }

    /**
     * 修改产品资料下载信息
     */
    @PreAuthorize("@ss.hasPermi('system:download:edit')")
    @Log(title = "产品资料下载信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bProductDownload b2bProductDownload)
    {
        return toAjax(b2bProductDownloadService.updateB2bProductDownload(b2bProductDownload));
    }

    /**
     * 删除产品资料下载信息
     */
    @PreAuthorize("@ss.hasPermi('system:download:remove')")
    @Log(title = "产品资料下载信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bProductDownloadService.deleteB2bProductDownloadBySids(sids));
    }
}
