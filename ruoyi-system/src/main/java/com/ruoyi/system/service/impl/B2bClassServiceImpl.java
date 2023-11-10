package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bClassMapper;
import com.ruoyi.system.domain.B2bClass;
import com.ruoyi.system.service.IB2bClassService;

/**
 * 分类模块Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@Service
public class B2bClassServiceImpl implements IB2bClassService 
{
    @Autowired
    private B2bClassMapper b2bClassMapper;

    /**
     * 查询分类模块
     * 
     * @param sid 分类模块主键
     * @return 分类模块
     */
    @Override
    public B2bClass selectB2bClassBySid(Long sid)
    {
        return b2bClassMapper.selectB2bClassBySid(sid);
    }

    /**
     * 查询分类模块列表
     * 
     * @param b2bClass 分类模块
     * @return 分类模块
     */
    @Override
    public List<B2bClass> selectB2bClassList(B2bClass b2bClass)
    {
        return b2bClassMapper.selectB2bClassList(b2bClass);
    }


    /**
     * 新增分类模块
     * 
     * @param b2bClass 分类模块
     * @return 结果
     */
    @Override
    public int insertB2bClass(B2bClass b2bClass)
    {
        return b2bClassMapper.insertB2bClass(b2bClass);
    }

    /**
     * 修改分类模块
     * 
     * @param b2bClass 分类模块
     * @return 结果
     */
    @Override
    public int updateB2bClass(B2bClass b2bClass)
    {
        return b2bClassMapper.updateB2bClass(b2bClass);
    }

    /**
     * 批量删除分类模块
     * 
     * @param sids 需要删除的分类模块主键
     * @return 结果
     */
    @Override
    public int deleteB2bClassBySids(Long[] sids)
    {
        return b2bClassMapper.deleteB2bClassBySids(sids);
    }

    /**
     * 删除分类模块信息
     * 
     * @param sid 分类模块主键
     * @return 结果
     */
    @Override
    public int deleteB2bClassBySid(Long sid)
    {
        return b2bClassMapper.deleteB2bClassBySid(sid);
    }
}
