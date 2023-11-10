package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Objects;

public class B2bProductPro extends BaseEntity {
    /** 产品id */
    private Long sid;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String prodName;

    /** 产品介绍*/
    @Excel(name = "产品介绍")
    private String desc;

    /** 产品封面 */
    @Excel(name = "产品封面")
    private String img;

    /** 默认价格 */
    @Excel(name = "默认价格")
    private String skudefault;

    /** 是否被收藏 */
    @Excel(name = "是否被收藏")
    private Long isfavorite;

    /** 厂家 */
    @Excel(name = "是否被收藏")
    private String manufacturer;

    public B2bProductPro(Long sid, String prodName, String desc, String img, String skudefault, Long isfavorite, String manufacturer) {
        this.sid = sid;
        this.prodName = prodName;
        this.desc = desc;
        this.img = img;
        this.skudefault = skudefault;
        this.isfavorite = isfavorite;
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        B2bProductPro that = (B2bProductPro) o;
        return Objects.equals(sid, that.sid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, prodName, desc, img, skudefault, isfavorite, manufacturer);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "B2bProductPro{" +
                "sid=" + sid +
                ", prodName='" + prodName + '\'' +
                ", desc='" + desc + '\'' +
                ", img='" + img + '\'' +
                ", skudefault='" + skudefault + '\'' +
                ", isfavorite=" + isfavorite +
                '}';
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSkudefault() {
        return skudefault;
    }

    public void setSkudefault(String skudefault) {
        this.skudefault = skudefault;
    }

    public Long getIsfavorite() {
        return isfavorite;
    }

    public void setIsfavorite(Long isfavorite) {
        this.isfavorite = isfavorite;
    }

    public B2bProductPro() {

    }

    public B2bProductPro(Long sid, String prodName, String desc, String img, String skudefault, Long isfavorite) {
        this.sid = sid;
        this.prodName = prodName;
        this.desc = desc;
        this.img = img;
        this.skudefault = skudefault;
        this.isfavorite = isfavorite;
    }
}
