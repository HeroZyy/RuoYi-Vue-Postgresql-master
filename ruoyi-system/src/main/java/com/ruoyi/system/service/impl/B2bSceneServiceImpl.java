package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.B2bSceneMapper;
import com.ruoyi.system.domain.B2bScene;
import com.ruoyi.system.service.IB2bSceneService;

/**
 * 场景模块Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
@Service
public class B2bSceneServiceImpl implements IB2bSceneService 
{
    @Autowired
    private B2bSceneMapper b2bSceneMapper;

    /**
     * 查询场景模块
     * 
     * @param sid 场景模块主键
     * @return 场景模块
     */
    @Override
    public B2bScene selectB2bSceneBySid(Long sid)
    {
        return b2bSceneMapper.selectB2bSceneBySid(sid);
    }

    /**
     * 查询场景模块列表
     * 
     * @param b2bScene 场景模块
     * @return 场景模块
     */
    @Override
    public List<B2bScene> selectB2bSceneList(B2bScene b2bScene)
    {
        return b2bSceneMapper.selectB2bSceneList(b2bScene);
    }

    /**
     * 新增场景模块
     * 
     * @param b2bScene 场景模块
     * @return 结果
     */
    @Override
    public int insertB2bScene(B2bScene b2bScene)
    {
        return b2bSceneMapper.insertB2bScene(b2bScene);
    }

    /**
     * 修改场景模块
     * 
     * @param b2bScene 场景模块
     * @return 结果
     */
    @Override
    public int updateB2bScene(B2bScene b2bScene)
    {
        return b2bSceneMapper.updateB2bScene(b2bScene);
    }

    /**
     * 批量删除场景模块
     * 
     * @param sids 需要删除的场景模块主键
     * @return 结果
     */
    @Override
    public int deleteB2bSceneBySids(Long[] sids)
    {
        return b2bSceneMapper.deleteB2bSceneBySids(sids);
    }

    /**
     * 删除场景模块信息
     * 
     * @param sid 场景模块主键
     * @return 结果
     */
    @Override
    public int deleteB2bSceneBySid(Long sid)
    {
        return b2bSceneMapper.deleteB2bSceneBySid(sid);
    }
}
