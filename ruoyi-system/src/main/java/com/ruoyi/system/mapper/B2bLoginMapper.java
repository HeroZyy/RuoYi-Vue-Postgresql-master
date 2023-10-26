package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.B2bLogin;

/**
 * 登录信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-23
 */
public interface B2bLoginMapper 
{
    /**
     * 查询登录信息
     * 
     * @param sid 登录信息主键
     * @return 登录信息
     */
    public B2bLogin selectB2bLoginBySid(Long sid);

    /**
     * 查询登录信息列表
     * 
     * @param b2bLogin 登录信息
     * @return 登录信息集合
     */
    public List<B2bLogin> selectB2bLoginList(B2bLogin b2bLogin);

    /**
     * 新增登录信息
     * 
     * @param b2bLogin 登录信息
     * @return 结果
     */
    public int insertB2bLogin(B2bLogin b2bLogin);

    /**
     * 修改登录信息
     * 
     * @param b2bLogin 登录信息
     * @return 结果
     */
    public int updateB2bLogin(B2bLogin b2bLogin);

    /**
     * 删除登录信息
     * 
     * @param sid 登录信息主键
     * @return 结果
     */
    public int deleteB2bLoginBySid(Long sid);

    /**
     * 批量删除登录信息
     * 
     * @param sids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteB2bLoginBySids(Long[] sids);
}
