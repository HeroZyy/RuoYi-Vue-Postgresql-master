package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
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
 * 推荐关联Controller
 * 
 * @author ruoyi
 * @date 2023-10-30
 */
@RestController
@RequestMapping("/system/recommendrel")
public class B2bRecommendrelController extends BaseController
{
    @Autowired
    private IB2bRecommendrelService b2bRecommendrelService;

    @Autowired
    private IB2bProductService productService;

    @Autowired
    private IB2bClassService classService;

    @Autowired
    private IB2bSceneService sceneService;

    @Autowired
    private IB2bProductPfilesService pfilesService;

    @Autowired
    private IB2bSceneService b2bSceneService;

    /**
     * 热点推荐列表
     */
    @GetMapping("/redWord")
    public AjaxResult redWord(Long rec_id , Long scene_id , Long class_id)
    {
        B2bRecommendrel b2bRecommendrel = new B2bRecommendrel();
        b2bRecommendrel.setRecId(rec_id);
        List<B2bRedRecomend> res = new ArrayList<>();

        //1热点3精品4感兴趣5场景，获取推荐信息
        //1热点下可能会返回类型和场景名称，其他均不会返回类型和场景，都返回产品
        List<B2bRecommendrel> b2bRecommendrels = b2bRecommendrelService.selectB2bRecommendrelList(b2bRecommendrel);
        //遍历推荐关系表
        for (B2bRecommendrel r : b2bRecommendrels) {
            B2bRedRecomend re = new B2bRedRecomend();
            //推荐的是产品，是类还是场景？
            //只推荐产品 , 以下三个参数代表遍历到该产品的prodId,classId,sceneId
            Long prodId = r.getProdId();
            Long classId = r.getClassId();
            Long sceneId = r.getSceneId();

            //if(prodId != -1){
            re.setSid(prodId);
            //推荐类型 1表示产品
            re.setType(1L);
            B2bProduct b2bProduct = productService.selectB2bProductBySid(prodId);
            //产品名称
            re.setRec_name(b2bProduct.getProdName());
            //pfiles
            if(rec_id == 2){
                //如果是轮播图，要加上轮播图文件
                B2bProductPfiles b = new B2bProductPfiles();
                b.setProductId(prodId);
                //该产品的资源文件
                List<B2bProductPfiles> b2bProductPfiles1 = pfilesService.selectB2bProductPfilesList(b);
                for (B2bProductPfiles it : b2bProductPfiles1) {
                    //如果是轮播图，要加上轮播图文件 type=2
                    if(it.getFileType().equals("2")){
                        re.setPfiles(it);
                    }
                }
            }
            else{
                //如果是其他类型，返回所有该产品的pfile,type!=2
                B2bProductPfiles b = new B2bProductPfiles();
                b.setProductId(prodId);
                //该产品的资源文件
                List<B2bProductPfiles> b2bProductPfiles1 = pfilesService.selectB2bProductPfilesList(b);
                for (B2bProductPfiles it : b2bProductPfiles1) {
                    //如果是其他文件，要加上其他文件 type!=2
                    if(!it.getFileType().equals("2")){
                        re.setPfiles(it);
                    }
                }
            }
            //产品信息
            //如果rec_id == 4 那么需要先登录再收藏
            if(rec_id == 4L){
                try {
                    SysUser user = SecurityUtils.getLoginUser().getUser();
                } catch (Exception e) {
                    return error("请先登录再收藏");
                }
            }

            B2bProductPro productPro = productService.getProductPro(prodId);
            re.setProductPro(productPro);
            //场景推荐
            if(rec_id == 5){
                //如果是场景推荐，参数需要加入场景的id
                re.setScene_id(scene_id);
                B2bScene scene = sceneService.selectB2bSceneBySid(scene_id);
                String scene_name = "";
                try {
                    scene_name = scene.getName();
                }
                catch (Exception e ){
                    error("请上传sceneId");
                }
                re.setScene_name(scene_name);
                if(!Objects.equals(sceneId, scene_id)){
                    //如果该产品的场景id和所需要的场景id不是同一种，那么不返回
                    continue;
                }
                else{
                    re.setOrder_id(r.getOrderId());
                    res.add(re);
                    continue;
                }
            }
            re.setOrder_id(r.getOrderId());
            res.add(re);

        }
        return success(res);
    }

    /**
     * 查询推荐关联列表
     */
//    @PreAuthorize("@ss.hasPermi('system:recommendrel:list')")
    @GetMapping("/list")
    public TableDataInfo list(B2bRecommendrel b2bRecommendrel)
    {
        startPage();
        List<B2bRecommendrel> list = b2bRecommendrelService.selectB2bRecommendrelList(b2bRecommendrel);
        return getDataTable(list);
    }

    /**
     * 导出推荐关联列表
     */
//    @PreAuthorize("@ss.hasPermi('system:recommendrel:export')")
    @Log(title = "推荐关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B2bRecommendrel b2bRecommendrel)
    {
        List<B2bRecommendrel> list = b2bRecommendrelService.selectB2bRecommendrelList(b2bRecommendrel);
        ExcelUtil<B2bRecommendrel> util = new ExcelUtil<B2bRecommendrel>(B2bRecommendrel.class);
        util.exportExcel(response, list, "推荐关联数据");
    }

    /**
     * 获取推荐关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:recommendrel:query')")
    @GetMapping(value = "/{sid}")
    public AjaxResult getInfo(@PathVariable("sid") Long sid)
    {
        return success(b2bRecommendrelService.selectB2bRecommendrelBySid(sid));
    }

    /**
     * 新增推荐关联
     */
    @PreAuthorize("@ss.hasPermi('system:recommendrel:add')")
    @Log(title = "推荐关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B2bRecommendrel b2bRecommendrel)
    {
        return toAjax(b2bRecommendrelService.insertB2bRecommendrel(b2bRecommendrel));
    }

    /**
     * 修改推荐关联
     */
    @PreAuthorize("@ss.hasPermi('system:recommendrel:edit')")
    @Log(title = "推荐关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B2bRecommendrel b2bRecommendrel)
    {
        return toAjax(b2bRecommendrelService.updateB2bRecommendrel(b2bRecommendrel));
    }

    /**
     * 删除推荐关联
     */
    @PreAuthorize("@ss.hasPermi('system:recommendrel:remove')")
    @Log(title = "推荐关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sids}")
    public AjaxResult remove(@PathVariable Long[] sids)
    {
        return toAjax(b2bRecommendrelService.deleteB2bRecommendrelBySids(sids));
    }
}
