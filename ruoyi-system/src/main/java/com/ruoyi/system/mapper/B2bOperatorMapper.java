package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.B2bOperator;

/**
 * 操作员Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public interface B2bOperatorMapper 
{
    /**
     * 查询操作员
     * 
     * @param sid 操作员主键
     * @return 操作员
     */
    public B2bOperator selectB2bOperatorBySid(Long sid);

    /**
     * 查询操作员列表
     * 
     * @param b2bOperator 操作员
     * @return 操作员集合
     */
    public List<B2bOperator> selectB2bOperatorList(B2bOperator b2bOperator);

    /**
     * 新增操作员
     * 
     * @param b2bOperator 操作员
     * @return 结果
     */
    public int insertB2bOperator(B2bOperator b2bOperator);

    /**
     * 修改操作员
     * 
     * @param b2bOperator 操作员
     * @return 结果
     */
    public int updateB2bOperator(B2bOperator b2bOperator);

    /**
     * 删除操作员
     * 
     * @param sid 操作员主键
     * @return 结果
     */
    public int deleteB2bOperatorBySid(Long sid);

    /**
     * 批量删除操作员
     * 
     * @param sids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteB2bOperatorBySids(Long[] sids);
}
