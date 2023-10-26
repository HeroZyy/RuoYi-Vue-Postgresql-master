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
import com.ruoyi.system.domain.B2bProductFavorite;
import com.ruoyi.system.service.IB2bProductFavoriteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品收藏Controller
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@RestController
@RequestMapping("/system/favorite")
public class B2bProductFavoriteController extends BaseController
{
    @Autowired
    private IB2bProductFavoriteService b2bProductFavoriteService;

    /**
     * 查询产品收藏列表
     */
    @PreAuthorize("@ss.hasPermi('system:favorite:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bProductFavorite b2bProductFavorite)
    {
        startPage();
        List<B2bProductFavorite> list = b2bProductFavoriteService.selectB2bProductFavoriteList(b2bProductFavorite);
        return getDataTable(list);
    }

    /**
     * 导出产品收藏列表
     */
    @PreAuthorize("@ss.hasPermi('system:favorite:export')")
    @Log(title = "产品收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bProductFavorite b2bProductFavorite)
    {
        List<B2bProductFavorite> list = b2bProductFavoriteService.selectB2bProductFavoriteList(b2bProductFavorite);
        ExcelUtil<B2bProductFavorite> util = new ExcelUtil<B2bProductFavorite>(B2bProductFavorite.class);
        util.exportExcel(response, list, "产品收藏数据");
    }

    /**
     * 获取产品收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:favorite:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bProductFavoriteService.selectB2bProductFavoriteBySid(sid));
    }

    /**
     * 新增产品收藏
     */
    @PreAuthorize("@ss.hasPermi('system:favorite:add')")
    @Log(title = "产品收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bProductFavorite b2bProductFavorite)
    {
        return toAjax(b2bProductFavoriteService.insertB2bProductFavorite(b2bProductFavorite));
    }

    /**
     * 修改产品收藏
     */
    @PreAuthorize("@ss.hasPermi('system:favorite:edit')")
    @Log(title = "产品收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bProductFavorite b2bProductFavorite)
    {
        return toAjax(b2bProductFavoriteService.updateB2bProductFavorite(b2bProductFavorite));
    }

    /**
     * 删除产品收藏
     */
    @PreAuthorize("@ss.hasPermi('system:favorite:remove')")
    @Log(title = "产品收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bProductFavoriteService.deleteB2bProductFavoriteBySids(sids));
    }
}
