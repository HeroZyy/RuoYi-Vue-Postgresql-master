package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bOperatorMapper;
import com.ruoyi.system.domain.B2bOperator;
import com.ruoyi.system.service.IB2bOperatorService;

/**
 * 操作员Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@Service
public class B2bOperatorServiceImpl implements IB2bOperatorService 
{
    @Autowired
    private B2bOperatorMapper b2bOperatorMapper;

    /**
     * 查询操作员
     * 
     * @param sid 操作员主键
     * @return 操作员
     */
    @Override
    public B2bOperator selectB2bOperatorBySid(Long sid)
    {
        return b2bOperatorMapper.selectB2bOperatorBySid(sid);
    }

    /**
     * 查询操作员列表
     * 
     * @param b2bOperator 操作员
     * @return 操作员
     */
    @Override
    public List<B2bOperator> selectB2bOperatorList(B2bOperator b2bOperator)
    {
        return b2bOperatorMapper.selectB2bOperatorList(b2bOperator);
    }

    /**
     * 新增操作员
     * 
     * @param b2bOperator 操作员
     * @return 结果
     */
    @Override
    public int insertB2bOperator(B2bOperator b2bOperator)
    {
        return b2bOperatorMapper.insertB2bOperator(b2bOperator);
    }

    /**
     * 修改操作员
     * 
     * @param b2bOperator 操作员
     * @return 结果
     */
    @Override
    public int updateB2bOperator(B2bOperator b2bOperator)
    {
        return b2bOperatorMapper.updateB2bOperator(b2bOperator);
    }

    /**
     * 批量删除操作员
     * 
     * @param sids 需要删除的操作员主键
     * @return 结果
     */
    @Override
    public int deleteB2bOperatorBySids(Long[] sids)
    {
        return b2bOperatorMapper.deleteB2bOperatorBySids(sids);
    }

    /**
     * 删除操作员信息
     * 
     * @param sid 操作员主键
     * @return 结果
     */
    @Override
    public int deleteB2bOperatorBySid(Long sid)
    {
        return b2bOperatorMapper.deleteB2bOperatorBySid(sid);
    }
}
