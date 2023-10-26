package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.B2bProductFavorite;

/**
 * 产品收藏Service接口
 * 
 * @author ruoyi
 * @date 2023-10-20
 */
public interface IB2bProductFavoriteService 
{
    /**
     * 查询产品收藏
     * 
     * @param sid 产品收藏主键
     * @return 产品收藏
     */
    public B2bProductFavorite selectB2bProductFavoriteBySid(Long sid);

    /**
     * 查询产品收藏列表
     * 
     * @param b2bProductFavorite 产品收藏
     * @return 产品收藏集合
     */
    public List<B2bProductFavorite> selectB2bProductFavoriteList(B2bProductFavorite b2bProductFavorite);

    /**
     * 新增产品收藏
     * 
     * @param b2bProductFavorite 产品收藏
     * @return 结果
     */
    public int insertB2bProductFavorite(B2bProductFavorite b2bProductFavorite);

    /**
     * 修改产品收藏
     * 
     * @param b2bProductFavorite 产品收藏
     * @return 结果
     */
    public int updateB2bProductFavorite(B2bProductFavorite b2bProductFavorite);

    /**
     * 批量删除产品收藏
     * 
     * @param sids 需要删除的产品收藏主键集合
     * @return 结果
     */
    public int deleteB2bProductFavoriteBySids(Long[] sids);

    /**
     * 删除产品收藏信息
     * 
     * @param sid 产品收藏主键
     * @return 结果
     */
    public int deleteB2bProductFavoriteBySid(Long sid);
}
