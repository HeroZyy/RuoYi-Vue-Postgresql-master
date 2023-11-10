package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;

public class DownloadMsg {
    /** 产品id */
    @Excel(name = "产品id")
    private Long prodId;

    /** 下载地址 */
    @Excel(name = "下载地址")
    private String url;

    /** 是否允许下载 */
    @Excel(name = "是否允许下载")
    private Long allowDl;

    /** 下载名称 */
    @Excel(name = "下载名称")
    private String dlname;

    public DownloadMsg(Long prodId, String url, Long allowDl, String dlname) {
        this.prodId = prodId;
        this.url = url;
        this.allowDl = allowDl;
        this.dlname = dlname;
    }

    public DownloadMsg() {

    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getAllowDl() {
        return allowDl;
    }

    public void setAllowDl(Long allowDl) {
        this.allowDl = allowDl;
    }

    public String getDlname() {
        return dlname;
    }

    public void setDlname(String dlname) {
        this.dlname = dlname;
    }

    @Override
    public String toString() {
        return "DownloadMsg{" +
                "prodId=" + prodId +
                ", url='" + url + '\'' +
                ", allowDl=" + allowDl +
                ", dlname='" + dlname + '\'' +
                '}';
    }
}
