package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bProductDescMapper;
import com.ruoyi.system.domain.B2bProductDesc;
import com.ruoyi.system.service.IB2bProductDescService;

/**
 * 产品描述
Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-30
 */
@Service
public class B2bProductDescServiceImpl implements IB2bProductDescService 
{
    @Autowired
    private B2bProductDescMapper b2bProductDescMapper;

    /**
     * 查询产品描述

     * 
     * @param sid 产品描述
主键
     * @return 产品描述

     */
    @Override
    public B2bProductDesc selectB2bProductDescBySid(Long sid)
    {
        return b2bProductDescMapper.selectB2bProductDescBySid(sid);
    }

    /**
     * 查询产品描述
列表
     * 
     * @param b2bProductDesc 产品描述

     * @return 产品描述

     */
    @Override
    public List<B2bProductDesc> selectB2bProductDescList(B2bProductDesc b2bProductDesc)
    {
        return b2bProductDescMapper.selectB2bProductDescList(b2bProductDesc);
    }

    /**
     * 新增产品描述

     * 
     * @param b2bProductDesc 产品描述

     * @return 结果
     */
    @Override
    public int insertB2bProductDesc(B2bProductDesc b2bProductDesc)
    {
        return b2bProductDescMapper.insertB2bProductDesc(b2bProductDesc);
    }

    /**
     * 修改产品描述

     * 
     * @param b2bProductDesc 产品描述

     * @return 结果
     */
    @Override
    public int updateB2bProductDesc(B2bProductDesc b2bProductDesc)
    {
        return b2bProductDescMapper.updateB2bProductDesc(b2bProductDesc);
    }

    /**
     * 批量删除产品描述

     * 
     * @param sids 需要删除的产品描述
主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductDescBySids(Long[] sids)
    {
        return b2bProductDescMapper.deleteB2bProductDescBySids(sids);
    }

    /**
     * 删除产品描述
信息
     * 
     * @param sid 产品描述
主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductDescBySid(Long sid)
    {
        return b2bProductDescMapper.deleteB2bProductDescBySid(sid);
    }
}
