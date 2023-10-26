package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bSkuMapper;
import com.ruoyi.system.domain.B2bSku;
import com.ruoyi.system.service.IB2bSkuService;

/**
 * 产品单元Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
@Service
public class B2bSkuServiceImpl implements IB2bSkuService 
{
    @Autowired
    private B2bSkuMapper b2bSkuMapper;

    /**
     * 查询产品单元
     * 
     * @param sid 产品单元主键
     * @return 产品单元
     */
    @Override
    public B2bSku selectB2bSkuBySid(Long sid)
    {
        return b2bSkuMapper.selectB2bSkuBySid(sid);
    }

    /**
     * 查询产品单元列表
     * 
     * @param b2bSku 产品单元
     * @return 产品单元
     */
    @Override
    public List<B2bSku> selectB2bSkuList(B2bSku b2bSku)
    {
        return b2bSkuMapper.selectB2bSkuList(b2bSku);
    }

    /**
     * 新增产品单元
     * 
     * @param b2bSku 产品单元
     * @return 结果
     */
    @Override
    public int insertB2bSku(B2bSku b2bSku)
    {
        return b2bSkuMapper.insertB2bSku(b2bSku);
    }

    /**
     * 修改产品单元
     * 
     * @param b2bSku 产品单元
     * @return 结果
     */
    @Override
    public int updateB2bSku(B2bSku b2bSku)
    {
        return b2bSkuMapper.updateB2bSku(b2bSku);
    }

    /**
     * 批量删除产品单元
     * 
     * @param sids 需要删除的产品单元主键
     * @return 结果
     */
    @Override
    public int deleteB2bSkuBySids(Long[] sids)
    {
        return b2bSkuMapper.deleteB2bSkuBySids(sids);
    }

    /**
     * 删除产品单元信息
     * 
     * @param sid 产品单元主键
     * @return 结果
     */
    @Override
    public int deleteB2bSkuBySid(Long sid)
    {
        return b2bSkuMapper.deleteB2bSkuBySid(sid);
    }
}
