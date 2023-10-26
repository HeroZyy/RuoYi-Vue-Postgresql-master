package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bProductMapper;
import com.ruoyi.system.domain.B2bProduct;
import com.ruoyi.system.service.IB2bProductService;

/**
 * 产品基本信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@Service
public class B2bProductServiceImpl implements IB2bProductService 
{
    @Autowired
    private B2bProductMapper b2bProductMapper;

    /**
     * 查询产品基本信息
     * 
     * @param sid 产品基本信息主键
     * @return 产品基本信息
     */
    @Override
    public B2bProduct selectB2bProductBySid(Long sid)
    {
        return b2bProductMapper.selectB2bProductBySid(sid);
    }

    /**
     * 查询产品基本信息列表
     * 
     * @param b2bProduct 产品基本信息
     * @return 产品基本信息
     */
    @Override
    public List<B2bProduct> selectB2bProductList(B2bProduct b2bProduct)
    {
        return b2bProductMapper.selectB2bProductList(b2bProduct);
    }

    /**
     * 新增产品基本信息
     * 
     * @param b2bProduct 产品基本信息
     * @return 结果
     */
    @Override
    public int insertB2bProduct(B2bProduct b2bProduct)
    {
        return b2bProductMapper.insertB2bProduct(b2bProduct);
    }

    /**
     * 修改产品基本信息
     * 
     * @param b2bProduct 产品基本信息
     * @return 结果
     */
    @Override
    public int updateB2bProduct(B2bProduct b2bProduct)
    {
        return b2bProductMapper.updateB2bProduct(b2bProduct);
    }

    /**
     * 批量删除产品基本信息
     * 
     * @param sids 需要删除的产品基本信息主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductBySids(Long[] sids)
    {
        return b2bProductMapper.deleteB2bProductBySids(sids);
    }

    /**
     * 删除产品基本信息信息
     * 
     * @param sid 产品基本信息主键
     * @return 结果
     */
    @Override
    public int deleteB2bProductBySid(Long sid)
    {
        return b2bProductMapper.deleteB2bProductBySid(sid);
    }
}
