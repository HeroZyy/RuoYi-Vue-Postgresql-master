package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品描述
对象 b2b_product_desc
 * 
 * @author ruoyi
 * @date 2023-10-30
 */
public class B2bProductDesc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品id */
    private Long sid;

    /** 产品描述 */
    @Excel(name = "产品描述")
    private String describe;

    /** 扩展字段1 */
    @Excel(name = "厂家信息")
    private String msg1;

    /** 扩展字段2 */
    @Excel(name = "联系方式")
    private String msg2;

    /** 扩展字段3 */
    @Excel(name = "扩展字段3")
    private String msg3;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setDescribe(String describe) 
    {
        this.describe = describe;
    }

    public String getDescribe() 
    {
        return describe;
    }
    public void setMsg1(String msg1)
    {
        this.msg1 = msg1;
    }

    public String getMsg1()
    {
        return msg1;
    }
    public void setMsg2(String msg2)
    {
        this.msg2 = msg2;
    }

    public String getMsg2()
    {
        return msg2;
    }
    public void setMsg3(String msg3)
    {
        this.msg3 = msg3;
    }

    public String getMsg3()
    {
        return msg3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("describe", getDescribe())
            .append("msg1", getMsg1())
            .append("msg2", getMsg2())
            .append("msg3", getMsg3())
            .toString();
    }
}
