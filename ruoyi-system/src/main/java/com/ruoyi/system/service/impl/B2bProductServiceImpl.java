package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.apache.commons.math3.analysis.function.Exp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bProductMapper;

/**
 * 产品基本信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@Service
public class B2bProductServiceImpl implements IB2bProductService 
{
    @Autowired
    private B2bProductMapper b2bProductMapper;

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
    private IB2bClassService b2bClassService;

    @Autowired
    private IB2bSceneRelationService bSceneRelationService;

    @Autowired
    private IB2bSceneRelationService b2bSceneRelationService;

    @Autowired
    private IB2bProductDescService b2bProductDescService;

    @Autowired
    private IB2bProductFavoriteService productFavoriteService;

    @Autowired
    private IB2bSkuService skuService;
    @Override
    public B2bProductPro getProductPro(Long productId) {
        B2bProductPro ProductPro = new B2bProductPro();
        //product id
        ProductPro.setSid(productId);
        //desc
        B2bProductDesc b2bProductDesc = new B2bProductDesc();
        b2bProductDesc.setSid(productId);
        B2bProductDesc desc = b2bProductDescService.selectB2bProductDescBySid(productId);
        if(desc != null) {
            String describe = desc.getDescribe();
            ProductPro.setDesc(describe);
        }
        //img
        B2bProductPfiles Pfile = new B2bProductPfiles();
        Pfile.setSid(productId);
        Pfile.setOrderNum(1L);
        Pfile.setFileType("0");
        List<B2bProductPfiles> Pfiles = b2bProductPfilesService.selectB2bProductPfilesList(Pfile);
        for (B2bProductPfiles pfile : Pfiles) {
            ProductPro.setImg(pfile.getFileUrl());
            break;
        }
        // price
        B2bSku b2bSku = new B2bSku();
        b2bSku.setProdId(productId);
        b2bSku.setDefaultSku(1L);
        List<B2bSku> b2bSkus = skuService.selectB2bSkuList(b2bSku);
        for (B2bSku bSkus : b2bSkus) {
            ProductPro.setSkudefault(bSkus.getPrice());
            break;
        }
        //isFavorite
        Long isFavorite = 0L;

        try {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            B2bProductFavorite favorite = new B2bProductFavorite();
            favorite.setProdId(productId);
            favorite.setMemId(user.getUserId());
            List<B2bProductFavorite> favorites = b2bProductFavoriteService.selectB2bProductFavoriteList(favorite);
            if(!favorites.isEmpty()){
                isFavorite = 1L;
            }
        }
        catch (Exception ignored){
        }
        ProductPro.setIsfavorite(isFavorite);
        //manufacturer
        if(desc != null) {
            String manufacturer = desc.getMsg1();
            ProductPro.setManufacturer(manufacturer);
        }
        return ProductPro;
    }

    @Override
    public B2bProductDetail getProductDetail(Long productId) {
        B2bProduct b2bProduct = b2bProductService.selectB2bProductBySid(productId);
        B2bSku b2bSku = new B2bSku();
        b2bSku.setProdId(productId);
        List<B2bSku> b2bSkus = b2bSkuService.selectB2bSkuList(b2bSku);
        B2bProductDownload b2bProductDownload = new B2bProductDownload();
        b2bProductDownload.setProdId(productId);
        //产品下载
        List<B2bProductDownload> b2bProductDownloads = b2bProductDownloadService.selectB2bProductDownloadList(b2bProductDownload);
        List<DownloadMsg> dlmsg = new ArrayList<>();
        for (B2bProductDownload download : b2bProductDownloads) {
            DownloadMsg downloadMsg = new DownloadMsg();
            downloadMsg.setUrl(download.getUrl());
            downloadMsg.setAllowDl(download.getAllowDl());
            downloadMsg.setProdId(download.getProdId());
            //某个url的文件名称
            B2bProductPfiles p = new B2bProductPfiles();
            p.setFileUrl(download.getUrl());
            List<B2bProductPfiles> b2bProductPfiles = b2bProductPfilesService.selectB2bProductPfilesList(p);
            for (B2bProductPfiles b2bProductPfile : b2bProductPfiles) {
                if(Objects.equals(b2bProductPfile.getProductId(), productId)){
                    downloadMsg.setDlname(b2bProductPfile.getFileName());
                }
            }
            dlmsg.add(downloadMsg);
        }
        //pfiles
        B2bProductPfiles b2bProductPfile = new B2bProductPfiles();
        b2bProductPfile.setProductId(productId);
        List<B2bProductPfiles> b2bProductPfiles = b2bProductPfilesService.selectB2bProductPfilesList(b2bProductPfile);
        //desc
        B2bProductDesc b2bProductDesc = b2bProductDescService.selectB2bProductDescBySid(productId);
        String desc = "";
        if(b2bProductDesc != null) {
            desc = b2bProductDesc.getDescribe();
        }
        //favorites
        SysUser user = SecurityUtils.getLoginUser().getUser();
        B2bProductFavorite favorite1 = new B2bProductFavorite();
        favorite1.setMemId(user.getUserId());
        List<B2bProductFavorite> favorites = productFavoriteService.selectB2bProductFavoriteList(favorite1);
        Long isFavorite = -1L;
        if(!favorites.isEmpty()){
            for (B2bProductFavorite f:favorites) {
                if (f.getProdId().equals(productId)) {
                    isFavorite = 1L;
                    break;
                }
            }
        }
        //manufacturer
        String manufacturer = "";
        if(b2bProductDesc != null) manufacturer = b2bProductDesc.getMsg1();
        //标准报价清单
        B2bProductPfiles pf = new B2bProductPfiles();
        pf.setProductId(productId);
        pf.setFileType("3");
        List<B2bProductPfiles> pfs = b2bProductPfilesService.selectB2bProductPfilesList(pf);
        return new B2bProductDetail(b2bProduct, b2bSkus,  b2bProductPfiles , dlmsg , desc , isFavorite , manufacturer , pfs);
    }

    /**
     * 查询产品基本信息
     * 
     * @param sid 产品基本信息主键
     * @return 产品基本信息
     */
    @Override
    public B2bProduct selectB2bProductBySid(Long sid)
    {
        return b2bProductMapper.selectB2bProductBySid(sid);
    }

    /**
     * 查询产品基本信息列表
     * 
     * @param b2bProduct 产品基本信息
     * @return 产品基本信息
     */
    @Override
    public List<B2bProduct> selectB2bProductList(B2bProduct b2bProduct)
    {
        return b2bProductMapper.selectB2bProductList(b2bProduct);
    }

    /**
     * 新增产品基本信息
     * 
     * @param b2bProduct 产品基本信息
     * @return 结果
     */
    @Override
    public int insertB2bProduct(B2bProduct b2bProduct)
    {
        return b2bProductMapper.insertB2bProduct(b2bProduct);
    }

    /**
     * 修改产品基本信息
     * 
     * @param b2bProduct 产品基本信息
     * @return 结果
     */
    @Override
    public int updateB2bProduct(B2bProduct b2bProduct)
    {
        return b2bProductMapper.updateB2bProduct(b2bProduct);
    }

    /**
     * 批量删除产品基本信息
     * 
     * @param sids 需要删除的产品基本信息主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductBySids(Long[] sids)
    {
        return b2bProductMapper.deleteB2bProductBySids(sids);
    }

    /**
     * 删除产品基本信息信息
     * 
     * @param sid 产品基本信息主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductBySid(Long sid)
    {
        return b2bProductMapper.deleteB2bProductBySid(sid);
    }
}
