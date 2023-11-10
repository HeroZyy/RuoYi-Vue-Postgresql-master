package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;

/**
 * 产品单元对象 b2b_sku
 * 
 * @author ruoyi
 * @date 2023-11-07
 */
public class B2bSku extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long sid;

    /** 产品sku的id */
    @Excel(name = "产品sku的id")
    private Long skuId;

    /** 产品sku名称 */
    @Excel(name = "产品sku名称")
    private String skuName;

    /** 产品sku价格 */
    @Excel(name = "产品sku价格")
    private String price;

    /** 产品sku描述 */
    @Excel(name = "产品sku描述")
    private String describe;

    /** 是否为该产品id下的默认sku */
    @Excel(name = "是否为该产品id下的默认sku")
    private Long defaultSku;

    /** 产品id */
    @Excel(name = "产品id")
    private Long prodId;

    /** 产品sku标准报价清单 */
    @Excel(name = "产品sku标准报价清单")
    private String skuList;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
    }
    public void setSkuName(String skuName) 
    {
        this.skuName = skuName;
    }

    public String getSkuName() 
    {
        return skuName;
    }
    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getPrice() 
    {
        return price;
    }
    public void setDescribe(String describe) 
    {
        this.describe = describe;
    }

    public String getDescribe() 
    {
        return describe;
    }
    public void setDefaultSku(Long defaultSku) 
    {
        this.defaultSku = defaultSku;
    }

    public Long getDefaultSku() 
    {
        return defaultSku;
    }
    public void setProdId(Long prodId) 
    {
        this.prodId = prodId;
    }

    public Long getProdId() 
    {
        return prodId;
    }
    public void setSkuList(String skuList) 
    {
        this.skuList = skuList;
    }

    public String getSkuList() 
    {
        return skuList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("skuId", getSkuId())
            .append("skuName", getSkuName())
            .append("price", getPrice())
            .append("describe", getDescribe())
            .append("defaultSku", getDefaultSku())
            .append("prodId", getProdId())
            .append("skuList", getSkuList())
            .append("remark", getRemark())
            .toString();
    }
}
