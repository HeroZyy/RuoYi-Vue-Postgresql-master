package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.IB2bProductDescService;
import com.ruoyi.system.service.IB2bProductPfilesService;
import com.ruoyi.system.service.IB2bProductService;
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
import com.ruoyi.system.service.IB2bProductFavoriteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品收藏Controller
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@RestController
@RequestMapping("/system/favorite")
public class B2bProductFavoriteController extends BaseController
{
    @Autowired
    private IB2bProductFavoriteService b2bProductFavoriteService;

    @Autowired
    private IB2bProductService productService;

    @Autowired
    private IB2bProductPfilesService pfilesService;

    @Autowired
    private IB2bProductDescService b2bProductDescService;

    /**
     * 用户收藏产品
     */
    @GetMapping("/loveProduct")
    public AjaxResult loveProduct(Long productId){
        B2bProductFavorite favorite = new B2bProductFavorite();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        favorite.setMemId(user.getUserId());
        favorite.setProdId(productId);
        favorite.setSkuId(-1L);
        List<B2bProductFavorite> favorites = b2bProductFavoriteService.selectB2bProductFavoriteList(favorite);
        if(!favorites.isEmpty()) return error("您已收藏该产品");

        int insertB2bProductFavorite = b2bProductFavoriteService.insertB2bProductFavorite(favorite);
        if(insertB2bProductFavorite>0) return success("收藏成功");
        return error("收藏失败");
    }

    /**
     * 用户取消收藏产品
     */
    @GetMapping("/cancelLove")
    public AjaxResult cancelLove(Long productId){
        B2bProductFavorite favorite = new B2bProductFavorite();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        favorite.setMemId(user.getUserId());
        favorite.setProdId(productId);
        List<B2bProductFavorite> favorites = b2bProductFavoriteService.selectB2bProductFavoriteList(favorite);
        if(favorites.isEmpty()) return error("您未收藏该产品");
        for (B2bProductFavorite f:
             favorites) {
            if(Objects.equals(f.getProdId(), productId)){
                int deleteB2bProductFavoriteBySid = b2bProductFavoriteService.deleteB2bProductFavoriteBySid(f.getSid());
                if(deleteB2bProductFavoriteBySid>0) return success("取消收藏成功");
            }
        }
        return error("取消收藏失败");
    }
    /**
     * 查询某个用户的收藏列表
     */
    @GetMapping("/userlist")
    public TableDataInfo userlist()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long memId = user.getUserId();
        B2bProductFavorite b2bProductFavorite = new B2bProductFavorite();
        b2bProductFavorite.setMemId(memId);
        startPage();
        List<B2bProductFavorite> favorites = b2bProductFavoriteService.selectB2bProductFavoriteList(b2bProductFavorite);
        List<B2bFavoriteProduct> favoriteProduct = new ArrayList<>();
        for (B2bProductFavorite favorite: favorites) {
            B2bFavoriteProduct newProductFavorite = new B2bFavoriteProduct();
            B2bProduct b2bProduct = productService.selectB2bProductBySid(favorite.getProdId());
            if(b2bProduct != null){
                newProductFavorite.setProd_id(b2bProduct.getSid());
                newProductFavorite.setName(b2bProduct.getProdName());
            }
            //获取该产品的封面
            B2bProductPfiles b2bProductPfiles = pfilesService.selectB2bProductPfilesBySid(favorite.getProdId());
            if(b2bProductPfiles != null) newProductFavorite.setImg(b2bProductPfiles.getFileUrl());
            //获取产品的详情介绍
            B2bProductDesc b2bProductDesc = b2bProductDescService.selectB2bProductDescBySid(favorite.getProdId());
            if(b2bProductDesc != null) {
                String describe = b2bProductDesc.getDescribe();
                newProductFavorite.setDesc(describe);
            }
            favoriteProduct.add(newProductFavorite);
        }
        return getDataTable(favoriteProduct);
    }

    /**
     * 查询产品收藏列表
     */
//    @PreAuthorize("@ss.hasPermi('system:favorite:list')")
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
