package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;
import java.util.Objects;

public class B2bAiListConfig extends BaseEntity {
    private Long productId;
    private Long devType;
    private List<B2bDevNeedNum> devNeedNum;

    public B2bAiListConfig() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getDevType() {
        return devType;
    }

    public void setDevType(Long devType) {
        this.devType = devType;
    }

    public List<B2bDevNeedNum> getDevNeedNum() {
        return devNeedNum;
    }

    public void setDevNeedNum(List<B2bDevNeedNum> devNeedNum) {
        this.devNeedNum = devNeedNum;
    }
}
