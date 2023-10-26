package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.B2bProduct;

/**
 * 产品基本信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public interface B2bProductMapper 
{
    /**
     * 查询产品基本信息
     * 
     * @param sid 产品基本信息主键
     * @return 产品基本信息
     */
    public B2bProduct selectB2bProductBySid(Long sid);

    /**
     * 查询产品基本信息列表
     * 
     * @param b2bProduct 产品基本信息
     * @return 产品基本信息集合
     */
    public List<B2bProduct> selectB2bProductList(B2bProduct b2bProduct);

    /**
     * 新增产品基本信息
     * 
     * @param b2bProduct 产品基本信息
     * @return 结果
     */
    public int insertB2bProduct(B2bProduct b2bProduct);

    /**
     * 修改产品基本信息
     * 
     * @param b2bProduct 产品基本信息
     * @return 结果
     */
    public int updateB2bProduct(B2bProduct b2bProduct);

    /**
     * 删除产品基本信息
     * 
     * @param sid 产品基本信息主键
     * @return 结果
     */
    public int deleteB2bProductBySid(Long sid);

    /**
     * 批量删除产品基本信息
     * 
     * @param sids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteB2bProductBySids(Long[] sids);
}
