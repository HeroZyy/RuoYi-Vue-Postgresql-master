package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class B2bProductConfigList extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
//    private Long sid;

    /** 设备分项 */
    @Excel(name = "设备分项")
    private List<B2bDevDetailedlist> devList;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private Long devType;

    /** 设备类型名称 */
    @Excel(name = "设备类型名称")
    private String devTypename;

    /** 产品的id */
    @Excel(name = "产品的id")
    private Long productId;

    public List<B2bDevDetailedlist> getDevList() {
        return devList;
    }

    public void setDevList(List<B2bDevDetailedlist> devList) {
        this.devList = devList;
    }

    public Long getDevType() {
        return devType;
    }

    public void setDevType(Long devType) {
        this.devType = devType;
    }

    public String getDevTypename() {
        return devTypename;
    }

    public void setDevTypename(String devTypename) {
        this.devTypename = devTypename;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public B2bProductConfigList() {
    }

    public B2bProductConfigList(List<B2bDevDetailedlist> devList, Long devType, String devTypename, Long productId) {
        this.devList = devList;
        this.devType = devType;
        this.devTypename = devTypename;
        this.productId = productId;
    }
}
