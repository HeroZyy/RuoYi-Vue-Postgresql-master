package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bLoginMapper;
import com.ruoyi.system.domain.B2bLogin;
import com.ruoyi.system.service.IB2bLoginService;

/**
 * 登录信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-23
 */
@Service
public class B2bLoginServiceImpl implements IB2bLoginService 
{
    @Autowired
    private B2bLoginMapper b2bLoginMapper;

    /**
     * 查询登录信息
     * 
     * @param sid 登录信息主键
     * @return 登录信息
     */
    @Override
    public B2bLogin selectB2bLoginBySid(Long sid)
    {
        return b2bLoginMapper.selectB2bLoginBySid(sid);
    }

    /**
     * 查询登录信息列表
     * 
     * @param b2bLogin 登录信息
     * @return 登录信息
     */
    @Override
    public List<B2bLogin> selectB2bLoginList(B2bLogin b2bLogin)
    {
        return b2bLoginMapper.selectB2bLoginList(b2bLogin);
    }

    /**
     * 新增登录信息
     * 
     * @param b2bLogin 登录信息
     * @return 结果
     */
    @Override
    public int insertB2bLogin(B2bLogin b2bLogin)
    {
        return b2bLoginMapper.insertB2bLogin(b2bLogin);
    }

    /**
     * 修改登录信息
     * 
     * @param b2bLogin 登录信息
     * @return 结果
     */
    @Override
    public int updateB2bLogin(B2bLogin b2bLogin)
    {
        return b2bLoginMapper.updateB2bLogin(b2bLogin);
    }

    /**
     * 批量删除登录信息
     * 
     * @param sids 需要删除的登录信息主键
     * @return 结果
     */
    @Override
    public int deleteB2bLoginBySids(Long[] sids)
    {
        return b2bLoginMapper.deleteB2bLoginBySids(sids);
    }

    /**
     * 删除登录信息信息
     * 
     * @param sid 登录信息主键
     * @return 结果
     */
    @Override
    public int deleteB2bLoginBySid(Long sid)
    {
        return b2bLoginMapper.deleteB2bLoginBySid(sid);
    }
}
