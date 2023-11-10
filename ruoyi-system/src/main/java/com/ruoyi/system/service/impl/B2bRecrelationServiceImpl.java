package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bRecrelationMapper;
import com.ruoyi.system.domain.B2bRecrelation;
import com.ruoyi.system.service.IB2bRecrelationService;

/**
 * 推荐关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@Service
public class B2bRecrelationServiceImpl implements IB2bRecrelationService 
{
    @Autowired
    private B2bRecrelationMapper b2bRecrelationMapper;

    /**
     * 查询推荐关联
     * 
     * @param sid 推荐关联主键
     * @return 推荐关联
     */
    @Override
    public B2bRecrelation selectB2bRecrelationBySid(Long sid)
    {
        return b2bRecrelationMapper.selectB2bRecrelationBySid(sid);
    }

    /**
     * 查询推荐关联列表
     * 
     * @param b2bRecrelation 推荐关联
     * @return 推荐关联
     */
    @Override
    public List<B2bRecrelation> selectB2bRecrelationList(B2bRecrelation b2bRecrelation)
    {
        return b2bRecrelationMapper.selectB2bRecrelationList(b2bRecrelation);
    }

    /**
     * 新增推荐关联
     * 
     * @param b2bRecrelation 推荐关联
     * @return 结果
     */
    @Override
    public int insertB2bRecrelation(B2bRecrelation b2bRecrelation)
    {
        return b2bRecrelationMapper.insertB2bRecrelation(b2bRecrelation);
    }

    /**
     * 修改推荐关联
     * 
     * @param b2bRecrelation 推荐关联
     * @return 结果
     */
    @Override
    public int updateB2bRecrelation(B2bRecrelation b2bRecrelation)
    {
        return b2bRecrelationMapper.updateB2bRecrelation(b2bRecrelation);
    }

    /**
     * 批量删除推荐关联
     * 
     * @param sids 需要删除的推荐关联主键
     * @return 结果
     */
    @Override
    public int deleteB2bRecrelationBySids(Long[] sids)
    {
        return b2bRecrelationMapper.deleteB2bRecrelationBySids(sids);
    }

    /**
     * 删除推荐关联信息
     * 
     * @param sid 推荐关联主键
     * @return 结果
     */
    @Override
    public int deleteB2bRecrelationBySid(Long sid)
    {
        return b2bRecrelationMapper.deleteB2bRecrelationBySid(sid);
    }
}
