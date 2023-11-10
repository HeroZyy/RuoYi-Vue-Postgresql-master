package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bSceneRelationMapper;
import com.ruoyi.system.domain.B2bSceneRelation;
import com.ruoyi.system.service.IB2bSceneRelationService;

/**
 * 场景关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@Service
public class B2bSceneRelationServiceImpl implements IB2bSceneRelationService 
{
    @Autowired
    private B2bSceneRelationMapper b2bSceneRelationMapper;

    /**
     * 查询场景关联
     * 
     * @param sid 场景关联主键
     * @return 场景关联
     */
    @Override
    public B2bSceneRelation selectB2bSceneRelationBySid(Long sid)
    {
        return b2bSceneRelationMapper.selectB2bSceneRelationBySid(sid);
    }

    /**
     * 查询场景关联列表
     * 
     * @param b2bSceneRelation 场景关联
     * @return 场景关联
     */
    @Override
    public List<B2bSceneRelation> selectB2bSceneRelationList(B2bSceneRelation b2bSceneRelation)
    {
        return b2bSceneRelationMapper.selectB2bSceneRelationList(b2bSceneRelation);
    }

    /**
     * 新增场景关联
     * 
     * @param b2bSceneRelation 场景关联
     * @return 结果
     */
    @Override
    public int insertB2bSceneRelation(B2bSceneRelation b2bSceneRelation)
    {
        return b2bSceneRelationMapper.insertB2bSceneRelation(b2bSceneRelation);
    }

    /**
     * 修改场景关联
     * 
     * @param b2bSceneRelation 场景关联
     * @return 结果
     */
    @Override
    public int updateB2bSceneRelation(B2bSceneRelation b2bSceneRelation)
    {
        return b2bSceneRelationMapper.updateB2bSceneRelation(b2bSceneRelation);
    }

    /**
     * 批量删除场景关联
     * 
     * @param sids 需要删除的场景关联主键
     * @return 结果
     */
    @Override
    public int deleteB2bSceneRelationBySids(Long[] sids)
    {
        return b2bSceneRelationMapper.deleteB2bSceneRelationBySids(sids);
    }

    /**
     * 删除场景关联信息
     * 
     * @param sid 场景关联主键
     * @return 结果
     */
    @Override
    public int deleteB2bSceneRelationBySid(Long sid)
    {
        return b2bSceneRelationMapper.deleteB2bSceneRelationBySid(sid);
    }
}
