package com.ruoyi.web.controller.system;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.*;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 场景分类产品关联Controller
 * 
 * @author ruoyi
 * @date 2023-11-06
 */
@RestController
@RequestMapping("/system/scprelation")
public class B2bScprelationController extends BaseController
{
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
    private IB2bScprelationService b2bScprelationService;

    /**
     * 通过场景获取分类
     *
     */
    @GetMapping("/getClassBySceneId")
    public AjaxResult getClassBySceneId(Long sceneId ){
        List<B2bClass> classes = b2bScprelationService.getClassBySceneId(sceneId);

        return success(classes);
    }
    /**
     * 获取某场景，某分类下的产品
     * 参数：场景id，分类id
     */
    @GetMapping("/getSceneClass")
    public AjaxResult getSceneClass(Long sceneId , Long classId ,String productName)
    {

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
//        List<B2bSceneWithClass> prodsRes = b2bScprelationService.getProd(Ids);
//        List<B2bSceneWithClass> sceneRes = new ArrayList<>();
//        List<B2bSceneWithClass> classRes = new ArrayList<>();
//        //在场景Id筛选
//        if(sceneId != null){
//            for (B2bSceneWithClass sceneWithClass : prodsRes) {
//                if(Objects.equals(sceneWithClass.getScene().getSid(), sceneId)){
//                    sceneRes.add(sceneWithClass);
//                }
//            }
//        }
//        else{sceneRes.addAll(prodsRes);}
//        //在分类Id筛选
//        if(classId != null){
//            //遍历每一个场景下的分类
//            for (B2bSceneWithClass sceneWithClass : prodsRes) {
//                //所有的上层筛选结果,不满足classId相等的就删掉
//                List<B2bClassWithProdPro> classWithProdPros = sceneWithClass.getClassWithProdPros();
//                //满足classId的
//                List<B2bClassWithProdPro> resSatisfyClass = sceneWithClass.getClassWithProdPros();
//                for (B2bClassWithProdPro classWithProdPro : classWithProdPros) {
//                    if(Objects.equals(classWithProdPro.getClazz().getSid(), classId)){
//                        resSatisfyClass.add(classWithProdPro);
//                    }
//                }
//                //所有满足该场景下的分类的结果
//                classRes.add(new B2bSceneWithClass(resSatisfyClass ,sceneWithClass.getScene()));
//            }
//        }
//        else{classRes.addAll(sceneRes);}

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
        return success(res);
    }

//    /**
//     * 查询场景下的所有分类
//     */
//
//    @GetMapping("/getClassBySceneId")
//    public AjaxResult getClassBySceneId(Long sceneId){
////        List<B2bScprelation> scps = b2bScprelationService.selectB2bProductBySceneIdAndClassId(sceneId, classId);
//        return success();
//    }

    /**
     * 查询场景分类产品关联列表
     */
//    @PreAuthorize("@ss.hasPermi('system:scprelation:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bScprelation b2bScprelation)
    {
        startPage();
        List<B2bScprelation> list = b2bScprelationService.selectB2bScprelationList(b2bScprelation);
        return getDataTable(list);
    }

    /**
     * 导出场景分类产品关联列表
     */
//    @PreAuthorize("@ss.hasPermi('system:scprelation:export')")
    @Log(title = "场景分类产品关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bScprelation b2bScprelation)
    {
        List<B2bScprelation> list = b2bScprelationService.selectB2bScprelationList(b2bScprelation);
        ExcelUtil<B2bScprelation> util = new ExcelUtil<B2bScprelation>(B2bScprelation.class);
        util.exportExcel(response, list, "场景分类产品关联数据");
    }

    /**
     * 获取场景分类产品关联详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:scprelation:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bScprelationService.selectB2bScprelationBySid(sid));
    }

    /**
     * 新增场景分类产品关联
     */
//    @PreAuthorize("@ss.hasPermi('system:scprelation:add')")
    @Log(title = "场景分类产品关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bScprelation b2bScprelation)
    {
        return toAjax(b2bScprelationService.insertB2bScprelation(b2bScprelation));
    }

    /**
     * 修改场景分类产品关联
     */
//    @PreAuthorize("@ss.hasPermi('system:scprelation:edit')")
    @Log(title = "场景分类产品关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bScprelation b2bScprelation)
    {
        return toAjax(b2bScprelationService.updateB2bScprelation(b2bScprelation));
    }

    /**
     * 删除场景分类产品关联
     */
    @PreAuthorize("@ss.hasPermi('system:scprelation:remove')")
    @Log(title = "场景分类产品关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bScprelationService.deleteB2bScprelationBySids(sids));
    }
}
