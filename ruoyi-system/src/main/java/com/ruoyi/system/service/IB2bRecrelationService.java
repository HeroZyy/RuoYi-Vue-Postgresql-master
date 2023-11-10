package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bRecrelation;

/**
 * 推荐关联Service接口
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public interface IB2bRecrelationService 
{
    /**
     * 查询推荐关联
     * 
     * @param sid 推荐关联主键
     * @return 推荐关联
     */
    public B2bRecrelation selectB2bRecrelationBySid(Long sid);

    /**
     * 查询推荐关联列表
     * 
     * @param b2bRecrelation 推荐关联
     * @return 推荐关联集合
     */
    public List<B2bRecrelation> selectB2bRecrelationList(B2bRecrelation b2bRecrelation);

    /**
     * 新增推荐关联
     * 
     * @param b2bRecrelation 推荐关联
     * @return 结果
     */
    public int insertB2bRecrelation(B2bRecrelation b2bRecrelation);

    /**
     * 修改推荐关联
     * 
     * @param b2bRecrelation 推荐关联
     * @return 结果
     */
    public int updateB2bRecrelation(B2bRecrelation b2bRecrelation);

    /**
     * 批量删除推荐关联
     * 
     * @param sids 需要删除的推荐关联主键集合
     * @return 结果
     */
    public int deleteB2bRecrelationBySids(Long[] sids);

    /**
     * 删除推荐关联信息
     * 
     * @param sid 推荐关联主键
     * @return 结果
     */
    public int deleteB2bRecrelationBySid(Long sid);
}
