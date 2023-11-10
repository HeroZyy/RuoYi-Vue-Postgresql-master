package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bSceneRelation;

/**
 * 场景关联Service接口
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public interface IB2bSceneRelationService 
{
    /**
     * 查询场景关联
     * 
     * @param sid 场景关联主键
     * @return 场景关联
     */
    public B2bSceneRelation selectB2bSceneRelationBySid(Long sid);

    /**
     * 查询场景关联列表
     * 
     * @param b2bSceneRelation 场景关联
     * @return 场景关联集合
     */
    public List<B2bSceneRelation> selectB2bSceneRelationList(B2bSceneRelation b2bSceneRelation);

    /**
     * 新增场景关联
     * 
     * @param b2bSceneRelation 场景关联
     * @return 结果
     */
    public int insertB2bSceneRelation(B2bSceneRelation b2bSceneRelation);

    /**
     * 修改场景关联
     * 
     * @param b2bSceneRelation 场景关联
     * @return 结果
     */
    public int updateB2bSceneRelation(B2bSceneRelation b2bSceneRelation);

    /**
     * 批量删除场景关联
     * 
     * @param sids 需要删除的场景关联主键集合
     * @return 结果
     */
    public int deleteB2bSceneRelationBySids(Long[] sids);

    /**
     * 删除场景关联信息
     * 
     * @param sid 场景关联主键
     * @return 结果
     */
    public int deleteB2bSceneRelationBySid(Long sid);
}
