package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

public class B2bClassWithProdPro extends BaseEntity {
    private List<B2bProductPro> productPro;

    private B2bClass clazz;

    @Override
    public String toString() {
        return "B2bClassWithProdPro{" +
                "productPro=" + productPro.toString() +
                ", clazz=" + clazz.toString() +
                '}';
    }

    public B2bClassWithProdPro() {

    }

    public List<B2bProductPro> getProductPro() {
        return productPro;
    }

    public void setProductPro(List<B2bProductPro> productPro) {
        this.productPro = productPro;
    }

    public B2bClass getClazz() {
        return clazz;
    }

    public void setClazz(B2bClass clazz) {
        this.clazz = clazz;
    }

    public B2bClassWithProdPro(List<B2bProductPro> productPro, B2bClass clazz) {
        this.productPro = productPro;
        this.clazz = clazz;
    }
}
