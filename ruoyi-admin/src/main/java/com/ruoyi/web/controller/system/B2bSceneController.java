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
import com.ruoyi.system.domain.B2bScene;
import com.ruoyi.system.service.IB2bSceneService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 场景模块Controller
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@RestController
@RequestMapping("/system/scene")
public class B2bSceneController extends BaseController
{
    @Autowired
    private IB2bSceneService b2bSceneService;

    /**
     * 查询场景模块列表
     */
//    @PreAuthorize("@ss.hasPermi('system:scene:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bScene b2bScene)
    {
        startPage();
        List<B2bScene> list = b2bSceneService.selectB2bSceneList(b2bScene);
        return getDataTable(list);
    }

    /**
     * 场景下的推荐列表
     */
    @GetMapping("/listRecommend")
    public TableDataInfo listRecommend(B2bScene b2bScene)
    {
        startPage();
        List<B2bScene> list = b2bSceneService.selectB2bSceneList(b2bScene);
        return getDataTable(list);
    }

    /**
     * 导出场景模块列表
     */
    @PreAuthorize("@ss.hasPermi('system:scene:export')")
    @Log(title = "场景模块", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bScene b2bScene)
    {
        List<B2bScene> list = b2bSceneService.selectB2bSceneList(b2bScene);
        ExcelUtil<B2bScene> util = new ExcelUtil<B2bScene>(B2bScene.class);
        util.exportExcel(response, list, "场景模块数据");
    }

    /**
     * 获取场景模块详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:scene:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bSceneService.selectB2bSceneBySid(sid));
    }

    /**
     * 新增场景模块
     */
    @PreAuthorize("@ss.hasPermi('system:scene:add')")
    @Log(title = "场景模块", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bScene b2bScene)
    {
        return toAjax(b2bSceneService.insertB2bScene(b2bScene));
    }

    /**
     * 修改场景模块
     */
    @PreAuthorize("@ss.hasPermi('system:scene:edit')")
    @Log(title = "场景模块", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bScene b2bScene)
    {
        return toAjax(b2bSceneService.updateB2bScene(b2bScene));
    }

    /**
     * 删除场景模块
     */
    @PreAuthorize("@ss.hasPermi('system:scene:remove')")
    @Log(title = "场景模块", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bSceneService.deleteB2bSceneBySids(sids));
    }
}
