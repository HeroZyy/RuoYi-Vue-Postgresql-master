package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品基本信息对象 b2b_product
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public class B2bProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品id */
    @Excel(name = "产品id")
    private Long sid;

    /** 产品分类id */
    @Excel(name = "产品分类id")
    private Long classId;

    /** 该产品所属的商铺id */
    @Excel(name = "该产品所属的商铺id")
    private Long shopId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String prodName;

    /** 产品介绍富文本 */
    @Excel(name = "产品介绍富文本")
    private String intro;

    /** 产品规格富文本 */
    @Excel(name = "产品规格富文本")
    private String config;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setShopId(Long shopId) 
    {
        this.shopId = shopId;
    }

    public Long getShopId() 
    {
        return shopId;
    }
    public void setProdName(String prodName) 
    {
        this.prodName = prodName;
    }

    public String getProdName() 
    {
        return prodName;
    }
    public void setIntro(String intro) 
    {
        this.intro = intro;
    }

    public String getIntro() 
    {
        return intro;
    }
    public void setConfig(String config) 
    {
        this.config = config;
    }

    public String getConfig() 
    {
        return config;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("classId", getClassId())
            .append("shopId", getShopId())
            .append("prodName", getProdName())
            .append("intro", getIntro())
            .append("config", getConfig())
            .toString();
    }
}
