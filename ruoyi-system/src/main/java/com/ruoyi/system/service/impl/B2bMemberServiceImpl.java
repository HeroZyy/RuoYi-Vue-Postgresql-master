package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bMemberMapper;
import com.ruoyi.system.domain.B2bMember;
import com.ruoyi.system.service.IB2bMemberService;

/**
 * 会员Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
@Service
public class B2bMemberServiceImpl implements IB2bMemberService 
{
    @Autowired
    private B2bMemberMapper b2bMemberMapper;

    /**
     * 查询会员
     * 
     * @param sid 会员主键
     * @return 会员
     */
    @Override
    public B2bMember selectB2bMemberBySid(Long sid)
    {
        return b2bMemberMapper.selectB2bMemberBySid(sid);
    }

    /**
     * 查询会员列表
     * 
     * @param b2bMember 会员
     * @return 会员
     */
    @Override
    public List<B2bMember> selectB2bMemberList(B2bMember b2bMember)
    {
        return b2bMemberMapper.selectB2bMemberList(b2bMember);
    }

    /**
     * 新增会员
     * 
     * @param b2bMember 会员
     * @return 结果
     */
    @Override
    public int insertB2bMember(B2bMember b2bMember)
    {
        return b2bMemberMapper.insertB2bMember(b2bMember);
    }

    /**
     * 修改会员
     * 
     * @param b2bMember 会员
     * @return 结果
     */
    @Override
    public int updateB2bMember(B2bMember b2bMember)
    {
        return b2bMemberMapper.updateB2bMember(b2bMember);
    }

    /**
     * 批量删除会员
     * 
     * @param sids 需要删除的会员主键
     * @return 结果
     */
    @Override
    public int deleteB2bMemberBySids(Long[] sids)
    {
        return b2bMemberMapper.deleteB2bMemberBySids(sids);
    }

    /**
     * 删除会员信息
     * 
     * @param sid 会员主键
     * @return 结果
     */
    @Override
    public int deleteB2bMemberBySid(Long sid)
    {
        return b2bMemberMapper.deleteB2bMemberBySid(sid);
    }
}
