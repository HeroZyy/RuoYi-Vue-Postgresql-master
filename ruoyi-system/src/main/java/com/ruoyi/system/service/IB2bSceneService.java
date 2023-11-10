package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bScene;

/**
 * 场景模块Service接口
 * 
 * @author ruoyi
 * @date 2023-10-26
 */
public interface IB2bSceneService 
{
    /**
     * 查询场景模块
     * 
     * @param sid 场景模块主键
     * @return 场景模块
     */
    public B2bScene selectB2bSceneBySid(Long sid);

    /**
     * 查询场景模块列表
     * 
     * @param b2bScene 场景模块
     * @return 场景模块集合
     */
    public List<B2bScene> selectB2bSceneList(B2bScene b2bScene);

    /**
     * 新增场景模块
     * 
     * @param b2bScene 场景模块
     * @return 结果
     */
    public int insertB2bScene(B2bScene b2bScene);

    /**
     * 修改场景模块
     * 
     * @param b2bScene 场景模块
     * @return 结果
     */
    public int updateB2bScene(B2bScene b2bScene);

    /**
     * 批量删除场景模块
     * 
     * @param sids 需要删除的场景模块主键集合
     * @return 结果
     */
    public int deleteB2bSceneBySids(Long[] sids);

    /**
     * 删除场景模块信息
     * 
     * @param sid 场景模块主键
     * @return 结果
     */
    public int deleteB2bSceneBySid(Long sid);
}
