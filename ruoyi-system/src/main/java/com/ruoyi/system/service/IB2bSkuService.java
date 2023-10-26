package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bSku;

/**
 * 产品单元Service接口
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public interface IB2bSkuService 
{
    /**
     * 查询产品单元
     * 
     * @param sid 产品单元主键
     * @return 产品单元
     */
    public B2bSku selectB2bSkuBySid(Long sid);

    /**
     * 查询产品单元列表
     * 
     * @param b2bSku 产品单元
     * @return 产品单元集合
     */
    public List<B2bSku> selectB2bSkuList(B2bSku b2bSku);

    /**
     * 新增产品单元
     * 
     * @param b2bSku 产品单元
     * @return 结果
     */
    public int insertB2bSku(B2bSku b2bSku);

    /**
     * 修改产品单元
     * 
     * @param b2bSku 产品单元
     * @return 结果
     */
    public int updateB2bSku(B2bSku b2bSku);

    /**
     * 批量删除产品单元
     * 
     * @param sids 需要删除的产品单元主键集合
     * @return 结果
     */
    public int deleteB2bSkuBySids(Long[] sids);

    /**
     * 删除产品单元信息
     * 
     * @param sid 产品单元主键
     * @return 结果
     */
    public int deleteB2bSkuBySid(Long sid);
}
