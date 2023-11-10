package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 场景关联对象 b2b_scene_relation
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public class B2bSceneRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 场景编号 */
    private Long sid;

    /** 产品id */
    @Excel(name = "产品id")
    private Long prodId;

    /** 场景id */
    @Excel(name = "场景id")
    private Long sceneId;

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
    public void setSceneId(Long sceneId) 
    {
        this.sceneId = sceneId;
    }

    public Long getSceneId() 
    {
        return sceneId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("prodId", getProdId())
            .append("sceneId", getSceneId())
            .toString();
    }
}
