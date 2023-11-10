package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bRecommendrelMapper;
import com.ruoyi.system.domain.B2bRecommendrel;
import com.ruoyi.system.service.IB2bRecommendrelService;

/**
 * 推荐关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-30
 */
@Service
public class B2bRecommendrelServiceImpl implements IB2bRecommendrelService 
{
    @Autowired
    private B2bRecommendrelMapper b2bRecommendrelMapper;

    /**
     * 查询推荐关联
     * 
     * @param sid 推荐关联主键
     * @return 推荐关联
     */
    @Override
    public B2bRecommendrel selectB2bRecommendrelBySid(Long sid)
    {
        return b2bRecommendrelMapper.selectB2bRecommendrelBySid(sid);
    }

    /**
     * 查询推荐关联列表
     * 
     * @param b2bRecommendrel 推荐关联
     * @return 推荐关联
     */
    @Override
    public List<B2bRecommendrel> selectB2bRecommendrelList(B2bRecommendrel b2bRecommendrel)
    {
        return b2bRecommendrelMapper.selectB2bRecommendrelList(b2bRecommendrel);
    }

    /**
     * 新增推荐关联
     * 
     * @param b2bRecommendrel 推荐关联
     * @return 结果
     */
    @Override
    public int insertB2bRecommendrel(B2bRecommendrel b2bRecommendrel)
    {
        return b2bRecommendrelMapper.insertB2bRecommendrel(b2bRecommendrel);
    }

    /**
     * 修改推荐关联
     * 
     * @param b2bRecommendrel 推荐关联
     * @return 结果
     */
    @Override
    public int updateB2bRecommendrel(B2bRecommendrel b2bRecommendrel)
    {
        return b2bRecommendrelMapper.updateB2bRecommendrel(b2bRecommendrel);
    }

    /**
     * 批量删除推荐关联
     * 
     * @param sids 需要删除的推荐关联主键
     * @return 结果
     */
    @Override
    public int deleteB2bRecommendrelBySids(Long[] sids)
    {
        return b2bRecommendrelMapper.deleteB2bRecommendrelBySids(sids);
    }

    /**
     * 删除推荐关联信息
     * 
     * @param sid 推荐关联主键
     * @return 结果
     */
    @Override
    public int deleteB2bRecommendrelBySid(Long sid)
    {
        return b2bRecommendrelMapper.deleteB2bRecommendrelBySid(sid);
    }
}
