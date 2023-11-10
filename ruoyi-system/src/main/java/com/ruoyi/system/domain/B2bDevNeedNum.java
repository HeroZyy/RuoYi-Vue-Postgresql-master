package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class B2bDevNeedNum extends BaseEntity {
    private Long devNum;
    private Long devId;

    public Long getDevNum() {
        return devNum;
    }

    public void setDevNum(Long devNum) {
        this.devNum = devNum;
    }

    public Long getDevId() {
        return devId;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }
}
