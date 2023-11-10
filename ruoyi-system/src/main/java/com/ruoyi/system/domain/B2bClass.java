package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Objects;

/**
 * 分类模块对象 b2b_class
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public class B2bClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增标号 */
    private Long sid;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 分类的排序序号 */
    @Excel(name = "分类的排序序号")
    private Long orderId;

    /** 分类类型的父id */
    @Excel(name = "分类类型的父id")
    private Long parentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        B2bClass b2bClass = (B2bClass) o;
        return Objects.equals(sid, b2bClass.sid) && Objects.equals(name, b2bClass.name) && Objects.equals(orderId, b2bClass.orderId) && Objects.equals(parentId, b2bClass.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, name, orderId, parentId);
    }

    @Override
    public String toString() {
        return "B2bClass{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", parentId=" + parentId +
                '}';
    }

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

}
