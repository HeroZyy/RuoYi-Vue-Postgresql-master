package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bMember;

/**
 * 会员Service接口
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public interface IB2bMemberService 
{
    /**
     * 查询会员
     * 
     * @param sid 会员主键
     * @return 会员
     */
    public B2bMember selectB2bMemberBySid(Long sid);

    /**
     * 查询会员列表
     * 
     * @param b2bMember 会员
     * @return 会员集合
     */
    public List<B2bMember> selectB2bMemberList(B2bMember b2bMember);

    /**
     * 新增会员
     * 
     * @param b2bMember 会员
     * @return 结果
     */
    public int insertB2bMember(B2bMember b2bMember);

    /**
     * 修改会员
     * 
     * @param b2bMember 会员
     * @return 结果
     */
    public int updateB2bMember(B2bMember b2bMember);

    /**
     * 批量删除会员
     * 
     * @param sids 需要删除的会员主键集合
     * @return 结果
     */
    public int deleteB2bMemberBySids(Long[] sids);

    /**
     * 删除会员信息
     * 
     * @param sid 会员主键
     * @return 结果
     */
    public int deleteB2bMemberBySid(Long sid);
}
