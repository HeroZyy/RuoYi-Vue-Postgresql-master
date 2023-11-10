package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.B2bScprelation;

/**
 * 场景分类产品关联Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-06
 */
public interface B2bScprelationMapper 
{

    /**
     * 通过场景和分类id查
     */
    public List<B2bScprelation> selectB2bScprelationBySceneSidAndClassSid(Long classId , Long sceneId);

    /**
     * 场景id查
     */
    public List<B2bScprelation> selectB2bScprelationBySceneSid(Long sceneId);

    /**
     * 分类id查
     */
    public List<B2bScprelation> selectB2bScprelationByClassSid(Long classId);

    /**
     * 查询场景分类产品关联
     * 
     * @param sid 场景分类产品关联主键
     * @return 场景分类产品关联
     */
    public B2bScprelation selectB2bScprelationBySid(Long sid);

    /**
     * 查询场景分类产品关联列表
     * 
     * @param b2bScprelation 场景分类产品关联
     * @return 场景分类产品关联集合
     */
    public List<B2bScprelation> selectB2bScprelationList(B2bScprelation b2bScprelation);

    /**
     * 新增场景分类产品关联
     * 
     * @param b2bScprelation 场景分类产品关联
     * @return 结果
     */
    public int insertB2bScprelation(B2bScprelation b2bScprelation);

    /**
     * 修改场景分类产品关联
     * 
     * @param b2bScprelation 场景分类产品关联
     * @return 结果
     */
    public int updateB2bScprelation(B2bScprelation b2bScprelation);

    /**
     * 删除场景分类产品关联
     * 
     * @param sid 场景分类产品关联主键
     * @return 结果
     */
    public int deleteB2bScprelationBySid(Long sid);

    /**
     * 批量删除场景分类产品关联
     * 
     * @param sids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteB2bScprelationBySids(Long[] sids);
}
