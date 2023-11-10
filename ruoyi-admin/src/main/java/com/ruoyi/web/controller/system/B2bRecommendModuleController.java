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
import com.ruoyi.system.domain.B2bRecommendModule;
import com.ruoyi.system.service.IB2bRecommendModuleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 推荐模块Controller
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@RestController
@RequestMapping("/system/module")
public class B2bRecommendModuleController extends BaseController
{
    @Autowired
    private IB2bRecommendModuleService b2bRecommendModuleService;

    /**
     * 查询推荐模块列表
     */
    @PreAuthorize("@ss.hasPermi('system:module:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bRecommendModule b2bRecommendModule)
    {
        startPage();
        List<B2bRecommendModule> list = b2bRecommendModuleService.selectB2bRecommendModuleList(b2bRecommendModule);
        return getDataTable(list);
    }

    /**
     * 导出推荐模块列表
     */
    @PreAuthorize("@ss.hasPermi('system:module:export')")
    @Log(title = "推荐模块", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bRecommendModule b2bRecommendModule)
    {
        List<B2bRecommendModule> list = b2bRecommendModuleService.selectB2bRecommendModuleList(b2bRecommendModule);
        ExcelUtil<B2bRecommendModule> util = new ExcelUtil<B2bRecommendModule>(B2bRecommendModule.class);
        util.exportExcel(response, list, "推荐模块数据");
    }

    /**
     * 获取推荐模块详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:module:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bRecommendModuleService.selectB2bRecommendModuleBySid(sid));
    }

    /**
     * 新增推荐模块
     */
    @PreAuthorize("@ss.hasPermi('system:module:add')")
    @Log(title = "推荐模块", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bRecommendModule b2bRecommendModule)
    {
        return toAjax(b2bRecommendModuleService.insertB2bRecommendModule(b2bRecommendModule));
    }

    /**
     * 修改推荐模块
     */
    @PreAuthorize("@ss.hasPermi('system:module:edit')")
    @Log(title = "推荐模块", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bRecommendModule b2bRecommendModule)
    {
        return toAjax(b2bRecommendModuleService.updateB2bRecommendModule(b2bRecommendModule));
    }

    /**
     * 删除推荐模块
     */
    @PreAuthorize("@ss.hasPermi('system:module:remove')")
    @Log(title = "推荐模块", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bRecommendModuleService.deleteB2bRecommendModuleBySids(sids));
    }
}
