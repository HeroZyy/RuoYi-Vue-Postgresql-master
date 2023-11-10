package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.service.*;
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
import com.ruoyi.system.domain.B2bClass;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 分类模块Controller
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@RestController
@RequestMapping("/system/class")
public class B2bClassController extends BaseController
{
    @Autowired
    private IB2bClassService b2bClassService;
    @Autowired
    private IB2bProductService b2bProductService;

    @Autowired
    private IB2bSkuService b2bSkuService;

    @Autowired
    private IB2bProductPfilesService b2bProductPfilesService;

    @Autowired
    private IB2bProductDownloadService b2bProductDownloadService;

    @Autowired
    private IB2bProductFavoriteService b2bProductFavoriteService;

    @Autowired
    private IB2bSceneRelationService bSceneRelationService;

    @Autowired
    private IB2bSceneRelationService b2bSceneRelationService;

    @Autowired
    private IB2bProductDescService b2bProductDescService;

    @Autowired
    private IB2bProductFavoriteService productFavoriteService;
    /**
     * 获取所有分类下的所有产品，推荐排序后(b2b_recrelation)
     * 参数：无
     */


    /**
     * 查询分类模块列表
     */
//    @PreAuthorize("@ss.hasPermi('system:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bClass b2bClass)
    {
        startPage();
        List<B2bClass> list = b2bClassService.selectB2bClassList(b2bClass);
        return getDataTable(list);
    }

    /**
     * 获取某分类下的产品
     */
    @GetMapping("/getClassProduct")
    public AjaxResult getClassProduct(Long classId){
        return error();
    }
    /**
     * 导出分类模块列表
     */
    @PreAuthorize("@ss.hasPermi('system:class:export')")
    @Log(title = "分类模块", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bClass b2bClass)
    {
        List<B2bClass> list = b2bClassService.selectB2bClassList(b2bClass);
        ExcelUtil<B2bClass> util = new ExcelUtil<B2bClass>(B2bClass.class);
        util.exportExcel(response, list, "分类模块数据");
    }

    /**
     * 获取分类模块详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:class:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bClassService.selectB2bClassBySid(sid));
    }

    /**
     * 新增分类模块
     */
    @PreAuthorize("@ss.hasPermi('system:class:add')")
    @Log(title = "分类模块", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bClass b2bClass)
    {
        return toAjax(b2bClassService.insertB2bClass(b2bClass));
    }

    /**
     * 修改分类模块
     */
    @PreAuthorize("@ss.hasPermi('system:class:edit')")
    @Log(title = "分类模块", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bClass b2bClass)
    {
        return toAjax(b2bClassService.updateB2bClass(b2bClass));
    }

    /**
     * 删除分类模块
     */
    @PreAuthorize("@ss.hasPermi('system:class:remove')")
    @Log(title = "分类模块", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bClassService.deleteB2bClassBySids(sids));
    }
}
