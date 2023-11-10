package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bRecommendrel;

/**
 * 推荐关联Service接口
 * 
 * @author ruoyi
 * @date 2023-10-30
 */
public interface IB2bRecommendrelService 
{
    /**
     * 查询推荐关联
     * 
     * @param sid 推荐关联主键
     * @return 推荐关联
     */
    public B2bRecommendrel selectB2bRecommendrelBySid(Long sid);

    /**
     * 查询推荐关联列表
     * 
     * @param b2bRecommendrel 推荐关联
     * @return 推荐关联集合
     */
    public List<B2bRecommendrel> selectB2bRecommendrelList(B2bRecommendrel b2bRecommendrel);

    /**
     * 新增推荐关联
     * 
     * @param b2bRecommendrel 推荐关联
     * @return 结果
     */
    public int insertB2bRecommendrel(B2bRecommendrel b2bRecommendrel);

    /**
     * 修改推荐关联
     * 
     * @param b2bRecommendrel 推荐关联
     * @return 结果
     */
    public int updateB2bRecommendrel(B2bRecommendrel b2bRecommendrel);

    /**
     * 批量删除推荐关联
     * 
     * @param sids 需要删除的推荐关联主键集合
     * @return 结果
     */
    public int deleteB2bRecommendrelBySids(Long[] sids);

    /**
     * 删除推荐关联信息
     * 
     * @param sid 推荐关联主键
     * @return 结果
     */
    public int deleteB2bRecommendrelBySid(Long sid);
}
