package com.ruoyi.system.service.impl;

import java.util.*;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bScprelationMapper;

import static com.ruoyi.common.utils.UniqueUtil.removeDuplicates;

/**
 * 场景分类产品关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-06
 */
@Service
public class B2bScprelationServiceImpl implements IB2bScprelationService 
{

    @Autowired
    private B2bScprelationMapper b2bScprelationMapper;

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
    private IB2bScprelationService scprelationService;

    @Autowired
    private IB2bSceneService sceneService;


    /**
     * 获取分类下所有场景
     * @param
     * @return
     */
    @Override
    public List<B2bClass> getClassBySceneId(Long sceneId){
        List<B2bScprelation> scprelations = b2bScprelationMapper.selectB2bScprelationBySceneSid(sceneId);
        List<B2bClass> res = new ArrayList<>();
        int num = 0 ;
        for (B2bScprelation scprelation : scprelations) {
            res.add(b2bClassService.selectB2bClassBySid(scprelation.getClassId()));
            System.out.println(scprelation.getClassId() + " " + String.valueOf(num) + "sbws");
            num++;
        }
        res = removeDuplicates(res);
        return res;
    }

    @Override
    public List<B2bSceneWithClass> getProd(List<Long> prodId) {
        List<B2bScprelation> scps = new ArrayList<>();
        for (Long l : prodId) {
            B2bScprelation scpProd = new B2bScprelation();scpProd.setProdId(l);
            scps.addAll(b2bScprelationMapper.selectB2bScprelationList(scpProd));
        }
        HashMap<Long , List<B2bProductPro> > hashClazz = new HashMap<>();
        //B2bClassWithProdPro列表
        List<B2bClassWithProdPro> clazzs = new ArrayList<>();
        //B2bSceneWithClass列表 最终结果
        List<B2bSceneWithClass> scenes = new ArrayList<>();
        for (B2bScprelation scp : scps) {
            // hashClazz 映射 classId->ProductProList
            Long classId = scp.getClassId();
            if(!hashClazz.containsKey(classId)) {
                hashClazz.put(classId, new ArrayList<B2bProductPro>());
            }
            //更新该key下的列表 hashClazz
            List<B2bProductPro> b2bProductPros = hashClazz.get(classId);
            b2bProductPros.add(b2bProductService.getProductPro(scp.getProdId()));
            hashClazz.put(classId , b2bProductPros);

            // hashScene 映射 sceneId->ProductProList
            Long sceneId = scp.getSceneId();
            if(hashClazz.containsKey(classId)) {
                hashClazz.put(classId, new ArrayList<B2bProductPro>());
            }

        }
        //填充clazzs
        for (Long l : hashClazz.keySet()) {
            List<B2bProductPro> b2bProductPros = hashClazz.get(l);
            B2bClass b2bClass = b2bClassService.selectB2bClassBySid(l);
            clazzs.add(new B2bClassWithProdPro(b2bProductPros,b2bClass));
        }

        for (B2bClassWithProdPro clazz : clazzs) {
            // 目前一个class只属于一个场景, sceneWithClass sceneId确定 clazz确定
            List<B2bScprelation> b2bScprelations = new ArrayList<>();
            try {
                b2bScprelations = b2bScprelationMapper.selectB2bScprelationByClassSid(clazz.getClazz().getSid());
            }
            catch (Exception e){
                System.out.println(b2bScprelations.toString() + " : 1111");
            }
            Long sceneId = null;
            for (B2bScprelation b2bScprelation : b2bScprelations) {
                sceneId = b2bScprelation.getSceneId();
            }
            B2bScene scene = sceneService.selectB2bSceneBySid(sceneId);
            B2bSceneWithClass sceneWithClass = new B2bSceneWithClass(clazzs, scene);
            scenes.add(sceneWithClass);
        }
        return scenes;
    }

    /**
     * 查询分类下的产品列表和分类信息
     */
    @Override
    public B2bClassWithProdPro getClassProd(Long class_id) {
        List<B2bScprelation> scps = b2bScprelationMapper.selectB2bScprelationByClassSid(class_id);
        List<B2bProductPro> prods = new ArrayList<>();
        for (B2bScprelation scp : scps) {
            prods.add(b2bProductService.getProductPro(scp.getProdId()));
        }
        return new B2bClassWithProdPro(prods , b2bClassService.selectB2bClassBySid(class_id));
    }

    /**
     * 查询场景下的分类
     */
    @Override
    public B2bSceneWithClass getSceneClass(Long scene_id) {
        List<B2bScprelation> scps = b2bScprelationMapper.selectB2bScprelationBySceneSid(scene_id);
        List<B2bClassWithProdPro> clzWithPros = new ArrayList<>();
        for (B2bScprelation scp : scps) {
            clzWithPros.add(scprelationService.getClassProd(scp.getClassId()));
        }
        return new B2bSceneWithClass(clzWithPros, sceneService.selectB2bSceneBySid(scene_id));
    }

    /**
     * 查询场景和分类下的产品
     *
     * @param scene_id,class_id
     * @return B2bProductPro
     */
    @Override
    public List<B2bScprelation> selectB2bProductBySceneIdAndClassId(Long scene_id, Long class_id)
    {
        List<B2bScprelation> resList = new ArrayList<>();
        if(scene_id == null && class_id == null) {
            B2bScprelation sc = new B2bScprelation();
            resList.addAll(b2bScprelationMapper.selectB2bScprelationList(sc));
            return resList;
        }
        //先通过场景筛选
        if(scene_id != null){
            //scene_id == 1 表示查找所有的场景
            List<B2bScprelation> sceneList;
            if(scene_id == 1L) {
                B2bScprelation sc1 = new B2bScprelation();
                sceneList= b2bScprelationMapper.selectB2bScprelationList(sc1);
            }
            else sceneList = b2bScprelationMapper.selectB2bScprelationBySceneSid(scene_id);
            // 如果class_id 不为空 那么就用场景、类型筛选
            if(class_id != null){
                List<B2bScprelation> classList ;
                if(class_id == 1L) {
                    B2bScprelation sc1 = new B2bScprelation();
                    classList= b2bScprelationMapper.selectB2bScprelationList(sc1);
                }
                else classList = b2bScprelationMapper.selectB2bScprelationByClassSid(class_id);

                for (B2bScprelation sc : sceneList) {
                    for (B2bScprelation cl : classList) {
                       if(Objects.equals(cl.getClassId(), sc.getClassId())) {
                           resList.add(cl);
                       }
                    }
                }
                resList = removeDuplicates(resList);
                return resList;
            }
            //如果class_id为空 那么就用场景筛选
            resList.addAll(sceneList);
            return resList;
        }
        //只有class_id
        if(class_id != null){
            List<B2bScprelation> classList;
            if(class_id == 1L) {
                B2bScprelation sc1 = new B2bScprelation();
                classList= b2bScprelationMapper.selectB2bScprelationList(sc1);
            }
            else classList = b2bScprelationMapper.selectB2bScprelationByClassSid(class_id);
            resList.addAll(classList);
            return resList;
        }
        return resList;
    }
    /**
     * 查询场景分类产品关联
     * 
     * @param sid 场景分类产品关联主键
     * @return 场景分类产品关联
     */
    @Override
    public B2bScprelation selectB2bScprelationBySid(Long sid)
    {
        return b2bScprelationMapper.selectB2bScprelationBySid(sid);
    }

    /**
     * 查询场景分类产品关联列表
     * 
     * @param b2bScprelation 场景分类产品关联
     * @return 场景分类产品关联
     */
    @Override
    public List<B2bScprelation> selectB2bScprelationList(B2bScprelation b2bScprelation)
    {
        return b2bScprelationMapper.selectB2bScprelationList(b2bScprelation);
    }

    /**
     * 新增场景分类产品关联
     * 
     * @param b2bScprelation 场景分类产品关联
     * @return 结果
     */
    @Override
    public int insertB2bScprelation(B2bScprelation b2bScprelation)
    {
        return b2bScprelationMapper.insertB2bScprelation(b2bScprelation);
    }

    /**
     * 修改场景分类产品关联
     * 
     * @param b2bScprelation 场景分类产品关联
     * @return 结果
     */
    @Override
    public int updateB2bScprelation(B2bScprelation b2bScprelation)
    {
        return b2bScprelationMapper.updateB2bScprelation(b2bScprelation);
    }

    /**
     * 批量删除场景分类产品关联
     * 
     * @param sids 需要删除的场景分类产品关联主键
     * @return 结果
     */
    @Override
    public int deleteB2bScprelationBySids(Long[] sids)
    {
        return b2bScprelationMapper.deleteB2bScprelationBySids(sids);
    }

    /**
     * 删除场景分类产品关联信息
     * 
     * @param sid 场景分类产品关联主键
     * @return 结果
     */
    @Override
    public int deleteB2bScprelationBySid(Long sid)
    {
        return b2bScprelationMapper.deleteB2bScprelationBySid(sid);
    }
}
