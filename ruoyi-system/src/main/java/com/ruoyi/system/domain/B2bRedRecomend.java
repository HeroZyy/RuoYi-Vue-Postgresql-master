package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class B2bRedRecomend extends BaseEntity {
    //比起B2bRecommendrel多了pfiles,rec_name
    private Long sid;
    private Long type;
    private String rec_name;
    private Long order_id;
    //获取产品的资料
    private B2bProductPfiles pfiles;
    //分类id
    private Long class_id;
    //场景id
    private Long scene_id;
    //分类名称
    private String class_name;
    //场景名称
    private String scene_name;
    //产品信息
    private B2bProductPro productPro;

    public B2bProductPro getProductPro() {
        return productPro;
    }

    public void setProductPro(B2bProductPro productPro) {
        this.productPro = productPro;
    }

    public B2bRedRecomend(Long sid, Long type, String rec_name, Long order_id, B2bProductPfiles pfiles, Long class_id, Long scene_id, String class_name, String scene_name, B2bProductPro productPro) {
        this.sid = sid;
        this.type = type;
        this.rec_name = rec_name;
        this.order_id = order_id;
        this.pfiles = pfiles;
        this.class_id = class_id;
        this.scene_id = scene_id;
        this.class_name = class_name;
        this.scene_name = scene_name;
        this.productPro = productPro;
    }

    @Override
    public String toString() {
        return "B2bRedRecomend{" +
                "sid=" + sid +
                ", type=" + type +
                ", rec_name='" + rec_name + '\'' +
                ", order_id=" + order_id +
                ", pfiles=" + pfiles +
                ", class_id=" + class_id +
                ", scene_id=" + scene_id +
                ", class_name='" + class_name + '\'' +
                ", scene_name='" + scene_name + '\'' +
                '}';
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getScene_name() {
        return scene_name;
    }

    public void setScene_name(String scene_name) {
        this.scene_name = scene_name;
    }

    public B2bRedRecomend(Long sid, Long type, String rec_name, Long order_id, B2bProductPfiles pfiles, Long class_id, Long scene_id, String class_name, String scene_name) {
        this.sid = sid;
        this.type = type;
        this.rec_name = rec_name;
        this.order_id = order_id;
        this.pfiles = pfiles;
        this.class_id = class_id;
        this.scene_id = scene_id;
        this.class_name = class_name;
        this.scene_name = scene_name;
    }

    public B2bRedRecomend(Long sid, Long type, String rec_name, Long order_id, B2bProductPfiles pfiles, Long class_id, Long scene_id) {
        this.sid = sid;
        this.type = type;
        this.rec_name = rec_name;
        this.order_id = order_id;
        this.pfiles = pfiles;
        this.class_id = class_id;
        this.scene_id = scene_id;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public Long getScene_id() {
        return scene_id;
    }

    public void setScene_id(Long scene_id) {
        this.scene_id = scene_id;
    }

    public B2bProductPfiles getPfiles() {
        return pfiles;
    }

    public void setPfiles(B2bProductPfiles pfiles) {
        this.pfiles = pfiles;
    }

    public B2bRedRecomend(Long sid, Long type, String rec_name, Long order_id, B2bProductPfiles pfiles) {
        this.sid = sid;
        this.type = type;
        this.rec_name = rec_name;
        this.order_id = order_id;
        this.pfiles = pfiles;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public B2bRedRecomend(Long sid, Long type, String rec_name, Long order_id) {
        this.sid = sid;
        this.type = type;
        this.rec_name = rec_name;
        this.order_id = order_id;
    }

    public B2bRedRecomend() {

    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getRec_name() {
        return rec_name;
    }

    public void setRec_name(String rec_name) {
        this.rec_name = rec_name;
    }

    public B2bRedRecomend(Long sid, Long type, String rec_name) {
        this.sid = sid;
        this.type = type;
        this.rec_name = rec_name;
    }
}
