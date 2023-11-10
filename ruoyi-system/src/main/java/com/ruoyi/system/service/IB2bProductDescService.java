package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bProductDesc;

/**
 * 产品描述
Service接口
 * 
 * @author ruoyi
 * @date 2023-10-30
 */
public interface IB2bProductDescService 
{
    /**
     * 查询产品描述

     * 
     * @param sid 产品描述
主键
     * @return 产品描述

     */
    public B2bProductDesc selectB2bProductDescBySid(Long sid);

    /**
     * 查询产品描述
列表
     * 
     * @param b2bProductDesc 产品描述

     * @return 产品描述
集合
     */
    public List<B2bProductDesc> selectB2bProductDescList(B2bProductDesc b2bProductDesc);

    /**
     * 新增产品描述

     * 
     * @param b2bProductDesc 产品描述

     * @return 结果
     */
    public int insertB2bProductDesc(B2bProductDesc b2bProductDesc);

    /**
     * 修改产品描述

     * 
     * @param b2bProductDesc 产品描述

     * @return 结果
     */
    public int updateB2bProductDesc(B2bProductDesc b2bProductDesc);

    /**
     * 批量删除产品描述

     * 
     * @param sids 需要删除的产品描述
主键集合
     * @return 结果
     */
    public int deleteB2bProductDescBySids(Long[] sids);

    /**
     * 删除产品描述
信息
     * 
     * @param sid 产品描述
主键
     * @return 结果
     */
    public int deleteB2bProductDescBySid(Long sid);
}
