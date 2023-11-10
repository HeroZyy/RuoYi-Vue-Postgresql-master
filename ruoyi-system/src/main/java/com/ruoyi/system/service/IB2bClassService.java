package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bClass;

/**
 * 分类模块Service接口
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public interface IB2bClassService 
{
    /**
     * 查询分类模块
     * 
     * @param sid 分类模块主键
     * @return 分类模块
     */
    public B2bClass selectB2bClassBySid(Long sid);

    /**
     * 查询分类模块列表
     * 
     * @param b2bClass 分类模块
     * @return 分类模块集合
     */
    public List<B2bClass> selectB2bClassList(B2bClass b2bClass);


    /**
     * 新增分类模块
     * 
     * @param b2bClass 分类模块
     * @return 结果
     */
    public int insertB2bClass(B2bClass b2bClass);

    /**
     * 修改分类模块
     * 
     * @param b2bClass 分类模块
     * @return 结果
     */
    public int updateB2bClass(B2bClass b2bClass);

    /**
     * 批量删除分类模块
     * 
     * @param sids 需要删除的分类模块主键集合
     * @return 结果
     */
    public int deleteB2bClassBySids(Long[] sids);

    /**
     * 删除分类模块信息
     * 
     * @param sid 分类模块主键
     * @return 结果
     */
    public int deleteB2bClassBySid(Long sid);
}
