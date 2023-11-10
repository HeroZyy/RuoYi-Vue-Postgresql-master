package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.B2bRecommendModule;

/**
 * 推荐模块Mapper接口
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public interface B2bRecommendModuleMapper 
{
    /**
     * 查询推荐模块
     * 
     * @param sid 推荐模块主键
     * @return 推荐模块
     */
    public B2bRecommendModule selectB2bRecommendModuleBySid(Long sid);

    /**
     * 查询推荐模块列表
     * 
     * @param b2bRecommendModule 推荐模块
     * @return 推荐模块集合
     */
    public List<B2bRecommendModule> selectB2bRecommendModuleList(B2bRecommendModule b2bRecommendModule);

    /**
     * 新增推荐模块
     * 
     * @param b2bRecommendModule 推荐模块
     * @return 结果
     */
    public int insertB2bRecommendModule(B2bRecommendModule b2bRecommendModule);

    /**
     * 修改推荐模块
     * 
     * @param b2bRecommendModule 推荐模块
     * @return 结果
     */
    public int updateB2bRecommendModule(B2bRecommendModule b2bRecommendModule);

    /**
     * 删除推荐模块
     * 
     * @param sid 推荐模块主键
     * @return 结果
     */
    public int deleteB2bRecommendModuleBySid(Long sid);

    /**
     * 批量删除推荐模块
     * 
     * @param sids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteB2bRecommendModuleBySids(Long[] sids);
}
