package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品资料下载信息对象 b2b_product_download
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public class B2bProductDownload extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 下载表自增主键 */
    @Excel(name = "下载表自增主键")
    private Long sid;

    /** 产品id */
    @Excel(name = "产品id")
    private Long prodId;

    /** 下载地址 */
    @Excel(name = "下载地址")
    private String url;

    /** 是否允许下载 */
    @Excel(name = "是否允许下载")
    private Long allowDl;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setProdId(Long prodId) 
    {
        this.prodId = prodId;
    }

    public Long getProdId() 
    {
        return prodId;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setAllowDl(Long allowDl) 
    {
        this.allowDl = allowDl;
    }

    public Long getAllowDl() 
    {
        return allowDl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("prodId", getProdId())
            .append("url", getUrl())
            .append("allowDl", getAllowDl())
            .toString();
    }
}
