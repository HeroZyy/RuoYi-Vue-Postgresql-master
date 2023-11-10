package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

public class ProductList extends BaseEntity {
    private List<B2bProductPro> productList;

    @Override
    public String toString() {
        return "ProductList{" +
                "productList=" + productList +
                '}';
    }

    public List<B2bProductPro> getProductList() {
        return productList;
    }

    public void setProductList(List<B2bProductPro> productList) {
        this.productList = productList;
    }

    public ProductList() {
    }

    public ProductList(List<B2bProductPro> productList) {
        this.productList = productList;
    }
}
