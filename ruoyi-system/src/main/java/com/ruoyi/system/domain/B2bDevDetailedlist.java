package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;

/**
 * 智能报价清单对象 b2b_dev_detailedlist
 *
 * @author ruoyi
 * @date 2023-11-08
 */
public class B2bDevDetailedlist extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long sid;

    /**
     * 设备类型名称
     */
    @Excel(name = "类型名称")
    private String devTypename;


    /**
     * 设备名称
     */
    @Excel(name = "设备名称")
    private String devName;

    /**
     * 设备类型
     */
    @Excel(name = "设备类型")
    private Long devType;

    /**
     * 设备品牌范围
     */
    @Excel(name = "设备品牌范围")
    private String devBrand;

    /**
     * 规格要求
     */
    @Excel(name = "规格要求")
    private String devConfig;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String devUnits;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private String devNum;

    /**
     * 单价
     */
    @Excel(name = "单价")
    private String devPerprice;

    /**
     * 总价
     */
    @Excel(name = "总价")
    private String devTotalprice;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String devRemark;

    /**
     * 产品的id
     */
    @Excel(name = "产品的id")
    private Long productId;


    /**
     * 设备id
     */
    @Excel(name = "设备id")
    private Long devId;

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getSid() {
        return sid;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevType(Long devType) {
        this.devType = devType;
    }

    public Long getDevType() {
        return devType;
    }

    public void setDevBrand(String devBrand) {
        this.devBrand = devBrand;
    }

    public String getDevBrand() {
        return devBrand;
    }

    public void setDevConfig(String devConfig) {
        this.devConfig = devConfig;
    }

    public String getDevConfig() {
        return devConfig;
    }

    public void setDevUnits(String devUnits) {
        this.devUnits = devUnits;
    }

    public String getDevUnits() {
        return devUnits;
    }

    public void setDevNum(String devNum) {
        this.devNum = devNum;
    }

    public String getDevNum() {
        return devNum;
    }

    public void setDevPerprice(String devPerprice) {
        this.devPerprice = devPerprice;
    }

    public String getDevPerprice() {
        return devPerprice;
    }

    public void setDevTotalprice(String devTotalprice) {
        this.devTotalprice = devTotalprice;
    }

    public String getDevTotalprice() {
        return devTotalprice;
    }

    public void setDevRemark(String devRemark) {
        this.devRemark = devRemark;
    }

    public String getDevRemark() {
        return devRemark;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setDevTypename(String devTypename) {
        this.devTypename = devTypename;
    }

    public String getDevTypename() {
        return devTypename;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public Long getDevId() {
        return devId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sid", getSid())
                .append("devName", getDevName())
                .append("devType", getDevType())
                .append("devBrand", getDevBrand())
                .append("devConfig", getDevConfig())
                .append("devUnits", getDevUnits())
                .append("devNum", getDevNum())
                .append("devPerprice", getDevPerprice())
                .append("devTotalprice", getDevTotalprice())
                .append("devRemark", getDevRemark())
                .append("productId", getProductId())
                .append("devTypename", getDevTypename())
                .append("devId", getDevId())
                .toString();
    }
}
