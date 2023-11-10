package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 推荐模块对象 b2b_recommend_module
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public class B2bRecommendModule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增id */
    private Long sid;

    /** 推荐id */
    @Excel(name = "推荐id")
    private Long recId;

    /** 推荐栏目名称(有轮播、精品推荐、场景、类型) */
    @Excel(name = "推荐栏目名称(有轮播、精品推荐、场景、类型)")
    private String recName;

    /** 用数字表示推荐栏目中文名称(0:首页轮播1:首页精品推荐2:xx场景3:xx产品) */
    @Excel(name = "用数字表示推荐栏目中文名称(0:首页轮播 ,1:首页精品推荐 ,2:xx场景 ,3:xx产品)")
    private Long recType;

    /** 分类的id */
    @Excel(name = "分类的id")
    private Long classId;

    /** 场景的id */
    @Excel(name = "场景的id")
    private Long sceneId;

    public void setSid(Long sid) 
    {
        this.sid = sid;
    }

    public Long getSid() 
    {
        return sid;
    }
    public void setRecId(Long recId) 
    {
        this.recId = recId;
    }

    public Long getRecId() 
    {
        return recId;
    }
    public void setRecName(String recName) 
    {
        this.recName = recName;
    }

    public String getRecName() 
    {
        return recName;
    }
    public void setRecType(Long recType) 
    {
        this.recType = recType;
    }

    public Long getRecType() 
    {
        return recType;
    }
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setSceneId(Long sceneId) 
    {
        this.sceneId = sceneId;
    }

    public Long getSceneId() 
    {
        return sceneId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sid", getSid())
            .append("recId", getRecId())
            .append("recName", getRecName())
            .append("recType", getRecType())
            .append("classId", getClassId())
            .append("sceneId", getSceneId())
            .toString();
    }
}
