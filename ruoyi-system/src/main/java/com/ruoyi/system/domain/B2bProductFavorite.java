package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品收藏对象 b2b_product_favorite
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public class B2bProductFavorite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收藏表自增标号 */
    @Excel(name = "收藏表自增标号")
    private Long sid;

    /** 会员id */
    @Excel(name = "会员id")
    private Long memId;

    /** 收藏的sku_id */
    @Excel(name = "收藏的sku_id")
    private Long skuId;

    /** 产品的id */
    @Excel(name = "产品的id")
    private Long prodId;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setMemId(Long memId) 
    {
        this.memId = memId;
    }

    public Long getMemId() 
    {
        return memId;
    }
    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
    }
    public void setProdId(Long prodId) 
    {
        this.prodId = prodId;
    }

    public Long getProdId() 
    {
        return prodId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("memId", getMemId())
            .append("skuId", getSkuId())
            .append("prodId", getProdId())
            .toString();
    }
}
