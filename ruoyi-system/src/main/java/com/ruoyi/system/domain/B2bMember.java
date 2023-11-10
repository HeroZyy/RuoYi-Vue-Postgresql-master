package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员对象 b2b_member
 * 
 * @author ruoyi
 * @date 2023-10-27
 */
public class B2bMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会员id */
    private Long sid;

    /** 会员性别 */
    @Excel(name = "会员性别")
    private Long sex;

    /** 会员姓名 */
    @Excel(name = "会员姓名")
    private String realname;

    /** 会员手机 */
    @Excel(name = "会员手机")
    private String phone;

    /** 会员公司名称 */
    @Excel(name = "会员公司名称")
    private String company;

    /** 会员公司地址 */
    @Excel(name = "会员公司地址")
    private String addr;

    /** 会员类型 */
    @Excel(name = "会员类型")
    private Long memberType;

    /** 商铺主对应的商铺id */
    @Excel(name = "商铺主对应的商铺id")
    private Long shopId;

    /** 头像 */
    @Excel(name = "头像")
    private String avator;

    /** email */
    @Excel(name = "email")
    private String email;

    /** 职位 */
    @Excel(name = "职位")
    private String job;

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
    public void setMemberType(Long memberType) 
    {
        this.memberType = memberType;
    }

    public Long getMemberType() 
    {
        return memberType;
    }
    public void setShopId(Long shopId) 
    {
        this.shopId = shopId;
    }

    public Long getShopId() 
    {
        return shopId;
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
    public void setJob(String job) 
    {
        this.job = job;
    }

    public String getJob() 
    {
        return job;
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
            .append("memberType", getMemberType())
            .append("shopId", getShopId())
            .append("avator", getAvator())
            .append("email", getEmail())
            .append("job", getJob())
            .toString();
    }
}
