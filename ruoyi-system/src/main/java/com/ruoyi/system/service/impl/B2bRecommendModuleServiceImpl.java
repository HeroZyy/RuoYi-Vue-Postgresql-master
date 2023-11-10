package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bRecommendModuleMapper;
import com.ruoyi.system.domain.B2bRecommendModule;
import com.ruoyi.system.service.IB2bRecommendModuleService;

/**
 * 推荐模块Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@Service
public class B2bRecommendModuleServiceImpl implements IB2bRecommendModuleService 
{
    @Autowired
    private B2bRecommendModuleMapper b2bRecommendModuleMapper;

    /**
     * 查询推荐模块
     * 
     * @param sid 推荐模块主键
     * @return 推荐模块
     */
    @Override
    public B2bRecommendModule selectB2bRecommendModuleBySid(Long sid)
    {
        return b2bRecommendModuleMapper.selectB2bRecommendModuleBySid(sid);
    }

    /**
     * 查询推荐模块列表
     * 
     * @param b2bRecommendModule 推荐模块
     * @return 推荐模块
     */
    @Override
    public List<B2bRecommendModule> selectB2bRecommendModuleList(B2bRecommendModule b2bRecommendModule)
    {
        return b2bRecommendModuleMapper.selectB2bRecommendModuleList(b2bRecommendModule);
    }

    /**
     * 新增推荐模块
     * 
     * @param b2bRecommendModule 推荐模块
     * @return 结果
     */
    @Override
    public int insertB2bRecommendModule(B2bRecommendModule b2bRecommendModule)
    {
        return b2bRecommendModuleMapper.insertB2bRecommendModule(b2bRecommendModule);
    }

    /**
     * 修改推荐模块
     * 
     * @param b2bRecommendModule 推荐模块
     * @return 结果
     */
    @Override
    public int updateB2bRecommendModule(B2bRecommendModule b2bRecommendModule)
    {
        return b2bRecommendModuleMapper.updateB2bRecommendModule(b2bRecommendModule);
    }

    /**
     * 批量删除推荐模块
     * 
     * @param sids 需要删除的推荐模块主键
     * @return 结果
     */
    @Override
    public int deleteB2bRecommendModuleBySids(Long[] sids)
    {
        return b2bRecommendModuleMapper.deleteB2bRecommendModuleBySids(sids);
    }

    /**
     * 删除推荐模块信息
     * 
     * @param sid 推荐模块主键
     * @return 结果
     */
    @Override
    public int deleteB2bRecommendModuleBySid(Long sid)
    {
        return b2bRecommendModuleMapper.deleteB2bRecommendModuleBySid(sid);
    }
}
