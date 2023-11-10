package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;
import java.util.Objects;

public class B2bProductDetail extends BaseEntity {

    /**
     * product
     */
    private B2bProduct product;

    /**
     * sku
     */
    private List<B2bSku> sku;

//    /**
//     * download
//     */
//    private List<B2bProductDownload> download;

    /**
     * pfiles
     */
    private List<B2bProductPfiles> pfiles;

    /**
     * downmsg
     */
    private List<DownloadMsg> dlmsg;


    /**
     *
     * desc
     * @return
     */
    private String desc;

    /**
     * isFavorite 1代表收藏
     * @return
     */
    private Long isFavorite;

    /**
     * manufacturer 厂家
     * @return
     */
    private String manufacturer;

    /**
     * 标准报价清单
     * @return
     */
    private List<B2bProductPfiles> pricelist;

    public B2bProductDetail(B2bProduct product, List<B2bSku> sku, List<B2bProductPfiles> pfiles, List<DownloadMsg> dlmsg, String desc, Long isFavorite, String manufacturer, List<B2bProductPfiles> pricelist) {
        this.product = product;
        this.sku = sku;
        this.pfiles = pfiles;
        this.dlmsg = dlmsg;
        this.desc = desc;
        this.isFavorite = isFavorite;
        this.manufacturer = manufacturer;
        this.pricelist = pricelist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        B2bProductDetail that = (B2bProductDetail) o;
        return Objects.equals(product, that.product) && Objects.equals(sku, that.sku) && Objects.equals(pfiles, that.pfiles) && Objects.equals(dlmsg, that.dlmsg) && Objects.equals(desc, that.desc) && Objects.equals(isFavorite, that.isFavorite) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(pricelist, that.pricelist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, sku, pfiles, dlmsg, desc, isFavorite, manufacturer, pricelist);
    }

    @Override
    public String toString() {
        return "B2bProductDetail{" +
                "product=" + product +
                ", sku=" + sku +
                ", pfiles=" + pfiles +
                ", dlmsg=" + dlmsg +
                ", desc='" + desc + '\'' +
                ", isFavorite=" + isFavorite +
                ", manufacturer='" + manufacturer + '\'' +
                ", pricelist=" + pricelist +
                '}';
    }

    public List<B2bProductPfiles> getPricelist() {
        return pricelist;
    }

    public void setPricelist(List<B2bProductPfiles> pricelist) {
        this.pricelist = pricelist;
    }

    public B2bProductDetail(B2bProduct product, List<B2bSku> sku, List<B2bProductPfiles> pfiles, List<DownloadMsg> dlmsg, String desc, Long isFavorite, String manufacturer) {
        this.product = product;
        this.sku = sku;
        this.pfiles = pfiles;
        this.dlmsg = dlmsg;
        this.desc = desc;
        this.isFavorite = isFavorite;
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public B2bProduct getProduct() {
        return product;
    }

    public void setProduct(B2bProduct product) {
        this.product = product;
    }

    public List<B2bSku> getSku() {
        return sku;
    }

    public void setSku(List<B2bSku> sku) {
        this.sku = sku;
    }

    public List<B2bProductPfiles> getPfiles() {
        return pfiles;
    }

    public void setPfiles(List<B2bProductPfiles> pfiles) {
        this.pfiles = pfiles;
    }

    public List<DownloadMsg> getDlmsg() {
        return dlmsg;
    }

    public void setDlmsg(List<DownloadMsg> dlmsg) {
        this.dlmsg = dlmsg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Long isFavorite) {
        this.isFavorite = isFavorite;
    }

    public B2bProductDetail(B2bProduct product, List<B2bSku> sku, List<B2bProductPfiles> pfiles, List<DownloadMsg> dlmsg, String desc, Long isFavorite) {
        this.product = product;
        this.sku = sku;
        this.pfiles = pfiles;
        this.dlmsg = dlmsg;
        this.desc = desc;
        this.isFavorite = isFavorite;
    }
}
