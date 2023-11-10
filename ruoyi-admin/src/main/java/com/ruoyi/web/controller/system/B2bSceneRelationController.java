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
import com.ruoyi.system.domain.B2bSceneRelation;
import com.ruoyi.system.service.IB2bSceneRelationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 场景关联Controller
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@RestController
@RequestMapping("/system/relation")
public class B2bSceneRelationController extends BaseController
{
    @Autowired
    private IB2bSceneRelationService b2bSceneRelationService;

    /**
     * 查询场景关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bSceneRelation b2bSceneRelation)
    {
        startPage();
        List<B2bSceneRelation> list = b2bSceneRelationService.selectB2bSceneRelationList(b2bSceneRelation);
        return getDataTable(list);
    }

    /**
     * 导出场景关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:relation:export')")
    @Log(title = "场景关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bSceneRelation b2bSceneRelation)
    {
        List<B2bSceneRelation> list = b2bSceneRelationService.selectB2bSceneRelationList(b2bSceneRelation);
        ExcelUtil<B2bSceneRelation> util = new ExcelUtil<B2bSceneRelation>(B2bSceneRelation.class);
        util.exportExcel(response, list, "场景关联数据");
    }

    /**
     * 获取场景关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:relation:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bSceneRelationService.selectB2bSceneRelationBySid(sid));
    }

    /**
     * 新增场景关联
     */
    @PreAuthorize("@ss.hasPermi('system:relation:add')")
    @Log(title = "场景关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bSceneRelation b2bSceneRelation)
    {
        return toAjax(b2bSceneRelationService.insertB2bSceneRelation(b2bSceneRelation));
    }

    /**
     * 修改场景关联
     */
    @PreAuthorize("@ss.hasPermi('system:relation:edit')")
    @Log(title = "场景关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bSceneRelation b2bSceneRelation)
    {
        return toAjax(b2bSceneRelationService.updateB2bSceneRelation(b2bSceneRelation));
    }

    /**
     * 删除场景关联
     */
    @PreAuthorize("@ss.hasPermi('system:relation:remove')")
    @Log(title = "场景关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bSceneRelationService.deleteB2bSceneRelationBySids(sids));
    }
}
