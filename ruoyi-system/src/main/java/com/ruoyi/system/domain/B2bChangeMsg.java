package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;

public class B2bChangeMsg extends BaseEntity {
    private String userName ;
    private String email    ;
    private String sex      ;
    private String avatar   ;
    private String realname ;
    private String company  ;
    private String addr     ;

    public B2bChangeMsg() {
    }

    @Override
    public String toString() {
        return "B2bChangeMsg{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar='" + avatar + '\'' +
                ", realname='" + realname + '\'' +
                ", company='" + company + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public B2bChangeMsg(String userName, String email, String sex, String avatar, String realname, String company, String addr) {
        this.userName = userName;
        this.email = email;
        this.sex = sex;
        this.avatar = avatar;
        this.realname = realname;
        this.company = company;
        this.addr = addr;
    }
}
