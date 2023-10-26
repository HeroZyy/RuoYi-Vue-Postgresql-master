package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 登录信息对象 b2b_login
 *
 * @author ruoyi
 * @date 2023-10-23
 */
public class B2bLogin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 登录信息表唯一表示 */
    private Long sid;

    /** 人员id */
    @Excel(name = "人员id")
    private Long relationId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 该人员是会员还是操作员 */
    @Excel(name = "该人员是会员还是操作员")
    private Long loginType;

    public void setSid(Long sid)
    {
        this.sid = sid;
    }

    public Long getSid()
    {
        return sid;
    }
    public void setRelationId(Long relationId)
    {
        this.relationId = relationId;
    }

    public Long getRelationId()
    {
        return relationId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setLoginType(Long loginType)
    {
        this.loginType = loginType;
    }

    public Long getLoginType()
    {
        return loginType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("sid", getSid())
                .append("relationId", getRelationId())
                .append("userName", getUserName())
                .append("password", getPassword())
                .append("phone", getPhone())
                .append("loginType", getLoginType())
                .toString();
    }

}
