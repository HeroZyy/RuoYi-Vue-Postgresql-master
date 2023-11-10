package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 场景模块对象 b2b_scene
 *
 * @author ruoyi
 * @date 2023-10-26
 */
public class B2bScene extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 场景自增标号 */
    private Long sid;

    /** 场景名称 */
    @Excel(name = "场景名称")
    private String name;

    /** 场景的排序序号 */
    @Excel(name = "场景的排序序号")
    private Long orderId;

    /** 场景的父id */
    @Excel(name = "场景的父id")
    private Long parentId;

    public void setSid(Long sid)
    {
        this.sid = sid;
    }

    public Long getSid()
    {
        return sid;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("sid", getSid())
                .append("name", getName())
                .append("orderId", getOrderId())
                .append("parentId", getParentId())
                .toString();
    }
}
