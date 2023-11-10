package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import com.ruoyi.system.service.impl.B2bDownloadService;
import com.ruoyi.system.service.impl.SysRoleServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品基本信息Controller
 *
 * @author ruoyi
 * @date 2023-10-26
 */
@RestController
@RequestMapping("/system/product")
public class B2bProductController extends BaseController {
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
    private ISysRoleService sysRoleService;

    @Autowired
    private IB2bSceneRelationService bSceneRelationService;

    @Autowired
    private IB2bSceneRelationService b2bSceneRelationService;

    @Autowired
    private IB2bProductDescService b2bProductDescService;

    @Autowired
    private IB2bProductFavoriteService productFavoriteService;

    @Autowired
    private IB2bScprelationService b2bScprelationService;

    @Autowired
    private B2bDownloadService downloadService;
    /**
     * 搜索喜欢的产品，要有场景，分类筛选
     */
    @GetMapping("/getLoveInfoByClassIdAndProductId")
    public AjaxResult getLoveInfoByClassIdAndProductId(Long classId, Long sceneId,String productName) {
        SysUser user = getLoginUser().getUser();
        Long userId = user.getUserId();
        B2bProductFavorite favorite = new B2bProductFavorite();
        favorite.setMemId(userId);
        List<B2bProductFavorite> favorites = productFavoriteService.selectB2bProductFavoriteList(favorite);
        // 这里要先对收藏进行筛选，还没写
        ProductList productRes = new ProductList();
        List<B2bProduct> productlist = new ArrayList<>();
        List<B2bProductPro> reslist = new ArrayList<>();
        //根据产品名称筛选的产品Id
        List<Long> productIds = new ArrayList<>();
        B2bProduct b2bProduct1 = new B2bProduct();
        if(productName == null ) productName = "";
        b2bProduct1.setProdName(productName);
        List<B2bProduct> b2bProducts = b2bProductService.selectB2bProductList(b2bProduct1);
        //productIds 包含了所有筛选名称后的产品Id
        for (B2bProduct b2bProduct : b2bProducts) {
            productIds.add(b2bProduct.getSid());
        }
        //如果分类和场景都为空,那么直接模糊搜索产品名称
        if(sceneId == null && classId == null){
            ArrayList<B2bProductPro> resPro = new ArrayList<>();
            for (B2bProduct b2bProduct : b2bProducts) {
                B2bProductPro productPro = b2bProductService.getProductPro(b2bProduct.getSid());
                resPro.add(productPro);
            }
            productRes.setProductList(resPro);
            return success(productRes);
        }
        //如果分类和场景都不为空，先按照场景筛选再分类
        if (sceneId != null && classId != null) {
            B2bSceneRelation b2bSceneRelation = new B2bSceneRelation();
            b2bSceneRelation.setSceneId(sceneId);
            List<B2bSceneRelation> b2bSceneRelations = b2bSceneRelationService.selectB2bSceneRelationList(b2bSceneRelation);
            //某个场景下的产品Id
            List<Long> sceneIds = new ArrayList<>();
            for (B2bSceneRelation sceneRelation : b2bSceneRelations) {
                sceneIds.add(sceneRelation.getProdId());
            }
            List<Long> commonIds = new ArrayList<>();

            for (Long productId : productIds) {
                //筛选不符合要求的就删除掉
                if (sceneIds.contains(productId)) {
                    commonIds.add(productId);
                }
            }
            // 将 commonIds 赋值给 productIds
            productIds = commonIds;
            //按分类查找分类列表
            B2bProduct product = new B2bProduct();
            product.setClassId(classId);
            //该分类下的列表
            productlist = b2bProductService.selectB2bProductList(product);
            for (B2bProduct product1 : productlist) {
                Long sid = product1.getSid();
                //场景下包含该产品
                if (productIds.contains(sid)) {
                    B2bProductPro productPro = b2bProductService.getProductPro(sid);
                    reslist.add(productPro);
                }
            }
            productRes.setProductList(reslist);
            return success(productRes);
        }
        if(sceneId != null){
            B2bSceneRelation b2bSceneRelation = new B2bSceneRelation();
            b2bSceneRelation.setSceneId(sceneId);
            List<B2bSceneRelation> b2bSceneRelations = b2bSceneRelationService.selectB2bSceneRelationList(b2bSceneRelation);

            //某个场景下的产品Id
            List<Long> sceneIds = new ArrayList<>();
            for (B2bSceneRelation sceneRelation : b2bSceneRelations) {
                sceneIds.add(sceneRelation.getProdId());
            }
            List<Long> commonIds = new ArrayList<>();

            for (Long productId : productIds) {
                //筛选不符合要求的就删除掉
                if (sceneIds.contains(productId)) {
                    commonIds.add(productId);
                }
            }
            // 将 commonIds 赋值给 productIds
            productIds = commonIds;

            for (Long productId:productIds) {
                B2bProduct b2bProduct = b2bProductService.selectB2bProductBySid(productId);
                B2bProductPro productPro = b2bProductService.getProductPro(b2bProduct.getSid());
                reslist.add(productPro);
            }
            productRes.setProductList(reslist);
            return success(productRes);
        }

        if (classId != null) {
            B2bProduct product = new B2bProduct();
            product.setClassId(classId);
            productlist = b2bProductService.selectB2bProductList(product);
            for (B2bProduct product1 : productlist) {
                Long sid = product1.getSid();
                //场景下包含该产品
                if (productIds.contains(sid)) {
                    B2bProductPro productPro = b2bProductService.getProductPro(sid);
                    reslist.add(productPro);
                }
            }
            productRes.setProductList(reslist);
            return success(productRes);
        }
        return error("查询异常");
    }

    /**
     * 获取产品信息
     */
    @GetMapping("/ifSuperMember")
    public AjaxResult ifSuperMember()
    {
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();
        List<SysRole> sysRoles = sysRoleService.selectRolesByUserId(user.getUserId());
        for (SysRole sysRole : sysRoles) {
            if(sysRole.getRoleId() == 4L){
                return success( 1 );
            }
        }
        return success( 0 );
    }

//    /**
//     * 标准报价清单下载
//     */
//    @GetMapping("/download")
//    public ResponseEntity<Resource> download(Long productId) {
//
//        // 读取文件
//        ResponseEntity<Resource> resourceResponseEntity = downloadService.downloadFile(addr, fileName);
//
//        // 创建一个ResponseEntity对象，并设置响应内容、响应头和状态码
//        return resourceResponseEntity;
//    }

    /**
     * 根据分类id和场景id查询产品列表
     */
    @GetMapping("/getDetailInfoByClassIdAndProductId")
    public AjaxResult getDetailInfoByClassIdAndProductId(Long classId, Long sceneId ,String productName) {
        ProductList productRes = new ProductList();
        List<B2bProduct> productlist = new ArrayList<>();
        List<B2bProductPro> reslist = new ArrayList<>();
        //根据产品名称筛选的产品Id
        List<Long> Ids = new ArrayList<>();
        B2bProduct b2bProduct1 = new B2bProduct();
        if(productName == null ) productName = "";
        b2bProduct1.setProdName(productName);
        List<B2bProduct> b2bProducts = b2bProductService.selectB2bProductList(b2bProduct1);
        //productIds 包含了所有筛选名称后的产品Id
        for (B2bProduct b2bProduct : b2bProducts) {
            Ids.add(b2bProduct.getSid());
        }
        //获取某场景，某分类下的产品
        List<B2bScprelation> scps = b2bScprelationService.selectB2bProductBySceneIdAndClassId(sceneId, classId);
        ArrayList<Long> prodIds = new ArrayList<>();
        for (B2bScprelation scp : scps) {
            prodIds.add(scp.getProdId());
        }
        List<B2bProductPro> res = new ArrayList<>();
        for (Long prodId : prodIds) {
            for(Long id : Ids){
                if(Objects.equals(id, prodId)){
                    res.add(b2bProductService.getProductPro(prodId));
                }
            }
        }
        productRes.setProductList(res);
        return success(productRes);
//        //根据产品名称筛选的产品Id
//        List<Long> productIds = new ArrayList<>();
//        B2bProduct b2bProduct1 = new B2bProduct();
//        if(productName == null ) productName = "";
//        b2bProduct1.setProdName(productName);
//        List<B2bProduct> b2bProducts = b2bProductService.selectB2bProductList(b2bProduct1);
//        //productIds 包含了所有筛选名称后的产品Id
//        for (B2bProduct b2bProduct : b2bProducts) {
//            productIds.add(b2bProduct.getSid());
//        }
//        //如果分类和场景都为空,那么直接模糊搜索产品名称
//        if(sceneId == null && classId == null){
//            ArrayList<B2bProductPro> resPro = new ArrayList<>();
//            for (B2bProduct b2bProduct : b2bProducts) {
//                B2bProductPro productPro = b2bProductService.getProductPro(b2bProduct.getSid());
//                resPro.add(productPro);
//            }
//            productRes.setProductList(resPro);
//            return success(productRes);
//        }
//        //如果分类和场景都不为空，先按照场景筛选再分类
//        if (sceneId != null && classId != null) {
//            B2bSceneRelation b2bSceneRelation = new B2bSceneRelation();
//            b2bSceneRelation.setSceneId(sceneId);
//            List<B2bSceneRelation> b2bSceneRelations = b2bSceneRelationService.selectB2bSceneRelationList(b2bSceneRelation);
//            //某个场景下的产品Id
//            List<Long> sceneIds = new ArrayList<>();
//            for (B2bSceneRelation sceneRelation : b2bSceneRelations) {
//                sceneIds.add(sceneRelation.getProdId());
//            }
//            List<Long> commonIds = new ArrayList<>();
//
//            for (Long productId : productIds) {
//                //筛选不符合要求的就删除掉
//                if (sceneIds.contains(productId)) {
//                    commonIds.add(productId);
//                }
//            }
//            // 将 commonIds 赋值给 productIds
//            productIds = commonIds;
//            //按分类查找分类列表
//            B2bProduct product = new B2bProduct();
//            product.setClassId(classId);
//            //该分类下的列表
//            productlist = b2bProductService.selectB2bProductList(product);
//            for (B2bProduct product1 : productlist) {
//                Long sid = product1.getSid();
//                //场景下包含该产品
//                if (productIds.contains(sid)) {
//                    B2bProductPro productPro = b2bProductService.getProductPro(sid);
//                    reslist.add(productPro);
//                }
//            }
//            productRes.setProductList(reslist);
//            return success(productRes);
//        }
//        if(sceneId != null){
//            B2bSceneRelation b2bSceneRelation = new B2bSceneRelation();
//            b2bSceneRelation.setSceneId(sceneId);
//            List<B2bSceneRelation> b2bSceneRelations = b2bSceneRelationService.selectB2bSceneRelationList(b2bSceneRelation);
//
//            //某个场景下的产品Id
//            List<Long> sceneIds = new ArrayList<>();
//            for (B2bSceneRelation sceneRelation : b2bSceneRelations) {
//                sceneIds.add(sceneRelation.getProdId());
//            }
//            List<Long> commonIds = new ArrayList<>();
//
//            for (Long productId : productIds) {
//                //筛选不符合要求的就删除掉
//                if (sceneIds.contains(productId)) {
//                    commonIds.add(productId);
//                }
//            }
//            // 将 commonIds 赋值给 productIds
//            productIds = commonIds;
//
//            for (Long productId:productIds) {
//                B2bProduct b2bProduct = b2bProductService.selectB2bProductBySid(productId);
//                B2bProductPro productPro = b2bProductService.getProductPro(b2bProduct.getSid());
//                reslist.add(productPro);
//            }
//            productRes.setProductList(reslist);
//            return success(productRes);
//        }
//
//        if (classId != null) {
//            B2bProduct product = new B2bProduct();
//            product.setClassId(classId);
//            productlist = b2bProductService.selectB2bProductList(product);
//            for (B2bProduct product1 : productlist) {
//                Long sid = product1.getSid();
//                //场景下包含该产品
//                if (productIds.contains(sid)) {
//                    B2bProductPro productPro = b2bProductService.getProductPro(sid);
//                    reslist.add(productPro);
//                }
//            }
//            productRes.setProductList(reslist);
//            return success(productRes);
//        }
    }

    /**
     * 根据产品名称搜索，需要和上面的分类id和场景id查询产品列表返回一致
     */
//    @GetMapping("/getDetailInfoByProductName")
//    public AjaxResult getDetailInfoByProductName(Long classId, Long sceneId) {
//        ProductList productRes = new ProductList();
//        List<B2bProduct> productlist = new ArrayList<>();
//        List<B2bProductPro> reslist = new ArrayList<>();
//        //如果分类和场景都不为空，先分类再按照场景筛选
//        if (sceneId != null && classId != null) {
//            B2bSceneRelation b2bSceneRelation = new B2bSceneRelation();
//            b2bSceneRelation.setSceneId(sceneId);
//            List<B2bSceneRelation> b2bSceneRelations = b2bSceneRelationService.selectB2bSceneRelationList(b2bSceneRelation);
//            //某个产品下的产品Id
//            List<Long> productIds = new ArrayList<>();
//            for (B2bSceneRelation sceneRelation : b2bSceneRelations) {
//                productIds.add(sceneRelation.getProdId());
//            }
//            //按分类查找分类列表
//            B2bProduct product = new B2bProduct();
//            product.setClassId(classId);
//            //该分类下的列表
//            productlist = b2bProductService.selectB2bProductList(product);
//            for (B2bProduct product1 : productlist) {
//                Long sid = product1.getSid();
//                //场景下包含该产品
//                if (productIds.contains(sid)) {
//                    B2bProductPro productPro = b2bProductService.getProductPro(sid);
//                    reslist.add(productPro);
//                }
//            }
//
//            productRes.setProductList(reslist);
//            return success(productRes);
//        }
//        if (classId != null) {
//            B2bProduct product = new B2bProduct();
//            product.setClassId(classId);
//            productlist = b2bProductService.selectB2bProductList(product);
//            iter
//            productRes.setProductList(productlist);
//            return success(productRes);
//        }
//        if(sceneId != null){
//            //
//            B2bSceneRelation b2bSceneRelation = new B2bSceneRelation();
//            b2bSceneRelation.setSceneId(sceneId);
//            List<B2bSceneRelation> b2bSceneRelations = b2bSceneRelationService.selectB2bSceneRelationList(b2bSceneRelation);
//            //所有筛选场景下的Id列表
//            List<Long> productIds = new ArrayList<>();
//            for (B2bSceneRelation sceneRelation : b2bSceneRelations) {
//                productIds.add(sceneRelation.getProdId());
//            }
//
//            for (Long productId:productIds) {
//                B2bProduct b2bProduct = b2bProductService.selectB2bProductBySid(productId);
//                productlist.add(b2bProduct);
//            }
//            productRes.setProductList(productlist);
//            return success(productRes);
//        }
//        return error("查询异常");
//    }

    /**
     * 根据产品id查询产品详情
     */
    @GetMapping("/selectProductDetailByProductId")
    public AjaxResult getDetailInfo(Long productId) {
        return success(b2bProductService.getProductDetail(productId));
    }

    /**
     * 查询产品基本信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bProduct b2bProduct) {
        startPage();
        List<B2bProduct> list = b2bProductService.selectB2bProductList(b2bProduct);
        return getDataTable(list);
    }

    @PostMapping("/exportExcle")
    private void download(@RequestBody B2bProduct b2bProduct, HttpServletResponse response){//查询需要导出的数据
        System.out.println(b2bProduct.getClassId() + ": ClassId");
        List<B2bProduct> list = b2bProductService.selectB2bProductList(b2bProduct);
        //根据生成的数据生成excel表格
        ExcelUtil<B2bProduct> util = new ExcelUtil<B2bProduct>(B2bProduct.class);//这里修改了若依自带的ExcelUtil方法让其返回文件的完整路径而是不是原先的AjaxResult
        String filePath = util.getPath(list,"车辆列表");//调用了通用下载方法进行文件下载
        System.out.println(filePath + ": 车辆列表文件路径");
        ExcelUtil.download(filePath,Boolean.TRUE,response);
    }

    /**
     * 导出产品基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:product:export')")
    @Log(title = "产品基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bProduct b2bProduct) {
        List<B2bProduct> list = b2bProductService.selectB2bProductList(b2bProduct);
        ExcelUtil<B2bProduct> util = new ExcelUtil<B2bProduct>(B2bProduct.class);
        util.exportExcel(response, list, "产品基本信息数据");
        String filePath = util.getPath(list,"产品基本信息数据");//调用了通用下载方法进行文件下载
        System.out.println("xlsx文件绝对路径 : " + util.getAbsoluteFile(filePath));
    }

    /**
     * 获取产品基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult get(@PathVariable("sid") Long sid) {
        return success(b2bProductService.selectB2bProductBySid(sid));
    }

    /**
     * 新增产品基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:add')")
    @Log(title = "产品基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bProduct b2bProduct) {
        return toAjax(b2bProductService.insertB2bProduct(b2bProduct));
    }

    /**
     * 修改产品基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:edit')")
    @Log(title = "产品基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bProduct b2bProduct) {
        return toAjax(b2bProductService.updateB2bProduct(b2bProduct));
    }

    /**
     * 删除产品基本信息
     */
    @PreAuthorize("@ss.hasPermi('system:product:remove')")
    @Log(title = "产品基本信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids) {
        return toAjax(b2bProductService.deleteB2bProductBySids(sids));
    }
}
