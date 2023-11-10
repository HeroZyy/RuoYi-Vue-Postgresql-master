package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.*;

/**
 * 场景分类产品关联Service接口
 * 
 * @author ruoyi
 * @date 2023-11-06
 */
public interface IB2bScprelationService 
{
    //只用prodId，取得该prodId所属的分类和场景
    public List<B2bSceneWithClass> getProd(List<Long> prodId);
    //只用classId，取得该classId下的分类和产品
    public B2bClassWithProdPro getClassProd(Long class_id);
    //只用sceneId，取得该sceneId下的分类和产品和场景
    public B2bSceneWithClass getSceneClass(Long scene_id);

    List<B2bClass> getClassBySceneId(Long scene_id);

    List<B2bScprelation> selectB2bProductBySceneIdAndClassId(Long scene_id, Long class_id);

    /**
     * 查询场景分类产品关联
     * 
     * @param sid 场景分类产品关联主键
     * @return 场景分类产品关联
     */
    public B2bScprelation selectB2bScprelationBySid(Long sid);

    /**
     * 查询场景分类产品关联列表
     * 
     * @param b2bScprelation 场景分类产品关联
     * @return 场景分类产品关联集合
     */
    public List<B2bScprelation> selectB2bScprelationList(B2bScprelation b2bScprelation);

    /**
     * 新增场景分类产品关联
     * 
     * @param b2bScprelation 场景分类产品关联
     * @return 结果
     */
    public int insertB2bScprelation(B2bScprelation b2bScprelation);

    /**
     * 修改场景分类产品关联
     * 
     * @param b2bScprelation 场景分类产品关联
     * @return 结果
     */
    public int updateB2bScprelation(B2bScprelation b2bScprelation);

    /**
     * 批量删除场景分类产品关联
     * 
     * @param sids 需要删除的场景分类产品关联主键集合
     * @return 结果
     */
    public int deleteB2bScprelationBySids(Long[] sids);

    /**
     * 删除场景分类产品关联信息
     * 
     * @param sid 场景分类产品关联主键
     * @return 结果
     */
    public int deleteB2bScprelationBySid(Long sid);
}
