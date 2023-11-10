package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 操作员对象 b2b_operator
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public class B2bOperator extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 操作员id */
    private Long sid;

    /** 操作员性别 */
    @Excel(name = "操作员性别")
    private Long sex;

    /** 操作员姓名 */
    @Excel(name = "操作员姓名")
    private String realname;

    /** 操作员手机 */
    @Excel(name = "操作员手机")
    private String phone;

    /** 操作员公司名称 */
    @Excel(name = "操作员公司名称")
    private String company;

    /** 操作员公司地址 */
    @Excel(name = "操作员公司地址")
    private String addr;

    /** 操作员类型,0表示超级管理员,1表示操作员 */
    @Excel(name = "操作员类型,0表示超级管理员,1表示操作员")
    private Long optType;

    /** 头像 */
    @Excel(name = "头像")
    private String avator;

    /** email */
    @Excel(name = "email")
    private String email;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setSex(Long sex) 
    {
        this.sex = sex;
    }

    public Long getSex() 
    {
        return sex;
    }
    public void setRealname(String realname) 
    {
        this.realname = realname;
    }

    public String getRealname() 
    {
        return realname;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setAddr(String addr) 
    {
        this.addr = addr;
    }

    public String getAddr() 
    {
        return addr;
    }
    public void setOptType(Long optType) 
    {
        this.optType = optType;
    }

    public Long getOptType() 
    {
        return optType;
    }
    public void setAvator(String avator) 
    {
        this.avator = avator;
    }

    public String getAvator() 
    {
        return avator;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("sex", getSex())
            .append("realname", getRealname())
            .append("phone", getPhone())
            .append("company", getCompany())
            .append("addr", getAddr())
            .append("optType", getOptType())
            .append("avator", getAvator())
            .append("email", getEmail())
            .toString();
    }
}
