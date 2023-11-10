package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

public class B2bSceneRecommend extends BaseEntity {
    private Long sid;

    private String SceneName;
    //该场景下的产品列表
    private List<B2bProduct> products;


    public B2bSceneRecommend() {
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getSceneName() {
        return SceneName;
    }

    public void setSceneName(String sceneName) {
        SceneName = sceneName;
    }

    public List<B2bProduct> getProducts() {
        return products;
    }

    public void setProducts(List<B2bProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "B2bSceneRecommend{" +
                "sid=" + sid +
                ", SceneName='" + SceneName + '\'' +
                ", products=" + products +
                '}';
    }

    public B2bSceneRecommend(Long sid, String sceneName, List<B2bProduct> products) {
        this.sid = sid;
        SceneName = sceneName;
        this.products = products;
    }
}
