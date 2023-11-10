package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 推荐关联对象 b2b_recRelation
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public class B2bRecrelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增标号 */
    private Long sid;

    /** 推荐id */
    @Excel(name = "推荐id")
    private Long recId;

    /** 推荐产品的id */
    @Excel(name = "推荐产品的id")
    private Long prodId;

    /** 产品的排序序号 */
    @Excel(name = "产品的排序序号")
    private Long orderId;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setRecId(Long recId) 
    {
        this.recId = recId;
    }

    public Long getRecId() 
    {
        return recId;
    }
    public void setProdId(Long prodId) 
    {
        this.prodId = prodId;
    }

    public Long getProdId() 
    {
        return prodId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("recId", getRecId())
            .append("prodId", getProdId())
            .append("orderId", getOrderId())
            .toString();
    }
}
