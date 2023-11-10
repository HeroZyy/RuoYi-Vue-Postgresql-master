package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品文件对象 b2b_product_pfiles
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public class B2bProductPfiles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long sid;

    /** 产品文件地址 */
    @Excel(name = "产品文件地址")
    private String fileUrl;

    /** 0表示图片，1表示视频,2表示ppt */
    @Excel(name = "0表示图片，1表示视频,2表示ppt")
    private String fileType;

    /** 资源排序 */
    @Excel(name = "资源排序")
    private Long orderNum;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String fileName;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private Long productId;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setFileUrl(String fileUrl) 
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl() 
    {
        return fileUrl;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("fileUrl", getFileUrl())
            .append("fileType", getFileType())
            .append("orderNum", getOrderNum())
            .append("fileName", getFileName())
            .append("productId", getProductId())
            .toString();
    }
}
